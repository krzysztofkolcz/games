package majoloowip.engine;

import majoloowip.engine.gfx.Font;
import majoloowip.engine.gfx.Image;
import majoloowip.engine.gfx.ImageRequest;
import majoloowip.engine.gfx.ImageTile;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Renderer {

    private int pw,ph;
    private int[] p;
    private int[] zb;
    private int[] lm;
    private int[] lb;
    private Font font = Font.STANDARD;
    private int ambientColor = 0xff;
    private int zDepth = 0;

    private boolean processing;
    private List<ImageRequest> imageRequests = new ArrayList<>();

    public int getzDepth() {
        return zDepth;
    }

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }


    public Renderer(GameContainer gc) {
        this.pw = gc.getWidth();
        this.ph = gc.getHeight();
        this.p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zb = new int[p.length];
        lm = new int[p.length];
        lb = new int[p.length];
    }

    public void clear(){
        for(int i = 0; i < p.length; i++){
            p[i] = 0;
            zb[i] = 0;
//            p[i] += i;
            lm[i] = ambientColor;
            lb[i] = 0;
        }
    }


    public void drawText(String text,int offX, int offY,int color){
        text = text.toUpperCase();
        int offset = 0;
        for(int i = 0; i < text.length(); i++){
            int unicode = text.codePointAt(i) - 32;
            for(int y = 0; y < font.getFontImage().getH(); y++){
                for(int x = 0; x < font.getWidths()[unicode]; x++){
                    if(font.getFontImage().getP()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getW()] == 0xffffffff){
                        setPixel(x + offX + offset, y + offY, color);
                    }
                }
            }
            offset += font.getWidths()[unicode];
        }
    }

    public void drawImage(Image image,int offX, int offY){

        if(image.isAlpha() && !processing){
           imageRequests.add(new ImageRequest(image,zDepth,offX,offY));
        }
        int imageHeight = image.getH();
        int imageWidth = image.getW();
        if(offX < - imageWidth){ return; }
        if(offX < - imageWidth){ return; }
        if(offX >= pw){ return; }
        if(offY >= ph){ return; }
        int drawImageYStart = 0;
        int drawImageXStart = 0;
        int drawImageYFinish = imageHeight;
        int drawImageXFinish = imageWidth;
        if(drawImageYFinish + offY > ph){ drawImageYFinish  = ph - offY; }
        if(drawImageXFinish + offX > pw){ drawImageXFinish = pw - offX; }
        if(offX < 0){ drawImageXStart -= offX; }
        if(offY < 0){ drawImageYStart -= offY; }
        for(int y = drawImageYStart; y < drawImageYFinish; y++){
            for(int x = drawImageXStart; x < drawImageXFinish; x++){
                setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
            }
        }
    }

    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){
        if(image.isAlpha() && !processing){
            imageRequests.add(new ImageRequest(image.getTileImage(tileX,tileY),zDepth,offX,offY));
        }
        int imageHeight = image.getTileH();
        int imageWidth = image.getTileW();
        if(offX < - imageWidth){ return; }
        if(offX < - imageWidth){ return; }
        if(offX >= pw){ return; }
        if(offY >= ph){ return; }
        int drawImageYStart = 0;
        int drawImageXStart = 0;
        int drawImageYFinish = imageHeight;
        int drawImageXFinish = imageWidth;
        if(drawImageYFinish + offY > ph){ drawImageYFinish  = ph - offY; }
        if(drawImageXFinish + offX > pw){ drawImageXFinish = pw - offX; }
        if(offX < 0){ drawImageXStart -= offX; }
        if(offY < 0){ drawImageYStart -= offY; }
        for(int y = drawImageYStart; y < drawImageYFinish; y++){
            for(int x = drawImageXStart; x < drawImageXFinish; x++){
                setPixel(x + offX, y + offY, image.getP()[(x + tileX*image.getTileW())+ (y + tileY*image.getTileH()) * image.getW()]);
            }
        }
    }

    public void setPixel(int x, int y, int value){
        int alpha = ((value >> 24) & 0xff);
        int index = 0;
        try {
            if ((x < 0 || x >= pw || y < 0 || y >= ph) || alpha ==0 ) {
                return;
            }
            index = x + y * pw;
            if(zb[index] > zDepth){
                return;
            }

            if(alpha == 255) {
                p[index] = value;
            }else{
                int color = 0;
                int pixelColor = p[index];
                int newRed = ((pixelColor >> 16) & 0xff) - (int)(((pixelColor >> 16) & 0xff - (value >> 16) & 0xff) * (alpha / 255f));
                int newGreen = ((pixelColor >> 8) & 0xff) - (int)(((pixelColor >> 8) & 0xff - (value >> 8) & 0xff) * (alpha / 255f));
                int newBlue = (pixelColor & 0xff) - (int)((pixelColor  & 0xff - value & 0xff) * (alpha / 255f));
                p[index] = (255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("x:"+x);
            System.out.println("y:"+y);
            System.out.println("pw:"+pw);
            System.out.println("Array size:"+p.length);
            int res = x + y * pw;
            System.out.println("x + y * pw:"+res);
        }
    }

    public void setMapLight(int x, int y, int value){
        if ((x < 0 || x >= pw || y < 0 || y >= ph) ) {
            return;
        }
        int baseColor = lm[x + y * pw];
        int finalColor =0 ;
        int maxRed = Math.max((baseColor >> 16) & 0xff,(value >> 16) & 0xff);
        int maxGreen = Math.max((baseColor >> 8) & 0xff,(value >> 8) & 0xff);
        int maxBlue = Math.max(baseColor & 0xff,value & 0xff);
        lm[x + y * pw] = (maxRed << 16 | maxGreen << 8 | maxBlue);
    }

    public void process(){
        processing = true;
        Collections.sort(imageRequests, new Comparator<ImageRequest>(){
            @Override
            public int compare(ImageRequest o1, ImageRequest o2) {
               if(o1.zDepth < o2.zDepth)
                   return -1;
                if(o1.zDepth > o2.zDepth)
                    return 1;
                return 0;
            }
        });

        for(int i = 0; i < imageRequests.size(); i++){
            ImageRequest ir = imageRequests.get(i);
            setzDepth(ir.zDepth);
            drawImage(ir.image,ir.offx,ir.offy);
        }

        for(int i = 0; i < p.length; i++){
            float r = ((lm[i]>>16) & 0xff ) / 255f;
            float g = ((lm[i]>>8) & 0xff) / 255f;
            float b = ((lm[i]) & 0xff) / 255f;

            p[i] = (
                (int)(((p[i] >> 16) & 0xff) * r) << 16 |
                (int)(((p[i] >> 8) & 0xff) * r) << 8 |
                (int)((p[i] & 0xff) * r)
            );
        }

        imageRequests.clear();
        processing = false;
    }

    private int redShif(int p){
        return (p >> 16) & 0xff;
    }

    private int greenShif(int p){
        return (p >> 8) & 0xff;
    }

    private int blueShif(int p){
        return p & 0xff;
    }

}

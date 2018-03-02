package majoloowip.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {

    private int w,h;
    private int[] p;

    private boolean alpha = false;

    public Image(int[] p, int w, int h){
        this.p = p;
        this.w = w;
        this.h = h;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setAplha(boolean alpha) {
        this.alpha = alpha;
    }

    public Image(String ph){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Image.class.getClassLoader().getResourceAsStream(ph));
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = image.getWidth();
        h = image.getHeight();
        p = image.getRGB(0,0,w,h,null,0,w);
        image.flush();
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int[] getP() {
        return p;
    }
}

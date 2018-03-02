package majoloowip.engine.gfx;

public class Font {

    public static final Font STANDARD = new Font("fonts/standard.png");

    private Image fontImage;
    private int[] offsets;
    private int[] widths;
    private final static int NUMBER_OF_FONTS = 59;

    public Image getFontImage() {
        return fontImage;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public int[] getWidths() {
        return widths;
    }

    public Font(String path){

        fontImage = new Image(path);
        offsets = new int[NUMBER_OF_FONTS];
        widths = new int[NUMBER_OF_FONTS];

        int unicode = 0;

        for(int i = 0; i < fontImage.getW(); i++){
            if(fontImage.getP()[i] == 0xff0000ff){//white pixel indicates start of character
                offsets[unicode] = i;
            }
            if(fontImage.getP()[i] == 0xffffff00){//white pixel indicates start of character
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
    }

}

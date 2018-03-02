package majoloowip.engine.gfx;

public class ImageRequest {

    public ImageRequest(Image image, int zDepth, int offx, int offy ) {
        this.zDepth = zDepth;
        this.offx = offx;
        this.offy = offy;
        this.image = image;
    }

    public int zDepth;
    public int offx, offy;
    public Image image;
}

package majoloowip.game;

import majoloowip.engine.AbstractGame;
import majoloowip.engine.GameContainer;
import majoloowip.engine.Renderer;
import majoloowip.engine.gfx.Image;
import majoloowip.engine.gfx.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame{

    ImageTile image;
    ImageTile image2;
    float tmp;

    public GameManager() {
        image = new ImageTile("bryly.png",68,70);
        image2 = new ImageTile("example.png",60,60);
        image2.setAplha(true);
    }

    @Override
    public void update(GameContainer gc, float dt) {
        tmp += (dt*20);
        /*
        if(gc.getInput().isKeyDown(KeyEvent.VK_A)){
            System.out.println("A pressed");
        };
        */
        if(tmp > 5){
            tmp = 0;
        }
    }

    @Override
    public void render(GameContainer gc, Renderer renderer) {
        for(int x = 0; x < image.getTileW(); x++){
            for(int y = 0; y < image.getH(); y++){
//                renderer.setLightMap(x,y,image.getP()[x+y*image.getW()]);
            }
        }
        renderer.drawImageTile(image2,gc.getInput().getMouseX()-8,gc.getInput().getMouseY()-8,0,0);
        renderer.drawImageTile(image,0,0,(int)tmp,0);
//        renderer.drawImageTile(image,gc.getInput().getMouseX()-8,gc.getInput().getMouseY()-8,(int)tmp,0);
    }

    public static void main(String[] args) {
        AbstractGame game = new GameManager();
        GameContainer gc = new GameContainer(game);
        gc.start();
    }
}

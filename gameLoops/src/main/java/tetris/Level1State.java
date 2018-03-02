package tetris;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    private GameStateManager gsm;
    private float x;
    private float y;
    private int speed = 1;// speed = 10 px /1 sec
    private int speedPerFrame = 0;
    // 10 px / 1 sec;
    // 1 sec = 200 updates
    // 10 px / 200 updates
    // 1px / 20 updates
    private int frameCounter = 0;

    private boolean leftKeyWasPressed = false;
    private boolean rightKeyWasPressed = false;
    private boolean upKeyWasPressed = false;
    private boolean downKeyWasPressed = false;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        x = 0;
        y = 0;
    }

    private void checkLeftKey(){
        if (Input.getKeyDown(KeyEvent.VK_LEFT) || Input.getKeyCurrent(KeyEvent.VK_LEFT)) {
            this.leftKeyWasPressed = true;
        }
    }

    private void checkRightKey(){
        if(Input.getKeyDown(KeyEvent.VK_RIGHT) || Input.getKeyCurrent(KeyEvent.VK_RIGHT)){
            this.rightKeyWasPressed = true;
        }
    }

    private void checkUpKey(){
        if(Input.getKeyDown(KeyEvent.VK_UP) || Input.getKeyCurrent(KeyEvent.VK_UP)){
            this.upKeyWasPressed = true;
        }
    }

    private void checkDownKey(){
        if (Input.getKeyDown(KeyEvent.VK_DOWN) || Input.getKeyCurrent(KeyEvent.VK_DOWN)) {
            this.downKeyWasPressed = true;
        }
    }

    private void checkAllKeys(){
        checkLeftKey();
        checkRightKey();
        checkUpKey();
        checkDownKey();
    }

    private void resetAllKeys(){
        resetDownKey();
        resetUpKey();
        resetLeftKey();
        resetRightKey();
    }

    private void resetLeftKey(){
        leftKeyWasPressed = false;
    }

    private void resetRightKey(){
        rightKeyWasPressed = false;
    }

    private void resetUpKey(){
        upKeyWasPressed = false;
    }

    private void resetDownKey(){
        downKeyWasPressed = false;
    }


    public void update() {
        frameCounter++;
        checkAllKeys();
        if(frameCounter >= 20){
            System.out.println("Move");
            frameCounter = 0;
            if (this.leftKeyWasPressed) {
                x -= 5;
                if (x < 0) {
                    x = Loop01.WIDTH;
                }
            }

            if (this.rightKeyWasPressed) {
                x += 5;
                if (x > Loop01.WIDTH) {
                    x = 0;
                }
            }

            if (this.upKeyWasPressed) {
//                y--;
                y -= 5;
                if (y < 0) {
                    y = Loop01.HEIGHT;
                }
            }

            if (this.downKeyWasPressed) {
//                y++;
                y += 5;
                if (y > Loop01.HEIGHT) {
                    y = 0;
                }
            }
            resetAllKeys();
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Loop01.WIDTH, Loop01.HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, 4, 4);
    }


}

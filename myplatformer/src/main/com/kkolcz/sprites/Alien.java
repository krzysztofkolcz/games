package com.kkolcz.sprites;

public class Alien extends Sprite {

    private final int INITIAL_X = 640;

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {

        loadImage("com/kkolcz/spaceship3.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }
        x -= 1;
    }
}

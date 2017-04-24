package com.platformer01_ex01.gamestate;
import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import com.platformer01_ex01.GamePanel;
import com.platformer01_ex01.bricks.*;

public class Level1State extends GameState{

	/* private TileMap tileMap; */
  /* private Background bg; */

  /* TODO - list of single bricks laying on the ground */
  private List<Pos> brickOnGround;

  private Brick brick;
  private List<Brick> brickTypes;
  private int brickCount;
  private int currentBrickNumber;
  private boolean currentFalling;
  private Random generator ;
  private int brickSize = 5; // TODO - param
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
    generator = new Random();
    brickTypes = new ArrayList<Brick>();
    brickTypes.add(new LBrick01());
    brickTypes.add(new LongBrick01());
    brickTypes.add(new SquareBrick01());
    brickTypes.add(new XLBrick01());
    brickTypes.add(new XZBrick01());
    brickTypes.add(new ZBrick01());
    brickTypes.add(new PlusBrick());

    brickCount = brickTypes.size();
    /* System.out.println("brickCount:"+brickCount); */
    currentBrickNumber = generator.nextInt(brickCount);
    /* System.out.println("currentBrickNumber:"+currentBrickNumber); */
    brick = brickTypes.get(currentBrickNumber);

		/* brick = new PlusBrick(); */
		brick.setFalling(true);
		brick.setXPos(40);
		brick.setYPos(0);

    /* brick = new ZBrick(); */
    brickOnGround = new ArrayList<>();
		brick.setBrickOnGround(brickOnGround);
 
  }
	
	public void update() {
   if(!brick.update()){
      brickOnGround.addAll(brick.getCurrentPosition());
      currentBrickNumber = generator.nextInt(brickCount);
      brick = brickTypes.get(currentBrickNumber);
      /* brick = new PlusBrick(); */
      System.out.println(brick.getClass().getName());
      brick.setFalling(true);
      brick.setXPos(40);
      brick.setYPos(0);
      brick.setBrickOnGround(brickOnGround);
    }
  }
	
	public void draw(Graphics2D g) {
		// clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    /* drawScale(g); */
    brick.draw(g);
    drawBricksOnGround(g);
	}

  private void drawBricksOnGround(Graphics2D g){

		g.setColor(Color.GRAY);
    for(Pos pos : brickOnGround){
      g.fillRect (pos.getX(), pos.getY(), brickSize, brickSize);
    }

		g.setColor(Color.BLACK);
    for(Pos pos : brickOnGround){
      g.drawRect (pos.getX(), pos.getY(), brickSize, brickSize);
    }
  }

  private void drawScale(Graphics2D g){
    for(int i = 0; i<GamePanel.HEIGHT; i+=5){
      g.setColor(Color.GRAY);
      g.drawLine(0,i,GamePanel.WIDTH,i);
      /* g.drawString(Integer.toString(i),20,i); */
    }

    for(int j = 0; j<GamePanel.WIDTH; j+=5){
      g.setColor(Color.GRAY);
      /* g.drawLine(j,0,GamePanel.HEIGHT,j); */
      g.drawLine(j,0,j,GamePanel.HEIGHT);
    }

  }
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) brick.setMoveLeft();
		if(k == KeyEvent.VK_H) brick.setMoveLeft();
		if(k == KeyEvent.VK_RIGHT) brick.setMoveRight();
		if(k == KeyEvent.VK_L) brick.setMoveRight();
		if(k == KeyEvent.VK_DOWN) brick.setMoveDown();
		if(k == KeyEvent.VK_J) brick.setMoveDown();

		if(k == KeyEvent.VK_R) brick.rotateLeft();
		if(k == KeyEvent.VK_D) brick.rotateRight();
  }
	
	public void keyReleased(int k) {
  }

}


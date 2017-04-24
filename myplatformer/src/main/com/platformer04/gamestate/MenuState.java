package com.platformer04.gamestate;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.platformer04.tilemap.Background;



public class MenuState extends GameState{

  private Background bg;
  private String[] options;
  private int currentPosition;
  private GameStateManager gsm;

  public MenuState(GameStateManager gsm){
    this.gsm = gsm;
    init();
  };

  public void init(){
    this.bg = new Background("/resources/backgrounds/menubg.gif",0);
    this.bg.setVector(0.5,0.0);
    options = new String[3];
    currentPosition = 0;
    options[0] = "Start Game";
    options[1] = "Help";
    options[2] = "Quit";
  }

  public void update(){
    bg.update();
  };

  public void draw(Graphics2D g){
    this.bg.draw(g);
    Font font = new Font("Serif", Font.BOLD, 30);
    g.setFont(font);
    for(int i = 0; i<options.length; i++){
      if(currentPosition == i){
        g.setColor(Color.WHITE);
      }else{
        g.setColor(Color.BLUE);
      }
      g.drawString(options[i],40,120+30*i);
    }

  };

  public void select(){
    if(currentPosition == 0){
      // StartGame
      gsm.setState(GameStateManager.LEVEL1STATE);
    }else if(currentPosition == 1){
      // Help
    }else if(currentPosition == 2){
      System.exit(0);
    }
  }

  public void keyPressed(int k){

		if(k == KeyEvent.VK_ENTER){
			select();
		}

    if(k == KeyEvent.VK_UP){
      currentPosition--;
      if(currentPosition < 0){
        currentPosition = options.length-1;
      }

    }
    if(k == KeyEvent.VK_DOWN){
      currentPosition++;
      if(currentPosition > options.length-1){
        currentPosition = 0;
      }
    }

  };

  public void keyReleased(int k){
  };

}

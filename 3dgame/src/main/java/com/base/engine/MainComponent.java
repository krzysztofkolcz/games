package com.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainComponent{

  public static final int WIDTH = 800;
  public static final int HEIGHT = 800;
  public static final String TITLE = "3D engine";
  public static final double FRAME_CAP = 5000.0;//how many updates per second


  private boolean isRunning;
  private Game game;

  public static void main(String[] args){
    Window.createWindow(WIDTH, HEIGHT, TITLE);
    MainComponent mc = new MainComponent();
    mc.start();
  }

  public MainComponent(){
    isRunning = false;
    game = new Game(); 
  }

  public void start(){
    if(isRunning){
      return;
    }

    run();
  }

  public void stop(){
    if(!isRunning){
      return;
    }

    isRunning = false;
  }

  public void run(){
    isRunning = true;
    long frameCounter = 0;
    int frames = 0;

    final double frameTime = 1.0/FRAME_CAP;//the amount of time one frame takes; czyli 1/5000 => 2/10000 => 0.0002s
    long lastTime = Time.getTime();//time when previous frame start to draw
    double unprocessedTime = 0;

    while(isRunning){
      boolean render = false;
      long startTime = Time.getTime();
      long passedTime = startTime - lastTime;//time - how long the previous frame took
      lastTime = startTime;
       
      unprocessedTime += passedTime / (double)Time.SECOND;// co to daje? powiedzmy, że na render zajmuje 1 ms = 1000 ns; 1000 ns / 1000000000 => 1/1000000 => 0.000001
      frameCounter += passedTime;

      while(unprocessedTime > frameTime){
        render = true;
        unprocessedTime -= frameTime;

        if(Window.isCloseRequested()){
          stop(); 
        } 
        Time.setDelta(frameTime);

        // Update Game
        Input.update();
        game.input();
        game.update();
        
        if(frameCounter >= Time.SECOND){
          System.out.println(frames);
          frames = 0;
          frameCounter = 0;
        }

      }


      if(render){
        render();
        frames++;
      }else{
        try{
          Thread.sleep(1);
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }
    }
  }

  public void render(){
    game.render();
    Window.render();
  }

  public void cleanUp(){
    Window.dispose();
  }


}

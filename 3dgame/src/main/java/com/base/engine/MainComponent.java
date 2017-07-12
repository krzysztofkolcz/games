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

    final double frameTime = 1.0/FRAME_CAP;//the amount of time one frame takes; czyli 1/5000 => 2/10000 => 0.0002
    System.out.println("frameTime:"+frameTime); 
    long lastTime = Time.getTime();//time when previous frame start to draw
    double unprocessedTime = 0;

    while(isRunning){
      boolean render = false;
      long startTime = Time.getTime();
      System.out.println("--------------------------------------------"); 
      System.out.println("lastTime:"+lastTime); 
      System.out.println("startTime:"+startTime); 
      long passedTime = startTime - lastTime;//time - how long the previous frame took
      System.out.println("passedTime:"+passedTime); 
      System.out.println("passedTime:"+passedTime/(double)Time.SECOND); 
      lastTime = startTime;
       
      unprocessedTime += passedTime / (double)Time.SECOND;// co to daje? powiedzmy, że na render zajmuje 1 ms = 1000 ns; 1000 ns / 1000000000 => 1/1000000 => 0.000001
      System.out.println("unprocessedTime += passedTime / (double)Time.SECOND"); 
      /* System.out.println( String.format( "unprocessedTime: %.19f", unprocessedTime) );  */
      /* System.out.println( String.format( "unprocessedTime: %.0f", unprocessedTime) );  */
      System.out.println( String.format( "unprocessedTime: %.20f", unprocessedTime) ); 
      frameCounter += passedTime;
      System.out.println("frameCounter += passedTime"); 
      System.out.println("frameCounter:"+frameCounter); 

      long whileLoopTimeStart = Time.getTime();
      int unprocessedTimeI = 0; 
      while(unprocessedTime > frameTime){
        unprocessedTimeI++; 
        /* System.out.println("unprocessedTime > frameTime");  */
        render = true;
        unprocessedTime -= frameTime;
        /* System.out.println("unprocessedTime -= frameTime");  */
        /* System.out.println("unprocessedTime"+unprocessedTime);  */

        if(Window.isCloseRequested()){
          stop(); 
        } 
        Time.setDelta(frameTime);

        // Update Game
        game.input();
        game.update();
        
        if(frameCounter >= Time.SECOND){
          System.out.println("frameCounter >= Time.SECOND"); 
          System.out.println("frames:"+frames); 
          frames = 0;
          frameCounter = 0;
        }

      }
      long whileLoopTimeEnd = Time.getTime();
      long whileLoopTime = whileLoopTimeEnd - whileLoopTimeStart;
      System.out.println("While loop time (l):"+ whileLoopTime );
      System.out.println("While loop time (s):"+whileLoopTime/(double)Time.SECOND);
      System.out.println("While loop count:"+unprocessedTimeI);


      if(render){
        System.out.println("render"); 
        render();
        frames++;
        System.out.println("frames++"); 
        System.out.println("frames:"+frames); 
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

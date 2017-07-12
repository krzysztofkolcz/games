package com.base.engine;

public class Game{

  public Game(){}
  public void input(){}
  public void update(){
  }
  public void render(){
  
    try{
      Thread.sleep(20);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}


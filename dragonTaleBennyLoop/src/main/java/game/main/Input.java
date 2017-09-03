package game.main;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;

public class Input implements KeyListener{

  public static final int NUM_KEYCODES = 256;
  public static final int NUM_MOUSEBUTTONS = 5;

  private  Set<Integer> currentKeys = new HashSet<Integer>(); /* keys that are pressed */
  private  Set<Integer> downKeys = new HashSet<Integer>(); /* keys pressed in this frame */
  private  Set<Integer> upKeys = new HashSet<Integer>(); /* keys up in this frame */

  public  void update(){
    upKeys.clear(); 
    downKeys.clear(); 
  }


  public void printCurrentKeys(){
    for(Integer i : currentKeys){
      System.out.print(i + ",");
    }
  }
  
  public  boolean getCurrentKey(int keyCode){
    return currentKeys.contains(keyCode); 
  }

  public  boolean getKeyDown(int keyCode){
    return downKeys.contains(keyCode); 
  }

  public  boolean getKeyUp(int keyCode){
    return upKeys.contains(keyCode); 
  }

	public void keyTyped(KeyEvent key) {
  }

	public void keyPressed(KeyEvent key) { 
    currentKeys.add(new Integer(key.getKeyCode())); 
    downKeys.add(key.getKeyCode()); 
    System.out.println("pressed:"+key.getKeyCode());
  }

	public void keyReleased(KeyEvent key) { 
    currentKeys.remove(new Integer(key.getKeyCode())); 
    upKeys.add(key.getKeyCode());
  }

}



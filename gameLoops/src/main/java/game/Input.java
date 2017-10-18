package game;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Input{

  private static Set<Integer> currentKeys = new HashSet<Integer>();
  private static Set<Integer> upKeys = new HashSet<Integer>();
  private static Set<Integer> downKeys = new HashSet<Integer>();

  public static void update(){
    upKeys.clear(); 
    downKeys.clear(); 
  }

  public static void setKeyPressed(Integer keyCode){
    System.out.println("setKeyPressed" + keyCode);
    downKeys.add(keyCode);
    currentKeys.add(keyCode);
  }

  public static void setKeyReleased(Integer keyCode){
    System.out.println("setKeyReleased" + keyCode);
    upKeys.add(keyCode);
    currentKeys.remove(keyCode);
    System.out.println(currentKeys);
  }

  public static boolean getKeyDown(Integer keyCode){
    if(downKeys.contains(keyCode))
      return true;
    return false;
  }

  public static boolean getKeyCurrent(Integer keyCode){
    if(currentKeys.contains(keyCode))
      return true;
    return false;
  }

  public static boolean getKeyUp(Integer keyCode){
    if(upKeys.contains(keyCode))
      return true;
    return false; 
  }

}

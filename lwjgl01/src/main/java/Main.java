
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

public class Main implements Runnable{

  // The window handle
  private long window;

  private int width = 1280;
  private int height = 720;

  private Thread thread;
  private boolean running = false;

  public void start(){
    running = false;
    thread = new Thread(this, "Game");
    thread.start(); 
  }

  private void init(){
    /* if(glfwInit() != GL_TRUE){ */
    if(glfwInit() != true){
      /* TODO: handle it */

    } 

    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
    window = glfwCreateWindow(width, height, "Flappy", NULL, NULL);

		/* ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor()); */
		/* glfwSetWindowPos(window, (GLFWvidmode.width(vidmode) - width) / 2, (GLFWvidmode.height(vidmode) - height) / 2); */

    // Get the resolution of the primary monitor
    GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

    // Center the window
    glfwSetWindowPos(
      window,
      (vidmode.width() - pWidth.get(0)) / 2,
      (vidmode.height() - pHeight.get(0)) / 2
    );
  }

  public void run(){
    init();
    while(running){
      update();
      render(); 
    } 
  }

  private void render(){

  }

  private void update(){

  }


  public static void main(String[] args){
    new Main().start();
  }
  

}

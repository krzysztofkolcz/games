/*  */
/* import static org.lwjgl.glfw.GLFW.*; */
/* import static org.lwjgl.opengl.GL11.*; */
/* import static org.lwjgl.system.MemoryUtil.*; */
/*  */
/* import static org.lwjgl.system.MemoryStack.*; */
/*  */
/* import java.nio.ByteBuffer; */

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main implements Runnable{

  // The window handle
  private long window;

  private int width = 1280;
  private int height = 720;

  private Thread thread;
  private boolean running = false;

  public void start(){
    running = true;
    thread = new Thread(this, "Game");
    thread.start(); 
  }

  private void init(){
    /* if(glfwInit() != GL_TRUE){ */
    if(glfwInit() != true){
      /* TODO: handle it */

    } 

    // Configure GLFW
    glfwDefaultWindowHints(); // optional, the current window hints are already the default
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

    // Create the window
    window = glfwCreateWindow(width, height, "Flappy", NULL, NULL);
    if ( window == NULL )
      throw new RuntimeException("Failed to create the GLFW window");

    // Setup a key callback. It will be called every time a key is pressed, repeated or released.
    /*
    glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
      if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
        glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
    });
    */

    glfwSetKeyCallback(window, new Input());

    try ( MemoryStack stack = stackPush() ) {
      IntBuffer pWidth = stack.mallocInt(1); // int* // ADDED
      IntBuffer pHeight = stack.mallocInt(1); // int* //ADDED

      // Get the window size passed to glfwCreateWindow
      glfwGetWindowSize(window, pWidth, pHeight);

      GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

      glfwSetWindowPos(
        window,
        (vidmode.width() - pWidth.get(0)) / 2, //GLFWvidmode -> vidmode
        (vidmode.height() - pHeight.get(0)) / 2 //GLFWvidmode -> vidmode
      );
    }

    // Make the OpenGL context current
    glfwMakeContextCurrent(window);// GLContext.createFromCurrrent -> glfwMakeContextCurrent(window);
    // Make the window visible
    glfwShowWindow(window);

    GL.createCapabilities();
    glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
    glEnable(GL_DEPTH_TEST);
    System.out.println("OpenGL:" + glGetString(GL_VERSION));

  }

  public void run(){
    init();
    while(running){
      update();
      render(); 

      if( glfwWindowShouldClose(window) ) {
        running = false;
      }
    } 
  }

  private void render(){

    //GL.createCapabilities(); // tu też dodać capabilities???
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glfwSwapBuffers(window); // swap the color buffers
  }

  private void update(){
      glfwPollEvents();
      if(Input.keys[GLFW_KEY_SPACE]){
        System.out.println("Space");
      }
  }


  public static void main(String[] args){
    new Main().start();
  }
  

}

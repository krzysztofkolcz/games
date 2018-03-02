package majoloowip.engine;

import java.awt.event.KeyEvent;

public class GameContainer implements Runnable{

    private Thread thread;

    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;
    private final double NANOSECONDS_IN_SECOND = 1000000000.0;


    private int width = 800, height = 600;
    private float scale = 1.0f;
    private String title = "My window";

    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;


    public GameContainer(AbstractGame game){
        this.game = game;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void start(){
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        thread = new Thread(this);
        thread.run();
    }

    public void stop(){

    }

    public Window getWindow() {
        return window;
    }

    public void run(){
        boolean render = false;
        running = true;
        double firstTime = 0;
        double lastTime = System.nanoTime() / NANOSECONDS_IN_SECOND;
        double passedTime =0;
        double unprocessedTime = 0;
        double frameTime = 0;
        int frames = 0;
        int fps = 0;
        while (running){
            render = false;
            firstTime = System.nanoTime() / NANOSECONDS_IN_SECOND;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                render = true;
                game.update(this,(float)UPDATE_CAP);
                /*
                if(input.isKeyDown(KeyEvent.VK_A)){
                    System.out.println("A pressed");
                }
                */
                input.update();

                if(frameTime >= 1.0){
                    frameTime -= 1.0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS:"+fps);
                }
            }


            if(render){
                renderer.clear();
                game.render(this,renderer);
                renderer.process();
                renderer.drawText("FPS:"+fps,10,10,0xffaa0099);
                frames++;
                window.update();
            }else{
                try {
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    public Input getInput() {
        return input;
    }

    private void dispose(){

    }

}

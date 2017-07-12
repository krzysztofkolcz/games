
mvn clean compile assembly:single


http://wiki.lwjgl.org/wiki/Setting_Up_LWJGL_with_Maven.html

export MAVEN_OPTS=-Djava.library.path=target/natives

mvn compile exec:java -Dexec.mainClass=com.base.engine.MainComponent


MainComponent.run
double FRAME_CAP = 5000.0;//how many updates per second
double frameTime = 1.0/FRAME_CAP;//the amount of time one frame takes; 1/5000 => 2/10000 => 0.0002



long frameCounter = 0;
int frames = 0;
long startTime = Time.getTime();
long passedTime = startTime - lastTime;//time - how long the previous frame took
long lastTime = Time.getTime();//time when previous frame start to draw, let say 8561
double unprocessedTime = 0;


startTime:4889624859958

mv ./src/com/* to ./src/main/java/com/*
add pom.xml

maven natives plugin:
https://code.google.com/archive/p/mavennatives/
http://forum.lwjgl.org/index.php?topic=6398.0


https://www.lwjgl.org/download

export MAVEN_OPTS=-Djava.library.path=target/natives
mvn clean compile assembly:single
error - ściąga natives do target/natives

https://github.com/LWJGL/lwjgl3/issues/100

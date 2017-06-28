
mvn clean compile assembly:single


http://wiki.lwjgl.org/wiki/Setting_Up_LWJGL_with_Maven.html

export MAVEN_OPTS=-Djava.library.path=target/natives

mvn compile exec:java -Dexec.mainClass=com.base.engine.MainComponent

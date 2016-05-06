PREREQUISITES:
1. JDK 8
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Tomcat 8
https://tomcat.apache.org/download-80.cgi
http://www3.ntu.edu.sg/home/ehchua/programming/howto/tomcat_howto.html
3. Eclipse IDE for Java EE Developers (Mars)
https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/mars/1/eclipse-jee-mars-1-win32-x86_64.zip

PROJECT SETUP:
1. Open Eclipse
2. Go to File > Import > General > Existing Projects into Workspace > Next
3. Browse to the location of the project files > Finish

BUILD
1. On Eclipse, right-click on the project name > Run as > Maven build and check for errors in Markers view panel

DEPLOY
1. On Eclipse, go to Window > Show View > Servers
2. On Server view panel, if there are no servers listed, click the link to add a new server
3. Set the server type to Apache > Tomcat v8.0 Server
4. If there are no Server Runtime Environments listed, click Add and set the location of the Tomcat 8 installation directory
5. Click Next
6. Add JavaHris as a new resource to the server. Click Finish
7. In the Servers view panel, right-click the Tomcat 8 instance and click Start/Restart to redeploy the contents. Note that there will be text displayed in the Console view panel while starting the server. 
8. Near the end, there will be a log similar to
	Starting ProtocolHandler ["http-nio-8082"]
For the above case, the server is instantiated at port 8082

RUN
1. Go to http://localhost:<server port>/JavaHris


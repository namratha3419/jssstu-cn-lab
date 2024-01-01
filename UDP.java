//server program
import java.net.*; 
public class UDPServer { 
public static void main(String args[]) throws Exception { 
DatagramSocket serverSocket=null; 
try{ 
serverSocket = new DatagramSocket(9884); 
System.out.println("Server is Ready for the client"); 
byte[] receiveData = new byte[1024]; 
byte[] sendData = new byte[1024]; 
while (true) { 
DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
serverSocket.receive(receivePacket); 
String sentence = new String(receivePacket.getData()); 
System.out.println("RECEIVED: " + sentence); 
InetAddress IPAddress = receivePacket.getAddress(); 
int port = receivePacket.getPort(); 
String capitalizedSentence = sentence.toUpperCase(); 
sendData = capitalizedSentence.getBytes(); 
DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
serverSocket.send(sendPacket); 
} 
}catch (Exception e) { 
e.printStackTrace(); 
}finally{ 
serverSocket.close(); 
}
}
}
  
//client program
import java.io.*; 
import java.net.*; 
public class UDPClient 
{ 
public static void main(String[] args) throws Exception 
{ 
BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in)); 
DatagramSocket clientSocket=new DatagramSocket(); 
InetAddress IPAddress=InetAddress.getByName("localhost"); 
byte[] sendData = new byte[1024]; 
byte[] receiveData=new byte[1024]; 
System.out.println("Enter the string in lowercase so that you receive the message in Uppercase from the server"); 
String sentence=inFromUser.readLine(); 
sendData=sentence.getBytes(); 
DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9884); 
clientSocket.send(sendPacket); 
DatagramPacket receivePacket=new DatagramPacket(receiveData, receiveData.length); 
clientSocket.receive(receivePacket); 
String modifiedSentence=new String(receivePacket.getData()); 
System.out.println("FROM SERVER: "+modifiedSentence); 
clientSocket.close(); 
} 
}

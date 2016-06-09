package com.stl.chat.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/*
 * A chat server that delivers public and private messages.
 */
public class MultiThreadChatServerSync {

  // The server socket.
  private static ServerSocket serverSocket = null;
  // The client socket.
  private static Socket clientSocket = null;
  
  

  // This chat server can accept up to maxClientsCount clients' connections.
  private static final int maxClientsCount = 10;
  private static final ClientThread[] threads = new ClientThread[maxClientsCount];
  
  //String url = "http://www.google.com/search?q=mkyong";
//Global message
  public static String globalMsg = "" ;
	


  public static void main(String args[]) {

    // The default port number.
    int portNumber = 124;
    if (args.length < 1) {
      System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"+ "Now using port number=" + portNumber);
    } else {
      portNumber = Integer.valueOf(args[0]).intValue();
    }

    /*
     * Open a server socket on the portNumber (default 2222). Note that we can
     * not choose a port less than 1023 if we are not privileged users (root).
     */
    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    /*
     * Create a client socket for each connection and pass it to a new client
     * thread.
     */
    while (true) {
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxClientsCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new ClientThread(clientSocket, threads)).start();
            break;
          }
        }
        if (i == maxClientsCount) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("Server too busy. Try later.");
          ServerUtil.sendToWeb("Server too busy. Try later.");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}
/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
class ClientThread extends Thread {

  private String clientName = null;
  private DataInputStream is = null;
  private PrintStream os = null;
  private Socket clientSocket = null;
  private final ClientThread[] threads;
  private int maxClientsCount;


  
  public ClientThread(Socket clientSocket, ClientThread[] threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    maxClientsCount = threads.length;
  }

  public void run() {
    int maxClientsCount = this.maxClientsCount;
    ClientThread[] threads = this.threads;

    try {
      /*
       * Create input and output streams for this client.
       */
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      /*String name;
      while (true) {
        os.println("Enter your name.");
        name = is.readLine().trim();
        if (name.indexOf('@') == -1) {
          break;
        } else {
          os.println("The name should not contain '@' character.");
        }
      }*/

      /* Welcome the new the client. */
     // os.println("Welcome " + name + " to our chat room.\nTo leave enter /quit in a new line.");
      synchronized (this) {
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null && threads[i] == this) {
            //clientName = "@" + name;
        	  clientName = threads[i].clientSocket.getRemoteSocketAddress()+"";
            break;
          }
        }
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null && threads[i] != this) {
            //threads[i].os.println("*** A new user " + name + " entered the chat room !!! ***");
        	  threads[i].os.println("A new connection from  " + threads[i].clientName);
        	  ServerUtil.sendToWeb("A new connection from  " + threads[i].clientName);
          }
        }
      }
      /* Start the conversation. */
      while (true) {
        String line = is.readLine();
        /*if(line.endsWith("#")){
        	globalMsg = line.substring(0,line.length()-1);
        }*/
        if (line.startsWith("/quit")) {
          break;
        }
        /* If the message is private sent it to the given client. */
        /*if (line.startsWith("@")) {
          String[] words = line.split("\\s", 2);
          if (words.length > 1 && words[1] != null) {
            words[1] = words[1].trim();
            if (!words[1].isEmpty()) {
              synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                  if (threads[i] != null && threads[i] != this
                      && threads[i].clientName != null
                      && threads[i].clientName.equals(words[0])) {
                    threads[i].os.println("<" + name + "> " + words[1]);
                    
                     * Echo this message to let the client know the private
                     * message was sent.
                     
                    this.os.println(">" + name + "> " + words[1]);
                    break;
                  }
                }
              }
            }
          }
        }*/ else {
          /* The message is public, broadcast it to all other clients. */
          synchronized (this) {
            for (int i = 0; i < maxClientsCount; i++) {
              if (threads[i] != null && threads[i].clientName != null) {
                //threads[i].os.println("<" + name + "> " + line);
            	  if(MultiThreadChatServerSync.globalMsg!=""){//message from server browser broadcast to other clients
            		    threads[i].os.println(threads[i].clientName + " " + MultiThreadChatServerSync.globalMsg);
          	  		    ServerUtil.sendToWeb(threads[i].clientName + " " + MultiThreadChatServerSync.globalMsg);
            	  }else{//message from server by keyboard input
            		    threads[i].os.println(threads[i].clientName + " " + line);
            	  		ServerUtil.sendToWeb(threads[i].clientName + " " + line);
            	  }
              }
            }//end for loop
            if(MultiThreadChatServerSync.globalMsg != ""){
            	MultiThreadChatServerSync.globalMsg = "" ;//Reseting global message
            	break ;
            }
          }
        }
      }//End while loop
      synchronized (this) {
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null && threads[i] != this && threads[i].clientName != null) {
            //threads[i].os.println("*** The user " + name + " is leaving the chat room !!! ***");
        	  threads[i].os.println("Connection Lost to " + threads[i].clientName);
        	  ServerUtil.sendToWeb("Connection Lost to " + threads[i].clientName);
          }
        }
      }
      //os.println("*** Bye " + name + " ***");
      os.println("Bye " + clientName);
      //ServerUtil.sendToWeb("Server Says: Bye " + clientName);

      /*
       * Clean up. Set the current thread variable to null so that a new client
       * could be accepted by the server.
       */
      synchronized (this) {
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] == this) {
            threads[i] = null;
          }
        }
      }
      /*
       * Close the output stream, close the input stream, close the socket.
       */
      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
    }
  }

	
}
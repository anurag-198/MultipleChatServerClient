/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;
import static chatapplication.encrypti.decrypt;
import static chatapplication.encrypti.encrypt;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;    
import java.security.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec; 


/**
 *
 * @author shadowwalker
 */
public class ChatApplication {
    static ServerSocket myServerSocket = null;
    static Socket mySocket = null;
    static BufferedReader cin = null;
    static PrintStream cout = null;
    static BufferedReader stdin = null;
    static serverUI su = null;
    static int max = 10;
    private static final MiniServer[] threads = new MiniServer[max];

    
    public static void main(String[] args) throws Exception {
    try {
      /*  su = new serverUI();
        su.setVisible(true);
        */
        ServerSocket myServerSocket = new ServerSocket(3048);  
        boolean listeningSocket = true;
        while (listeningSocket) {
            
           Socket clientSocket = myServerSocket.accept();
           int i = 0;
           for (i = 0; i < max; i++) {
            if (threads[i] == null) {
                (threads[i] = new MiniServer(clientSocket, threads)).start();
                break;
            }
        //   MiniServer mini = new MiniServer(clientSocket, su);
         //  mini.start();
           }
           if (i == max) {
                clientSocket.close();
            }
        }
        
        }catch(Exception e) {
            System.out.println(e);
        }
        
 	cin.close();
	cout.close();
 	stdin.close();
   
    }
    
}

class MiniServer extends Thread{
    String clientName ="";
    String val = "";
    private Socket socket = null;
     static BufferedReader cin = null;
    PrintStream cout = null;
    static BufferedReader stdin = null;
    String msg;
    serverUI s = null;
    private final MiniServer[] threads;
    int max;
    
    public MiniServer(Socket socket, MiniServer[] threads) {
       // super("MiniServer");
        this.socket = socket;
        this.threads = threads;
        max = threads.length;
    }

    public void run(){
        Map <String, String> mp = new HashMap <String, String> ();
        mp.put ("Anurag","111");
        mp.put ("Shaiwal","111");
        mp.put ("Priyank","111");
        mp.put ("Saptak","111");
        mp.put ("Vivek","111");
        mp.put("Tanushree", "111");
        mp.put("Nikita","111");
        
        Scanner sc = null;
        int max = this.max;
        MiniServer[] threads = this.threads;
        
        try {
             sc = new Scanner(socket.getInputStream());
             cout = new PrintStream(socket.getOutputStream());
             stdin = new BufferedReader(new InputStreamReader(System.in));
             String name = "";
             /***************************encrypt here *******************************/
             String s = "Enter your name and password in above fields ! ";
             
             s = encrypt(s);
             cout.println(s);
             
            // cout.println("Enter your name and password in above fields ! ");
            
             while (true) {
             /**************************decrypt here *******************************/
               
                 
                String re = sc.nextLine();
                re = decrypt(re);
                String[] name1 = re.split(" ");
                
                if (mp.get(name1[0]).equals(name1[1])) {
                    name = name1[0];
                    /*************************encrypt here ***************************/
                
                String s1 = "Successfully logged in !! ";
                s1 = encrypt(s1);
                cout.println(s1);
                    
                    
                  //  cout.println("Successfully logged in !! ");
                    break;
                }
                 else {
                     /*************************encrypt here ***************************/
                    String s1 = "Retry to enter your name and password !!";
                    s1 = encrypt(s1);
                    cout.println(s1);
                    
                    
                  //  cout.println("Retry to enter your name and password !!");
                    continue;
                }
            } 
                          
             synchronized(this) { 
             for (int i = 0; i < max; i++) {
                if (threads[i] != null && threads[i] == this) {
                    clientName = name;
                    break;
                }
             }
                 
             for (int i = 0; i < max; i++) {
             if (threads[i] != null && threads[i] != this) {
                  /*************************encrypt here ***************************/
                    
                    String s1 = name + " has joined us !!";
                    s1 = encrypt(s1);
                    threads[i].cout.println(s1);
                 
                 
                   // threads[i].cout.println(name + " has joined us !!");
             }
             }
            }
            
            while(true) {
                
                /************decrypt here *************************/
                
                String msg = sc.nextLine();
                msg = decrypt(msg);
                if (msg.contains("bye"))
                    break;
                synchronized(this) {
                    for (int i = 0; i < max; i++) {
                        if ((threads[i] != null ) && (threads[i] != this)) {
                         /*************************encrypt here ***************************/
                            
                          String s1 = name + " : " + msg;
                          s1 = encrypt(s1);
                          threads[i].cout.println(s1);
                            
                            
                            
                            
                          //  threads[i].cout.println(name + " : " + msg);
                        }
                    }
                }
            }        
               // cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
                    
                // msg = cin.readLine();
               /*     System.out.println("Client : " + s);
                    System.out.println("Server : ");
                    msg = stdin.readLine();
                    cout.println(msg);
                }*/
                synchronized(this) {
                    for (int z = 0; z < max; z++) {
                        if (threads[z] == this) {
                        threads[z] = null;
                        }
                    }
                }
            
                     //Read input and process here
        } catch (IOException ex) {
            Logger.getLogger(MiniServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MiniServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sc.close();
                cout.close();
                socket.close();
                
                stdin.close();
            } catch (IOException ex) {
                Logger.getLogger(MiniServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
            //implement your methods here

}


  

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singlechatapplication;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shadowwalker
 */
public class Client extends Thread implements Runnable{
    static Socket mySocket = null;
    static DataOutputStream dt = null;
    static DataInputStream di = null;
    static Scanner sc = null;
    static boolean flag = false;
    static BufferedReader sin   =  null;
    static PrintStream sout = null;
    
    public static void main(String[] args) {
        try {
            
            mySocket = new Socket("172.20.33.231",3044);
            BufferedReader sin=new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            PrintStream sout=new PrintStream(mySocket.getOutputStream());
            di = new DataInputStream(mySocket.getInputStream());
            BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
	    String s;
         
            try {
              //  String res;
                new Thread(new Client()).start();
              /*  while ((res = sin.readLine()) != null) {
                    System.out.println(res);
                        if (res.indexOf("*** Bye") != -1)
                             break;
      
                         flag = true;
                }
               */
                while (!flag) {
                   s=stdin.readLine();
                   
		   sout.println(s);
                }
           
            
            } catch(Exception e) {
                System.out.println(e);
            }
            
            
            mySocket.close();
            sin.close();
       	    sout.close();
 	    stdin.close();
            
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void run () {
        BufferedReader sin = null;        
        try {
            String res;
            sin = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            try {
                while ((res = sin.readLine()) != null) {
                    res = "SERVER : " + res;
                    System.out.println(res);
                    if (res.indexOf("*** Bye") != -1)
                        break;
                }
                flag = true;
            }
            catch (IOException e) {
                
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                sin.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}

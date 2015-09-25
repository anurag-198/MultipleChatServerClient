/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package singlechatapplication;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author shadowwalker
 */
public class SingleChatApplication extends Thread {
    static ServerSocket myServerSocket = null;
    static Socket mySocket = null;
    static BufferedReader cin = null;
    static PrintStream cout = null;
    static BufferedReader stdin = null;
    static boolean flag = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            myServerSocket = new ServerSocket(3044);  
            mySocket = myServerSocket.accept();
            boolean listeningSocket = true;
            Scanner sc = new Scanner(mySocket.getInputStream());
            cout = new PrintStream(mySocket.getOutputStream());
            stdin = new BufferedReader(new InputStreamReader(System.in));
            
            new Thread(new SingleChatApplication()).start();
            String s;
            while(!flag) {
                 s=stdin.readLine();
                 
		 cout.println(s);
            }
          
        } catch(IOException e) {
            System.out.println("e");
        }
    }
    
    public void run() {
        BufferedReader sin = null;        
        try {
            String res;
            sin = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            try {
                while ((res = sin.readLine()) != null) {
                    res = "CLIENT : " + res;
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
            Logger.getLogger(SingleChatApplication.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                sin.close();
            } catch (IOException ex) {
                Logger.getLogger(SingleChatApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

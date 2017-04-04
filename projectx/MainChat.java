/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chandu Mannem
 */
public class MainChat {
    static Socket socket;
    static JFXListView<String> listView;
    static DataInputStream fromFriend;
    static DataOutputStream toFriend;
   
    MainChat(Socket socket){
        MainChat.socket = socket;
        MainChat.fromFriend = null;
        MainChat.toFriend = null;
        keepCheck();
    }
    
    public static void keepCheck()
    {
        try {
            MainChat.fromFriend = new DataInputStream(MainChat.socket.getInputStream());
            System.out.println("keepAlive");
            while(true)
            {
               String s = MainChat.fromFriend.readUTF();
               MainChat.listView.getItems().add(s);
               MainChat.listView.setEditable(false);
               
            }
        } catch (IOException ex) {
            Logger.getLogger(MainChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void sendMessage(JFXTextArea textArea)
    {
        try {
            System.out.println("sendMessage");
            MainChat.toFriend = new DataOutputStream(MainChat.socket.getOutputStream());
            MainChat.toFriend.writeUTF(textArea.getText());
            MainChat.toFriend.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(MainChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

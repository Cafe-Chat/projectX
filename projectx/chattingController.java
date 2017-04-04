/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chandu Mannem
 */
public class chattingController implements Initializable {

    @FXML
    private AnchorPane chattingPane;
    @FXML
    private ImageView sendImage;
    @FXML
    private JFXListView<String> listView;
    @FXML
    private JFXTextField chatField;
    
    private AnchorPane pane;
    private DataInputStream fromFriend;
    private DataOutputStream toFriend;

    private Socket socket;
    private Thread readingThread;
    @FXML
    private ImageView share;
    @FXML
    private ImageView call;
    
    private JFXSnackbar snackBar;

    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.socket = SocketClass.socket;
        try {
            fromFriend = new DataInputStream(this.socket.getInputStream());
            toFriend = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(chattingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(()->{

        while(true)
        {
            
            try {
                System.out.println("Child Thread");
                String s = fromFriend.readUTF();
                System.out.println(s);
                if("ch1234anDuFile".equals(s))
                {
                    System.out.println("About ro start");
                    new FileRecieve(this.socket).FileToRecieve();
                    
                    snackBar = new JFXSnackbar(chattingPane);
                    snackBar.setPrefSize(300, 30);
                    snackBar.show("          FILE RECEIVED SUCCESSFUL",3000);
                }
                else if(!s.equals(""))
                {
                    System.out.println(s);
                    Platform.runLater(()->{
                        String s1 = "Friend: " + s;
                        listView.getItems().add(s1);
                    });
                }
                
            } catch (IOException ex) {
                Logger.getLogger(chattingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       }).start();
    }    

    @FXML
    private void onBack1(MouseEvent event) throws IOException {

        pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
        chattingPane.getChildren().setAll(pane);

    }

    @FXML
    private void onEnter(KeyEvent event) {
        
        if(event.getCode() == KeyCode.ENTER)
        {
            System.out.println("Enter Key Pressed");
            onLoad();

        }
    }

    @FXML
    private void onClick(MouseEvent event) {
        onLoad();
    }
    
    public void onLoad()
    {
        System.out.println(this.socket);
       if(this.socket.isConnected())
       {
           String s2 = chatField.getText();

           try {
               toFriend.writeUTF(s2);
               toFriend.flush();
               s2 = "Me: "+s2;
               listView.getItems().add(s2);
               chatField.setText("");
           } catch (IOException ex) {
               Logger.getLogger(chattingController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
           
       }
       else
       {
           System.out.println("Socket Lost");
       }
    }

    @FXML
    private void onShare(MouseEvent event) {
        
       String s = new FileTransfer(this.socket).FileToTransfer();
 
       try {
               toFriend.writeUTF(s);
               toFriend.flush();
               s = "Me: "+s;
               listView.getItems().add(s);
        } catch (IOException ex) {
               Logger.getLogger(chattingController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       snackBar = new JFXSnackbar(pane);
       snackBar.setPrefSize(300, 30);
       snackBar.show("          FILE SENDED SUCCESSFUL",3000);
        
    }

    @FXML
    private void onCall(MouseEvent event) {
    }
    
}

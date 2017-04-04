/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chandu Mannem
 */
public class connectedController implements Initializable {

    @FXML
    private AnchorPane connectedPane;
    @FXML
    private Label connTitle;
    @FXML
    private Label friendName;
    @FXML
    private Label friendIp;
    
    private AnchorPane pane;
    private Socket socket;

    /**
     * Initializes the controller class.
     */
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
        this.socket = SocketClass.socket;
        System.out.println("In intialization:"+this.socket);
        InetAddress ip = this.socket.getInetAddress();
        friendName.setText(ip.getHostName());
        friendIp.setText(ip.getHostAddress());
        String c = ip.getHostName().toUpperCase().substring(0, 1);
        connTitle.setText(c);
          
    }    

    @FXML
    private void onBack(MouseEvent event) throws IOException {
        
        pane = FXMLLoader.load(getClass().getResource("frontpage.fxml"));
        connectedPane.getChildren().setAll(pane);
    }

    @FXML
    private void onChat(ActionEvent event) throws IOException {
        
        pane = FXMLLoader.load(getClass().getResource("ChattingModule.fxml"));
        connectedPane.getChildren().setAll(pane);
    }

    @FXML
    private void onCall(ActionEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("CallingAndVideoModule.fxml"));
        connectedPane.getChildren().setAll(pane);
    }

    @FXML
    private void onVideoCall(ActionEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("CallingAndVideoModule.fxml"));
        connectedPane.getChildren().setAll(pane);
    }

    @FXML
    private void onShare(ActionEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("SharingModule.fxml"));
        connectedPane.getChildren().setAll(pane);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author chandu Mannem
 */
public class frontPageController implements Initializable {

    @FXML
    private AnchorPane frontPane;
    @FXML
    private Label myIp;
    @FXML
    private Label myName;
    @FXML
    private JFXTextField ipField;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private Label validText;
    
    private AnchorPane pane;
    private Socket socket;
    private JFXSnackbar snackBar;
    private int count;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //****Logic for Getting Wifi Adapter Ip for your Computer
        Enumeration<NetworkInterface> e;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        
            for(NetworkInterface networkInterface : Collections.list(e))
            {
                String s = networkInterface.getDisplayName();
                if(s.contains("Wi-Fi Adapter") || "wlan0".equals(networkInterface.getName()) || s.contains("Wireless"))
                {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                        myIp.setText(inetAddress.toString().substring(1));
                        break;
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(frontPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //**Logic for Getting Host Name of our PC
        try {
            myName.setText(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(frontPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    
    @FXML
    private void onClick(MouseEvent event) throws IOException {
        onLoading();
    }
    
    @FXML
    private void onEnter(KeyEvent event) throws IOException {

        if(event.getCode() == KeyCode.ENTER)
        {
            onLoading();
        }
    }
    
    @FXML
    private void onLogOut(MouseEvent event) throws IOException
    {
        pane = FXMLLoader.load(getClass().getResource("Projectx.fxml"));
        frontPane.getChildren().setAll(pane);
        
        snackBar = new JFXSnackbar(pane);
        snackBar.show("LogOut Successful",3000);
    }
   
    private void onLoading() throws IOException
    {
        boolean flag = onValidateField(ipField.getText());
        
        if(flag == false)
        {
            ipField.setText("");
            ipField.setFocusColor(Color.RED);
            validText.setVisible(true);
        }
        else
        {
            spinner.setVisible(true);
            validText.setVisible(false);
            ipField.setFocusColor(Color.BLACK);
            
            this.socket = new Socket(ipField.getText(),3333);
            count = 0;
            while(count == 0)
            {
                if(this.socket.isConnected())
                {
                    
                    count = 1;
                }
                System.out.println(count);
            }
            
            if(this.socket.isConnected())
            {  
                System.out.println("socket Connected");
                ipField.setText("");
                spinner.setVisible(false);
                
                System.out.println("Int frontPageController :"+socket);
                SocketClass.socket = this.socket;
                
                pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
                frontPane.getChildren().setAll(pane);
                
                snackBar = new JFXSnackbar(pane);
                snackBar.setPrefSize(300, 30);
                snackBar.show("          Congrats You Are Connected !!",3000);
                
            }
        }
    }
    
    private boolean onValidateField(String ipString)
    {

        if(ipString == null || ipString.isEmpty())
        {
            return false;
        }
        String[] parts = ipString.split("\\.");
        //System.out.println(parts.length);
        /*if(parts.length == 1)
        {
            return !ipString.endsWith(".");
        }*/
        if(4 != parts.length)
        {
           return false; 
        }
        
        for(String s : parts)
        {
            int i = Integer.parseInt(s);
            if(i<1 || i>255)
            {
                return false;
            }
        }
        return !ipString.endsWith(".");
    }
   
}

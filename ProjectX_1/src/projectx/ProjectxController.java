/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author chandu Mannem
 */
public class ProjectxController implements Initializable {

    @FXML
    private AnchorPane LandingPane;
    @FXML
    private JFXPasswordField passField;
    @FXML
    private JFXButton login;
    @FXML
    private AnchorPane frontPane;
    @FXML
    private AnchorPane connectedPane;
    @FXML
    private AnchorPane callingPane;
    @FXML
    private AnchorPane chattingPane;
    @FXML
    private JFXCheckBox checkBox;
    @FXML
    private Label validation;
    @FXML
    private ImageView sendImage;
    @FXML
    private AnchorPane sharePane;
    @FXML
    private Label agreeLabel;
    
    private FadeTransition fadeIn;
    private AnchorPane pane;
    private JFXSnackbar snackBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLoad(ActionEvent event) throws IOException{
        
        
        if("cafe".equals(passField.getText()) && checkBox.isSelected())
        {
           pane = FXMLLoader.load(getClass().getResource("frontpage.fxml")); 
           LandingPane.getChildren().setAll(pane);
            
           fadeIn = new FadeTransition(Duration.seconds(4),pane);
           fadein();
        }
        else if(!"cafe".equals(passField.getText()))
        {
            agreeLabel.setText("");
            validation.setText("Invalid LogIn");
        }
        else
        {
            validation.setText("");
            agreeLabel.setText(" Plz Agree our Terms");
        }
    }
    
    @FXML
    private void fileSend(ActionEvent event) throws IOException{
       
        snackBar = new JFXSnackbar(sharePane);
        snackBar.show("File Send Successful !!",3000);
    }
    
    @FXML
    private void onClick(MouseEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
        frontPane.getChildren().setAll(pane);

        snackBar = new JFXSnackbar(pane);
        snackBar.setPrefSize(300, 30);
        snackBar.show("          Congrats You Are Connected !!",3000);
        
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
    
    @FXML
    private void onBack1(MouseEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
        chattingPane.getChildren().setAll(pane);

    }
    
    @FXML
    private void onBack2(MouseEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
        callingPane.getChildren().setAll(pane);

    }
    
    @FXML
    private void onFileBack(MouseEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
        sharePane.getChildren().setAll(pane);

    }
    
    private void fadein(){
        fadeIn.setFromValue(0.15);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

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
import javafx.scene.layout.AnchorPane;
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
    private JFXCheckBox checkBox;
    @FXML
    private Label validation;
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
           fadeIn.setFromValue(0.15);
           fadeIn.setToValue(1);
           fadeIn.setCycleCount(1);
           fadeIn.play();
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
}

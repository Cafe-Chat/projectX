/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import com.jfoenix.controls.JFXSnackbar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chandu Mannem
 */
public class sharingController implements Initializable {

    @FXML
    private AnchorPane sharePane;
    
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
    private void onFileBack(MouseEvent event) throws IOException {
        pane = FXMLLoader.load(getClass().getResource("ConnectedPage.fxml"));
        sharePane.getChildren().setAll(pane);
    }

    @FXML
    private void fileSend(ActionEvent event) {
        
        snackBar = new JFXSnackbar(sharePane);
        snackBar.show("File Send Successful !!",3000);
    }
    
}

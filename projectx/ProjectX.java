/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author chandu Mannem
 */
public class ProjectX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Projectx.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        new Thread(()->{
           int i = 0;
            try {
                ServerSocket serverSocket = new ServerSocket(3333);
                Socket socket = serverSocket.accept();
                System.out.println("3");
                SocketClass.socket = socket; 
                
            } catch (IOException ex) {
                Logger.getLogger(ProjectX.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }).start();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

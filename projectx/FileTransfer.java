/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author chandu Mannem
 */
public class FileTransfer {
    public Socket socket;
    private BufferedInputStream toFriend;
    private OutputStream outputStream;
    private String FILE_NAME;
    
    
    FileTransfer(Socket socket)
    {
        this.socket = socket;
        FILE_NAME = null;
    }
    
    public String FileToTransfer()
    {
        if(this.socket.isConnected())
        {
            try {
                FileChooser fc = new FileChooser();
                fc.setTitle("Cafe Chat -- File Sharing");
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("ONLY TEXT_JPG_PNG_PDF_DOC", "*.jpg","*.png","*.pdf","*.doc","*.txt"));
                File FILE_TO_TRANSFER = fc.showOpenDialog(null);
                FILE_NAME = FILE_TO_TRANSFER.getName();
            
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_TO_TRANSFER));
              
                outputStream = this.socket.getOutputStream();
            
                new DataOutputStream(outputStream).writeUTF("ch1234anDuFile");                
                new DataOutputStream(outputStream).writeUTF(FILE_NAME);
            
                byte[] bytes = new byte[16*1024];
                int count;
                while ((count = bis.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, count);
                }
                
                outputStream.flush();
                
                bis.close();
                
             
            } catch (FileNotFoundException ex) {
                 Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                 Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return FILE_NAME;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chandu Mannem
 */
public class FileRecieve {
    
    public Socket socket;
    
    FileRecieve(Socket socket)
    {
        this.socket = socket;
    }
    
    public void FileToRecieve()
    {
        try {
            System.out.println("In FILE RECIVE MODULE");
            String FILE_NAME = new DataInputStream(this.socket.getInputStream()).readUTF();
            System.out.println(FILE_NAME);
                        
            InputStream inputStream = this.socket.getInputStream();
   
            String FILE_PATH = System.getProperty("user.home");
            File f = new File(FILE_PATH,"Desktop");
            f = new File(f.getPath(),"CafeChat");
            if(!f.isDirectory())
            {
                f.mkdir();
            }
            f = new File(f.getPath(),FILE_NAME);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f.getAbsoluteFile()));
            
            
            byte[] bytes = new byte[16*1024];
            int count;
            while ((count = inputStream.read(bytes)) > 0) {
                bos.write(bytes, 0, count);
            }
            
            
            System.out.println("File Recieved Success");
            
            bos.close();
           } catch (IOException ex) {
            Logger.getLogger(FileRecieve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

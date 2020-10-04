/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package good.panda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fahim
 */
public class Admin {
  
    
    //File Creations & Data
    
    String Username, Password, Email;
    
    File file = new File("S:\\Users\\RegData");
    int ln;
    
    void createFolder(){
        if(!file.exists()){
            file.mkdirs();
        }
    }
    
    void readFile(){
        
        try {
            FileReader fr = new FileReader(file+"\\admin.txt");
            System.out.println("file exists!");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fw = new FileWriter(file+"\\admin.txt");
                System.out.println("File created");
            } catch (IOException ex1) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    
    void CheckData(String usr, String pswd) throws IOException{
        
        try {
            RandomAccessFile raf = new RandomAccessFile(file+"\\admin.txt","rw");
            
            String line;
           
            line = raf.readLine();
            
            Username = line.substring(9);
            Password = raf.readLine().substring(9);
            Email = raf.readLine().substring(6);
            if(usr.equals(Username)& pswd.equals(Password)){
                JOptionPane.showMessageDialog(null, "Password matched");
            }else{
                JOptionPane.showMessageDialog(null, "Wrong user/Password");
            }
            
            
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    void logic(String usr, String pswd) throws IOException{
        
        try {
            RandomAccessFile raf = new RandomAccessFile(file+"\\admin.txt","rw");
              
            for (int i = 0; i < ln; i+=4) {
                
                System.out.println("count "+i);
                
                String forUser = raf.readLine().substring(9);
                String forPswd = raf.readLine().substring(9);
                
                if(usr.equals(forUser) && pswd.equals(forPswd)){
                    
                    Login log = new Login();
                    log.dispose();
                    AdminDash dash = new AdminDash();
                    dash.setVisible(true);
                    break;
                }
                
                else if(i==(ln-3)){
                    JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
                    break;
                }
                
                for (int j = 1; j <=2; j++) {
                    
                    raf.readLine();
                    
                }
            }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//Logic Ends
    
    
    void countLines(){
        
        try{
            
            ln = 0;
            RandomAccessFile raf = new RandomAccessFile(file+"\\admin.txt","rw");
            for (int i = 0; raf.readLine()!=null; i++) {
                ln++;
            }
            
        }catch(IOException e){
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    
}

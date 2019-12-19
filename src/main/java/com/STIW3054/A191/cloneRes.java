/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WANG LUO
 */
public class CloneRes extends Thread{
//    public static void main(String[] args) throws IOException{
//        String command ="git clone https://github.com/WwLuo-1024/251230-STIW3054-A191-A1";
//        Process p = Runtime.getRuntime().exec(command);
//        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        
//        }
    
    public boolean mkDirectory(String path){
        File file = null;
        try{
            file = new File(path);
            if(!file.exists()){
                return file.mkdirs();
            }
            else{
                return false;
            }
        }catch(Exception e){           
        }finally{
            file = null;
        }
        return false;
    }
    
    @Override
    public void run(){
        GetLink gl = new GetLink();
        List<String> urlList=new ArrayList<String>();
        urlList.addAll(gl.getLinksByJsoup());
        for(int i = 0;i<urlList.size();i++){
            try {
                String full_command;
                String command = "git clone {}";
                full_command = command.replace("{}",urlList.get(i));
                
                Process p = Runtime.getRuntime().exec(full_command,null,new File("D:\\realDemo"));
            } catch (IOException ex) {
                Logger.getLogger(CloneRes.class.getName()).log(Level.SEVERE, null, ex);
            }
            } 
    }
    
    public void Clone() throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
        try {                   
            Thread.sleep(1000);
            CloneRes a = new CloneRes();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
        System.out.println("The main thread is performing tasks");
         
        try {
            System.out.println("There are "+result.get()+" files need to be downloaded for this task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }         
        System.out.println("All repositories have been successfully cloned ");
    }
}
class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("The Main Thread Start");
        Thread.sleep(3000);
        String mkDirectoryPath = "D:\\realDemo";
        CloneRes cr = new CloneRes();
        if(cr.mkDirectory(mkDirectoryPath)){
            System.out.println(mkDirectoryPath+" The Direcory have benn sucessfully created");
        }
        else{
            System.out.println(mkDirectoryPath+" The Directory created failed!The Directory have been already existed");
        }
        int resNumber = 0;
        GetLink gl = new GetLink();
        for(int i=0;i<gl.getLinksByJsoup().size();i++)
            resNumber += 1;
        return resNumber;
    }
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.spinellis.ckjm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siti Nur Fatihah Fauzi
 */
public class CompileFile {
    public static List<String> getFolder(String path){
        File file = new File(path);
        File[] array = file.listFiles();
        List<String> folderPathList=new ArrayList<String>();
        for(int i =0;i < array.length;i++){
            if(array[i].isDirectory()){
                String folderPath = array[i].getPath();
                folderPathList.add(folderPath);
            }
        }
        return folderPathList;
    }
    
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
    
    
    public void mkLog(){
        String mkDirectoryPath = "D:\\realDemo\\log";
        CloneRes cr = new CloneRes();
        if(cr.mkDirectory(mkDirectoryPath)){
            System.out.println(mkDirectoryPath+" The Direcory have benn sucessfully created");
        }
        else{
            System.out.println(mkDirectoryPath+" The Directory created failed!The Directory have been already existed");
        }
    }
    public static void CompileProgram(String path) throws InterruptedException{
            try {
//            System.out.println("--"+path);
            System.out.println("=========================");
            String matricNum = path.substring(12, 18); //D:\realDemo\[249879]-STIW3054-A191-A1\
            String Command = "cmd /c   cd "+path+"&& mvn compile > D:\\realDemo\\log\\"+matricNum+".log.txt";
            Process a = Runtime.getRuntime().exec(Command);
            a.waitFor();
            a.destroy();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(CompileFile.class.getName()).log(Level.SEVERE, null, ex);
        } 
  }
    
    public void compile()throws InterruptedException{ //main!!!
        String path = "D:\\realDemo";
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size();i++){
            String folderPath = getFolder(path).get(i);
            FileFinder ff = new FileFinder();
            ff.pomPath(folderPath);
            if(!"".equals(ff.pomPath(folderPath))){
                int begin=ff.pomPath(folderPath).indexOf("D:");
                int last=ff.pomPath(folderPath).indexOf("\\pom");
                pomPathList.add(ff.pomPath(folderPath).substring(begin,last));
            }else{
                System.out.println("Please "+folderPath.substring(12,18)+" upload the full file");
            }          
        }
        mkLog();
            for(int i = 0;i < pomPathList.size();i++){
            CompileProgram(pomPathList.get(i));
        }
    }
}

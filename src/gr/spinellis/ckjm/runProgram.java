/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.spinellis.ckjm;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author WANG LUO
 */
public class runProgram{
    
    public static List<String> getFolder(String path){ //open directory(D:\realDemo) and get get everyone's assignment1 folder path.
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
        
    public static void mvnInstall(String path) throws InterruptedException{ 
        try {
//            System.out.println("--"+path);
            String Command = "cmd /c   cd "+path+"&& mvn install";
            Process a = Runtime.getRuntime().exec(Command);
            a.waitFor(2, TimeUnit.SECONDS);
            a.destroy();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(CompileFile.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
//     public boolean mkDirectory(String path){
//        File file = null;
//        try{
//            file = new File(path);
//            if(!file.exists()){
//                return file.mkdirs();
//            }
//            else{
//                return false;
//            }
//        }catch(Exception e){           
//        }finally{
//            file = null;
//        }
//        return false;
//    }
     
     public void mkOut(){
         String mkDirectoryPath = "D:\\realDemo\\out";
        CloneRes cr = new CloneRes();
        if(cr.mkDirectory(mkDirectoryPath)){
            System.out.println(mkDirectoryPath+" The Direcory have benn sucessfully created");
        }
        else{
            System.out.println(mkDirectoryPath+" The Directory created failed!The Directory have been already existed");
        }
     }
    public static void runJar(String path) throws InterruptedException{
        
        Thread.sleep(2000);
        try{
            String matricNum = null;
            String jarName = null;
            File file = new File(path+"\\target");
            File[] array = file.listFiles();
            for(int i =0;i < array.length;i++){
            if(array[i].isFile()){
                if(array[i].getName().endsWith(".jar")){
                    jarName = array[i].getName();
//                    System.out.println(jarName);
                }
                }
            }
            matricNum = path.substring(12,18); //[255312]-STIW3054-A191-A1
            String Command = "cmd /c   cd "+path+"\\target  && java -jar "+jarName+" > D:\\realDemo\\out\\"+matricNum+".out.txt"; //save to log folder  +" > D:\\realDemo\\out\\"+matricNum+".log.txt"
            Process a = Runtime.getRuntime().exec(Command);        
            a.waitFor(3, TimeUnit.SECONDS);
            a.destroy();
        }catch (IOException ex){
            ex.printStackTrace();
            Logger.getLogger(CompileFile.class.getName()).log(Level.SEVERE,null,ex);
    }
    
 }
    public void runProgram() throws InterruptedException{  //main runProgram()
        String path = "D:\\realDemo";
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size()-1;i++){
            String folderPath = getFolder(path).get(i);
            FileFinder ff = new FileFinder();
            if(!"".equals(ff.pomPath(folderPath))){
                int begin=ff.pomPath(folderPath).indexOf("D:");
                int last=ff.pomPath(folderPath).indexOf("\\pom");
                pomPathList.add(ff.pomPath(folderPath).substring(begin,last));
            }
            else{
                System.out.println("Please "+folderPath.substring(12,18)+" upload the full file");
            }           
        }
        mkOut();
        for(int i = 0;i < pomPathList.size();i++){
//            System.out.println(pomPathList.get(i));
            mvnInstall(pomPathList.get(i));
        }
        for(int i = 0;i < pomPathList.size();i++){
//            System.out.println(pomPathList.get(i));
            runJar(pomPathList.get(i));
        }
    }
}

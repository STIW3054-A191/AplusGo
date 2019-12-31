package gr.spinellis.ckjm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import jxl.read.biff.BiffException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveCkjm {
    /////////
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
    
    public static List<String>getPomPath(){
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
            }
//            else{
//                System.out.println("Please upload the full file");
//            }          
        }
        return pomPathList;
    }
    
    
    public static void getInfo() throws IOException{
    String url = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";    
    Document doc = Jsoup.connect(url).timeout(5000).get();
    Elements elementsTable = doc.select("table").select("tr");
    List<String> info = new ArrayList<String>();
    for (int i = 0; i < elementsTable.size() - 1; i++) {
    Elements tds = elementsTable.get(i+1).select("td");
    String Name = tds.get(2).text();
    String matricNum = tds.get(1).text();
//    System.out.println(matricNum);
//    System.out.println(Name);
    String fullInfo = matricNum + Name;
    info.add(fullInfo);
    }
    String path = "D:\\realDemo\\info.txt";
    	File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String l:info){
                writer.write(l + "\r\n");
            }
        }
   }
    
   
    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }
    
    
    
    
    //////////
    public static void getCkjm(String pomPath) throws IOException, InterruptedException {  //main
        ///////////
//        List<String> pomPathList = new ArrayList<String>();
//        pomPathList.addAll(getPomPath());
        ///////////
        Test2 t = new Test2();
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<ClassMetrics> ref = new AtomicReference<>();
//        System.out.println("----------"+pomPath);
        String matricNum = pomPath.substring(12,18);
        String name = null;
        List<String> stringList = readTxtFileIntoStringArrList("D:\\realDemo\\info.txt");
        for(int i = 0;i < stringList.size();i++){
            if(matricNum.equals(stringList.get(i).substring(0,6))){
                name = stringList.get(i).substring(6);
            }
        }
//        System.out.println(matricNum);
//        System.out.println(name);
        t.MatricName(matricNum, name);
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "WMC", "DIT", "NOC", "CBD", "RFC", "LCOM");
        CkjmOutputHandler outputHandler = new CkjmOutputHandler() {
            int w = 0, d = 0, n = 0, b = 0, r = 0, l = 0;
            Test2 t2 = new Test2();

            @Override
            public void handleClass(String name, ClassMetrics c) {

                //System.out.println("name: " + name + ", WMC: " + c.getWmc() + ", DIT: " + c.getDit() + ", NOC: " + c.getNoc() + ", CBO: " + c.getCbo() + ", RFC: " + c.getRfc() + ", LCOM: " + c.getLcom());
                ref.set(c);
                latch.countDown();
                w += c.getWmc();
                d += c.getDit();
                n += c.getNoc();
                b += c.getCbo();
                r += c.getRfc();
                l += c.getLcom();
                //System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", w, d, n, b, r, l);
                t2.test2(w, d, n, b, r, l);
            }
        };
        File root = new File(pomPath);  // \\target\\classes\\
        try {
            String[] extensions = {"class"};
            Collection files = FileUtils.listFiles(root, extensions, true);
            if(files.isEmpty()){
                t.test2(0,0,0,0,0,0);
            }
            for (Object o : files) {
                File file = (File) o;
                assertTrue("File " + file.getAbsolutePath() + " not present", file.exists());
                MetricsFilter.runMetrics(new String[]{file.getAbsolutePath()}, outputHandler);
                latch.await(1, TimeUnit.SECONDS);
                assertEquals(ref.get().getWmc() >= 0, ref.get().getWmc() >= 0);
                //System.out.println("File = " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.printData();
    }
    
public static void deleteOther(){
    String path = "D:\\realDemo";
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size();i++){
            String filenameZip=getFolder(path).get(i)+"\\target\\dependency-reduced-pom.xml";
            File file = new File(filenameZip);
            if(!file.exists()){
//            System.out.println("文件不存在");
            }
            else{
//            System.out.println("存在文件");
            file.delete();//删除文件
            }       
        }
}


public void runckjm() throws IOException, InterruptedException, FileNotFoundException, BiffException{ //runckjm
        deleteOther();
        getInfo();
        List<String> pomPathList = new ArrayList<String>();
        pomPathList.addAll(getPomPath());
        getCkjm("D:\\realDemo\\243147-STIW3054-A191-Assignment1");
        getCkjm("D:\\realDemo\\249879-STIW3054-A191-A1\\com.assignment");
        for(int i = 2;i < pomPathList.size();i++){
            getCkjm(pomPathList.get(i));
        }
        Test2 t22 = new Test2();
        t22.writeToExcel();
        t22.makeBarChart();
}
}

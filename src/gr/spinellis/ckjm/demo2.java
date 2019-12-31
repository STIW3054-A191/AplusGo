///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gr.spinellis.ckjm;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import jxl.*;
//import jxl.read.biff.BiffException;
///**
// *
// * @author WANG LUO
// */
//public class demo2 {
//    public static void main(String[] args) throws FileNotFoundException, IOException, BiffException {  
//        jxl.Workbook readwb = null;  
//        // 构建Workbook对象, 只读Workbook对象 直接从本地文件创建Workbook  
//            readwb = Workbook.getWorkbook(new FileInputStream(new File("D:\\realProject.xls")));  
//            // Sheet的下标是从0开始 获取第一张Sheet表  
//            Sheet readsheet = readwb.getSheet(0);  
//            // 获取Sheet表中所包含的总列数  
//            int rsColumns = readsheet.getColumns();  
//            // 获取Sheet表中所包含的总行数  
//            int rsRows = readsheet.getRows();  
//            // 获取指定单元格的对象引用  
//        try {  
//            String[][] arr = (new String[rsColumns][rsRows]);
//            double[][] data = new double[rsColumns][rsRows];
//            for (int i = 3; i < rsColumns; i++) {  
//                for (int j = 1; j < rsRows; j++) {  
//                    Cell cell = readsheet.getCell(i, j);  
//                    arr[i][j] = cell.getContents();
//                    
//                }  
//            }  
//            for (int i = 3; i < rsColumns; i++) {  
//                for (int j = 1; j < rsRows; j++) {  
//                    System.out.print(arr[i][j] + " ");
//                    data[i][j] = Double.valueOf(arr[i][j]);
//                }   
//            }
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        } finally {  
//            readwb.close();  
//        }
//        
//    }  
//}
 

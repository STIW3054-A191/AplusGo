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
//
///**
// *
// * @author WANG LUO
// */
//public class demo1 {
//    public static void main(String[]args) throws FileNotFoundException, IOException, BiffException{
//        Sheet sheet;
//        Workbook book;
//        Cell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8;
//
//            //hello.xls为要读取的excel文件名          
//            File file = new File("D:\\realProject.xls");
//            InputStream in = new FileInputStream(file);
//            book= Workbook.getWorkbook(in); 
//            
//            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
//            sheet=book.getSheet(0); 
//            
////            while(true)
////            {
////                //获取每一行的单元格 
////                cell1=sheet.getCell(0,i);//（列，行）
////                cell2=sheet.getCell(1,i);
////                cell3=sheet.getCell(2,i);
////                cell4=sheet.getCell(3,i);
////                cell5=sheet.getCell(4,i);
////                cell6=sheet.getCell(5,i);
////                cell7=sheet.getCell(6,i);
////                if("".equals(cell1.getContents())==true)    //如果读取的数据为空
////                    break;
////                System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+cell4.getContents()
////                		+"\t"+cell5.getContents()+"\t"+cell6.getContents()+"\t"+cell7.getContents()); 
////                i++;
////            }
//            
//            for(int i = 1;i < sheet.getRows();i++){
//                cell1=sheet.getCell(3,i);//（列，行）
//                cell2=sheet.getCell(4,i);
//                cell3=sheet.getCell(5,i);
//                cell4=sheet.getCell(6,i);
//                cell5=sheet.getCell(7,i);
//                cell6=sheet.getCell(8,i);
//                System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+cell4.getContents()
//                		+"\t"+cell5.getContents()+"\t"+cell6.getContents());
//            }
//            book.close(); 
//        }
//}
//


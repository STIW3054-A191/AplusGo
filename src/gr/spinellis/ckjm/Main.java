/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.spinellis.ckjm;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;

/**
 *
 * @author WANG LUO
 */
public class Main {
    public static void main(String[] args){
        long startTime=System.currentTimeMillis();
        CloneRes cr = new CloneRes();
        CompileFile cf = new CompileFile();
        runProgram rp = new runProgram();
        SaveCkjm sc = new SaveCkjm();
        CreatePDF cp = new CreatePDF();
        try {
            cr.Clone();
            System.out.println("Compile Star");
            System.out.println("=========================");
            cf.compile();
            System.out.println("Compile end");
            System.out.println("=========================");
            System.out.println("Run Program Start");
            System.out.println("=========================");
            rp.runProgram();
            System.out.println("Run end");
            System.out.println("=========================");
            sc.runckjm();
            System.out.println("PDF of bar chart is creating");
            cp.mkpdf();
            System.out.println("PDF of bar chart have sucessfully created in D:\\realDemo\\stsckedBar.pdf");
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

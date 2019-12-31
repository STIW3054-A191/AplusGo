/**
 *
 * @author WANG LUO
 */

package gr.spinellis.ckjm;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class CreatePDF {
    public void mkpdf() {
        Document document = new Document();
         document.setPageSize(PageSize.A4.rotate());
        String input = "D:\\realDemo\\stsckedBar.png";
        String output = "D:\\realDemo\\stsckedBar.pdf";
        
        try {
            FileOutputStream fos = new FileOutputStream(output);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            Image img = Image.getInstance(input);
            img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            document.add(img);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

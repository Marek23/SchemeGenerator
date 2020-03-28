package pl.com.hom.fpsdrawer;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfPage; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;

public class Main {     
   public static void main(String args[]) throws Exception {            
      // Creating a PdfWriter       
      String name = "RWG1.pdf";

      PdfWriter writer = new PdfWriter(name);           
      
      // Creating a PdfDocument object       
      PdfDocument pdfDoc = new PdfDocument(writer);                   
      
      // Creating a Document object       
      Document doc = new Document(pdfDoc);   
     
      
      // Creating a new page       
      PdfPage pdfPage = pdfDoc.addNewPage();
      
      
      // Creating a PdfCanvas object       
      PdfCanvas canvas = new PdfCanvas(pdfPage);
      SolidLine l = new SolidLine(10);
      // Initial point of the line
      l.draw(canvas, new Rectangle (0, 100));
      canvas.moveTo(0, 0);
      WmfImageData imageData1 = new WmfImageData("rysunek.wmf");
      PdfFormXObject xObject1 = new PdfFormXObject(imageData1, pdfDoc);
      canvas.addXObject(xObject1, 0, 0);
      canvas.moveTo(5, 5);
      canvas.setColor(Color., fill)
      imageData1 = new WmfImageData("rysunek.wmf");
      xObject1 = new PdfFormXObject(imageData1, pdfDoc);

      System.out.println(pdfPage.getPageSize().getHeight());
      System.out.println(pdfPage.getPageSize().getWidth());
      canvas.addXObject(xObject1, 5, 5);


      // Drawing the line
      
      // Closing the path stroke       
      canvas.closePathStroke();              
      // Closing the document       
      doc.close();  
      
      System.out.println("Object drawn on pdf successfully");             
   }     
}
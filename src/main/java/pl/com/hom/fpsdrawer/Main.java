package pl.com.hom.fpsdrawer;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage; 
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import pl.com.hom.Configuration;
import pl.com.hom.elements.graphics.Contactor;
import pl.com.hom.scheme.Column;
import pl.com.hom.scheme.ColumnLine;

import static pl.com.hom.Document.getPdfDocument;

public class Main {     
	public static void main(String args[]) throws Exception {				
		System.out.println("Start");

		Configuration.initialize();

		Column    column    = new Column(0);
		Contactor contactor = new Contactor();

		column.addElement(contactor);

		column.showSupplierPointsLines();
		System.out.println("---");
		contactor.showPoints();

		column.showLines();
		System.out.println("Stop");

		System.out.println("---");

		PdfPage   pdfPage = getPdfDocument().addNewPage(PageSize.A4.rotate());
		PdfCanvas canvas  = new PdfCanvas(pdfPage);

		for (ColumnLine line :column.getLines()) {
			canvas.setLineWidth(0.5f);
			canvas.setStrokeColorRgb(1f, 0f, 0f);
			canvas.moveTo(line.getBeginWidth(), line.getBeginHeight());
			canvas.lineTo(line.getEndWidth(), line.getEndHeight());
			canvas.stroke(); //IMPORTANT
			canvas.addXObject(contactor.image(), new Rectangle(contactor.getWidth(), contactor.getHeight(),5f,2f));
		}

		
		canvas.closePathStroke();				  		 
		getPdfDocument().close();  
		
		System.out.println("Object drawn on pdf successfully");
   }
}
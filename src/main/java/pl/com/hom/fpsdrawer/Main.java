package pl.com.hom.fpsdrawer;

import com.itextpdf.kernel.geom.Rectangle;

import static pl.com.hom.configuration.Document.getPdfDocument;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage; 
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import pl.com.hom.configuration.Configuration;
import pl.com.hom.configuration.Measures;
import pl.com.hom.elements.graphics.Contactor;
import pl.com.hom.scheme.Column;
import pl.com.hom.scheme.ColumnLine;
import pl.com.hom.scheme.Page;

public class Main {     
	public static void main(String args[]) throws Exception {				
		System.out.println("Start");

		Configuration.initialize();

		Page      page      = new Page();
		Column    column    = new Column(page, 100f);
		Contactor contactor = new Contactor(column);

		column.addElement(contactor);

		column.showSupplierPointsLines();
		System.out.println("---");
		contactor.showPoints();

		System.out.println("--- Lines ---");
		column.showLines();

		PdfPage   pdfPage = getPdfDocument().addNewPage(PageSize.A4.rotate());
		PdfCanvas canvas  = new PdfCanvas(pdfPage);

		System.out.println("C: " + contactor.getWidth());
		System.out.println("C: " + contactor.getHeight());

		System.out.println("P: " + pdfPage.getPageSize().getHeight());
		System.out.println("P: " + pdfPage.getPageSize().getWidth());

		for (ColumnLine line :column.getLines()) {
			canvas.setLineWidth(0.5f);
			canvas.setStrokeColorRgb(1f, 0f, 0f);
			canvas.moveTo(line.getBeginWidth(), line.getBeginHeight());
			canvas.lineTo(line.getEndWidth(), line.getEndHeight());
			canvas.addXObject(contactor.image(), new Rectangle(contactor.getWidthPos(), contactor.getHeightPos(),Measures.SCALE,Measures.SCALE));
			canvas.stroke(); //IMPORTANT
		}

		canvas.closePathStroke();				  		 
		getPdfDocument().close();  
		
		System.out.println("Object drawn on pdf successfully");
   }
}
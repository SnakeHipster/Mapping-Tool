package org.Assignment.MappingTool;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;

import org.junit.Test;

public class OutPrinterTest {

	/**
	 * Constructor Test
	 */
	@Test
	public void constructionTest(){
		OutPrinter p = new OutPrinter("Print Test");	//Tests that the constructor works as intended
	}
	
	/**
	 * Print Test
	 * @throws PrinterException
	 */
	@Test
	public void printTest() throws PrinterException{
		OutPrinter p = new OutPrinter("Print Test");
		String printData = p + "\n";
		
	    PrinterJob job = PrinterJob.getPrinterJob();		//Creates a new object of type PrinterJob
	    job.setPrintable(new OutPrinter(printData));
	    boolean doPrint = job.printDialog();
	    if (doPrint){ 
            job.print();	// Should open up the print dialogue box and if you print the page should display the string "Print Test"
	    }
	}
}

package org.Assignment.MappingTool;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
		
public class OutPrinter implements Printable {
	private String printString;

    /*
     * Constructor
     */
    public OutPrinter(String printDataIn){
    	this.printString = printDataIn;
    }

    /**
     * Overrides existing print function so that it can be customized
     */
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException{
    	// Makes sure there is only one page
    	if (page > 0)
    	{
    		return NO_SUCH_PAGE;
    	}
    
    	Graphics2D g2d = (Graphics2D)g;
    	int x = (int) pf.getImageableX();
    	int y = (int) pf.getImageableY();        
    	g2d.translate(x, y); 

    	// Keeps font all the same, in full implementation this will be expanded on
    	Font font = new Font("Serif", Font.PLAIN, 10);
    	FontMetrics metrics = g.getFontMetrics(font);
    	int lineHeight = metrics.getHeight();

    	BufferedReader br = new BufferedReader(new StringReader(printString));

    	// Draw the page:
    	try{		//Precaution in case no margin is created
    		String l;
    		x += 50;
    		y += 50;
    		while ((l = br.readLine()) != null){
    			y += lineHeight;
    			g2d.drawString(l, x, y);
    		}
    	} catch (IOException e){
    	}

    	return PAGE_EXISTS;
    }
}
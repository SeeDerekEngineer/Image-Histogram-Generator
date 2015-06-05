import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
	
public class CTScanImage {
	   private static Scanner myScanner = new Scanner(System.in);
	   private BufferedImage  image;
	   private int[][] rawValue = new int[xEnd][yEnd];
	   private int[] histogramValues = new int[256];
	   private static String answer1;
	   private static String answer2;
	   private static String answer3;
	   private static String answer4;
	   private static String answer5;
	   private static String answer6;
	   private static String answer7;
	   private boolean yesTrue1;
	   private boolean yesTrue2;
	   private boolean yesTrue3;
	   private boolean yesTrue4;
	   private boolean yesTrue5;
	   private boolean yesTrue6;
	   private boolean yesTrue7;
	   private double pixelsTotal = 0;
	   private int[] transformHistogram = new int[256];
	   private int[] equalizedHistogram = new int[256];
	   private double cdvMin=255;
	   private double cdvMax=0;
	   private double[] cdv = new double[256];
	   File f = null;
	   BufferedImage img = new BufferedImage(xEnd, yEnd, BufferedImage.TYPE_INT_RGB);   //Determines size of produced image
	   BufferedImage img2 = new BufferedImage(xEnd, yEnd, BufferedImage.TYPE_INT_RGB);  //Determines size of produced image
	   BufferedImage img3 = new BufferedImage(xEnd, yEnd, BufferedImage.TYPE_INT_RGB);  //Determines size of produced image
	   private static int xStart;
	   private static int yStart;
	   private static int xEnd;
	   private static int yEnd;
	   private static int boxXStart;
	   private static int boxXEnd;
	   private static int boxYStart;
	   private static int boxYEnd;
	   private static double compassConstant = 0.7071;
	   private int[] differences = new int[8];
	   private static int increment;
	
	   
	   public CTScanImage() {
	      try {
	    	  
	    	  File input = new File("Scans" + File.separator    //Obtain the jpeg file
            + "10_left.jpeg");
	         image = ImageIO.read(input);
	         yesTrue1 = answer1.equals("Yes") || answer1.equals("yes") || answer1.equals("YES") || answer1.equals("Y") || answer1.equals("y");
	         yesTrue2 = answer2.equals("Yes") || answer2.equals("yes") || answer2.equals("YES") || answer2.equals("Y") || answer2.equals("y");
	         yesTrue3 = answer3.equals("Yes") || answer3.equals("yes") || answer3.equals("YES") || answer3.equals("Y") || answer3.equals("y");
	         yesTrue4 = answer4.equals("Yes") || answer4.equals("yes") || answer4.equals("YES") || answer4.equals("Y") || answer4.equals("y");
	         yesTrue5 = answer5.equals("Yes") || answer5.equals("yes") || answer5.equals("YES") || answer5.equals("Y") || answer5.equals("y");
	         yesTrue6 = answer6.equals("Yes") || answer6.equals("yes") || answer6.equals("YES") || answer6.equals("Y") || answer6.equals("y");
	         yesTrue7 = answer7.equals("Yes") || answer7.equals("yes") || answer7.equals("YES") || answer7.equals("Y") || answer7.equals("y");
	         
	         for(int i=yStart; i<yEnd ; i++){
	            for(int j=xStart; j<xEnd ; j++){
                   Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed() * 0.299);
	               int green = (int)(c.getGreen() * 0.587);
	               int blue = (int)(c.getBlue() *0.114);
	               rawValue[j][i] = red+green+blue;			//Establish pixel values
	               
	               Color newColor = new Color(rawValue[j][i],
	               rawValue[j][i],rawValue[j][i]);
	               img.setRGB(j,i,newColor.getRGB());   //Establish grayscale image
	            }
	         }
	 for (int zz = 0; zz < 60; zz++){						//Start of local histogram equalization loop y
	 for (int z = 0; z < 50; z++){                          //Start of local histogram equalization loop x
	         for(int i=boxYStart; i<boxYEnd ; i++){
		            for(int j=boxXStart; j<boxXEnd ; j++){      
/*	               if(j > 0 && i > 0){
	               differences[0] = (int) Math.abs(rawValue[j][i]-(compassConstant*(rawValue[j-1][i-1] - rawValue[j][i])));
	               }	else {differences[0] = 0;}
	               if(i > 0){
	               differences[1] = Math.abs(rawValue[j][i]-rawValue[j][i-1]);
	               }	else {differences[1] = 0;}
	               if(j < xEnd-1 && i > 0){
	               differences[2] = (int) Math.abs(rawValue[j][i]-(compassConstant*(rawValue[j+1][i-1] - rawValue[j][i])));
	               }	else {differences[2] = 0;}
	               if(j > 0){
	               differences[3] = Math.abs(rawValue[j][i]-rawValue[j-1][i]);
	               }	else {differences[3] = 0;}
	               if(j < xEnd - 1){
	               differences[4] = Math.abs(rawValue[j][i]-rawValue[j+1][i]);
	               }	else {differences[4] = 0;}
	               if(j > 0 && i < yEnd - 1){
	               differences[5] = (int) Math.abs(rawValue[j][i]-(compassConstant*(rawValue[j-1][i+1] - rawValue[j][i])));
	               }	else {differences[5] = 0;}
	               if(i < yEnd - 1){
	               differences[6] = Math.abs(rawValue[j][i]-rawValue[j][i+1]);
	               }	else {differences[6] = 0;}
	               if(j < xEnd - 1 && i < yEnd - 1){
	               differences[7] = (int) Math.abs(rawValue[j][i]-(compassConstant*(rawValue[j+1][i+1] - rawValue[j][i])));	               
	               }	else {differences[7] = 0;}
	              
	               rawValue[j][i] = differences[0];
	               for(int z = 1; z < 8; z++){
	            	   if(differences[z] > rawValue[j][i]){rawValue[j][i] = differences[z];} 
	               }
*/	               
	               
	               for (int k =0; k < 256; k++){     //Establish Histogram values
	            	   if(k == rawValue[j][i]){
	            		   histogramValues[k] = histogramValues[k] + 1;  
	            		   break;
	            	   	} //End 'if' statement
	               } //End 'for' loop
	               cdv[0] = histogramValues[0];
	               
	               if(yesTrue1){
	               System.out.printf("%3d",rawValue[j][i]);	               
	               System.out.print("  ");
	               } //End of 'If' Statement
	               } //End 'for' loop j iteration
	            if(yesTrue1){System.out.println("");}
	            
	         
	         } //End 'for' loop i iteration
	         if(yesTrue1){System.out.println("*****************END OF RAW PIXEL VALUES*********************");}
	         
	         
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	         
	         //Establish cdv variable used for histogram equalization of pixel values
	         //Print histogram values
	         
	         for (int m = 0; m < 256; m++){
	        	 pixelsTotal = pixelsTotal + histogramValues[m];
	        	 if (histogramValues[m] > cdvMax){cdvMax = histogramValues[m];}
	        	 if (histogramValues[m] < cdvMin && histogramValues[m] != 0){cdvMin = histogramValues[m];}   //Establish cdvMin
          	     if (m != 0){cdv[m] = cdv[m-1] + histogramValues[m];}   									 //Establish cdv[]
	        	 if (yesTrue2){System.out.println(histogramValues[m]);} 
	         } //End of 'for' loop
	         if (yesTrue2){System.out.println("****************END OF HISTOGRAM VALUES*********************");}
	         
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	         
	         //Establish histogram equalized pixel values
	         
	         for (int m = 0; m < 256; m++){
	        	 transformHistogram[m] = (int) Math.round((((cdv[m]-cdvMin)/(pixelsTotal - cdvMin))*255));
	         } //End of 'for' loop
	      
	         for(int i=boxYStart; i<boxYEnd ; i++){
		            for(int j=boxXStart; j<boxXEnd ; j++){
	         /*          Color c = new Color(image.getRGB(j, i));
	                   int red = (int)(c.getRed() * 0.299);
		               int green = (int)(c.getGreen() * 0.587);
		               int blue = (int)(c.getBlue() *0.114);
		              rawValue[j][i] = red+green+blue;
		      */          
		                
	           
	        
		               
		               
		               
		               for (int k =0; k < 256; k++){               //Conversion of pixel values to histogram equalized pixel values
		            	   if(k == rawValue[j][i]){
		            		   rawValue[j][i] = transformHistogram[k];
		            		   break;
		            	   	} //End 'if' statement
		               } //End 'for' loop
		               for (int k = 0; k< 256; k++){
		            	   if(k == rawValue[j][i]){
		            		   equalizedHistogram[k] = equalizedHistogram[k] + 1;
		            		   break;
		            	   } //End 'if' statement
		               } //End 'for' loop
		               if(yesTrue3){
		               System.out.println(rawValue[j][i]);	               
		              // System.out.print("  ");
		               } //End of 'If' Statement
		               
		               if(rawValue[j][i] > 255){rawValue[j][i] = 255;}
		 //              if(j >= 492 && j < 592 && i >= 572 && i < 672){
		               Color newColor2 = new Color(rawValue[j][i],
		            		   rawValue[j][i], rawValue[j][i]);
		               img2.setRGB(j, i, newColor2.getRGB());
		 //              }
		               
		               if(rawValue[j][i] > 250) {
		            	   rawValue[j][i] = 255;
		               }	else {rawValue[j][i] = 0;}
		               Color newColor3 = new Color(rawValue[j][i],
		            		   rawValue[j][i], rawValue[j][i]);
	               		img3.setRGB(j, i, newColor3.getRGB());
	      				} //End 'for' loop
		           // if(yesTrue3){System.out.println("");}  
		         } //End 'for' loop
	
	
		
		
		
		
	
		System.out.println(z);
		
		for(int k = 0; k < 256; k++){				//for loop to reset histogram algorithms
			histogramValues[k] = 0;
			cdv[k] = 0;
			cdvMin = 255;
			cdvMax = 0;
			transformHistogram[k] = 0;
			pixelsTotal = 0;
		}//End for loop
	
		boxXStart = boxXStart + increment;
		boxXEnd = boxXEnd + increment;
	
	}//End local histogram equalization for loop x
	 	
	 	boxXStart = 0;
	 	boxXEnd = 35;
	 	boxYStart = boxYStart + increment;
		boxYEnd = boxYEnd + increment;
		
		System.out.println(zz);
		
	}//End local histogram equalization for loop y
	 
	      }  //End of 'try' block
	      catch (Exception e) {System.out.println("Did you put an image, because I got nothing?");}
	      
	      // Print Equalized Histogram Values
	      for (int m = 0; m < 256; m++){
	    	  if(yesTrue6){System.out.println(equalizedHistogram[m]);}
	         } //End of 'for' loop
	      	if(yesTrue6){System.out.println("END OF EQUALIZED HISTOGRAM VALUES");}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  
	   //Build the new image
	      
	   if(yesTrue4){
		   f = new File("NewScans" + File.separator
            + "GrayScan2.png");
		   try {
			ImageIO.write(img, "png", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	  
	   if(yesTrue5){
		   f = new File("NewScans" + File.separator
            + "EqualizedScan2.png");
		   try {
			ImageIO.write(img2, "png", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	
	   if(yesTrue7){
		   f = new File("NewScans" + File.separator
		            + "CompassScan2.png");
				   try {
					ImageIO.write(img3, "png", f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   }
	   
	   }
	   	   
	   
	   public static void main(String args[]) throws Exception 
	   {
	     
		      xStart = 0;
		      xEnd = 2094;
		      yStart = 0;
		      yEnd = 2112;
		      boxXStart = 0;
		      boxXEnd = 35;
		      boxYStart = 0;
		      boxYEnd = 35;
		      increment = 35;
		   
		  //System.out.print("Would you like the raw pixel values?: ");
		  answer1 = "No";
				  //myScanner.next();
		  //System.out.println("Would you like the historgram values?: ");
		  answer2 = "No";
	      //System.out.println("Would you like the histogram equalized pixel values?: ");
	      answer3 = "No";
	    		  //myScanner.next();
		  //System.out.println("Would you like the grayscale image?: ");
		  answer4 = "Yes";
		  //System.out.println("Would you like the histogram equalized grayscale image?: ");
		  answer5 = "Yes";
				  //myScanner.next();
		  //System.out.println("Would you like the equalized histogram values?: ");
		  answer6 = "No";
		  //System.out.println("Would you like the Compass Image?");
		  answer7 = "No";
		  
		  
		  CTScanImage obj = new CTScanImage();
	   }
	}
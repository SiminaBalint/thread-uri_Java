package assigment_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] Args) {				
		
		 try
	        {			 				 	
	            PrintStream fileOut = new PrintStream(Args[1]);
	            PrintStream fileErr = new PrintStream("./err.txt");
	            
	            System.setOut(fileOut);
	            System.setErr(fileErr);
	        }catch(FileNotFoundException ex){
	            ex.printStackTrace();
	        }
		
		try {		     		      
			Scanner myReader = new Scanner(new File(Args[0]));
		      while (myReader.hasNext()) {		    	  	
		    		int maxC = Integer.parseInt(myReader.next());		    		
		    		int maxS = Integer.parseInt(myReader.next());		    				    	  		    	    
		    	    Simulation s = new Simulation(maxS);
		    	    s.setMaxClients(maxC);		    	    
		    	    int time = Integer.parseInt(myReader.next());
		    	    s.setTime(time);		    		
		    	    int minA = Integer.parseInt(myReader.next());
		    	    int maxA = Integer.parseInt(myReader.next());
		    	    s.setArrivals(minA, maxA);		    	    
		    	    int minW = Integer.parseInt(myReader.next());
		    	    int maxW = Integer.parseInt(myReader.next());
		    	    s.setWaits(minW, maxW);		    	    		    	    
		    		s.startSim();		        
		      }
		      myReader.close();
		}catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
	    }				
	}

}

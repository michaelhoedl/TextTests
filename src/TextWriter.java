

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TextWriter {
	
	public static String writeText(String stri){
		
	try {
		File fileDir = new File("temp/testoutput.txt");
		
		
		BufferedWriter writer = new BufferedWriter( new FileWriter( fileDir));
		writer.write( stri);
		// do stuff 
		writer.close();
			
                
	    } 
	    catch (UnsupportedEncodingException e) 
	    {
			System.out.println(e.getMessage());
	    } 
	    catch (IOException e) 
	    {
			System.out.println(e.getMessage());
	    }
	    catch (Exception e)
	    {
			System.out.println(e.getMessage());
	    }
		return stri;
	}
}
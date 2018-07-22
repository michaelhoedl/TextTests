

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class TextReader {
	
	public static String getText(){
		String stri = "";
		
	try {
		File fileDir = new File("temp/test.txt");
			
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
		        
		String str;
		      
		while ((str = in.readLine()) != null) {
		    //System.out.println(str);
		    stri += str + "\n";
		}
		
                in.close();
                
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
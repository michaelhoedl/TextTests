
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String txt = TextReader.getText();
		//System.out.println("txt="+txt);
		
		Main m = new Main();
		String o = m.bearbeiteText(txt);
		
		System.out.println("o len="+o.length());
		
	}
	
	public String bearbeiteText(String txt) {
		String output = "";
		
		int len = txt.length();
		System.out.println("len="+len);
		
		
		int ix = txt.indexOf(") values (");
		
		String txt2 = txt.substring(ix+10);
		String txtInsrt = txt.substring(0,ix+10);
		
		//System.out.println("txt2="+txt2);
		System.out.println("txtInsrt="+txtInsrt);
		
		//wie viele Beistriche kommen im Insert vor:
		int anzBeistrich = (int) txtInsrt.chars().filter(ch -> ch == ',').count();
		System.out.println("anzBeistrich="+anzBeistrich);
		
		int ix2 = txt2.lastIndexOf(");");
		String txt3 = txt2.substring(0, ix2);
		
		//System.out.println("txt3="+txt3);
		
		ArrayList<String> texte = new ArrayList<String>();
		

		int ind = 0;
		for(int i = 1; i <= anzBeistrich+1; i++) {
			String x = null;
			
			// wenn Wert bereits ein String ist, dann ists ein String:
			if(i <= anzBeistrich) {
				if (txt3.startsWith("'")) {
					ind = txt3.indexOf("', ");
					x = txt3.substring(0+1, ind); // +1 wegen: "'" am Anfang
					
					txt3 = txt3.substring(ind+3); // +3 wegen: "', "
				} else { // sonst ists kein String
					ind = txt3.indexOf(", ");
					x = txt3.substring(0, ind);
					
					txt3 = txt3.substring(ind+2); // +2 wegen: ", "
				}
				texte.add(x);
			} else {
				if (txt3.startsWith("'")) {
					ind = txt3.lastIndexOf("'");
					x = txt3.substring(0+1, ind); // +1 wegen: "'" am Anfang
					
					txt3 = txt3.substring(ind+1); // +3 wegen: "'"
				} else { // sonst ists kein String
					x = txt3.substring(0);
					//txt3 = txt3.substring(ind+2); // +2 wegen: ", "
				}
				texte.add(x);
			}
			
		}
		
		int texteLen = texte.size();
		System.out.println("texteLen="+texteLen);
		output = txtInsrt;
		
		for (int i = 0; i < texteLen; i++) {
			
			int tlen = texte.get(i).length();
			
			
			System.out.println("length="+tlen);
			
			if (tlen > 3700) {
				// to_clob einbauen
				
				System.out.println("wie oft: "+(int)Math.ceil(tlen/3700));
				output += "to_clob('";
				for (int j = 1; j <= (int)Math.ceil(tlen/3700); j++) {
					if (j==1) {
						output += texte.get(i).substring(0, j*3700)+"')||to_clob('";
					} else {
						//if (j < (int)Math.ceil(tlen/3700))
							output += texte.get(i).substring((j-1)*3700-1, j*3700)+"')||to_clob('";
						//else
						//	output += texte.get(i).substring(j*3700-1);
					}
				}
				output += "')";
				
			} else {
				output += "'"+texte.get(i)+"'";
			}
			
			if (i < texteLen-1) {
				output += ", ";
			}
		}
		output += ");";
		
		//for(String s : texte) {
		//	System.out.println("length="+s.length()+"; s=");
		//	output += s;
		//}
 
		TextWriter.writeText(output);
		
		return output;
	}

}

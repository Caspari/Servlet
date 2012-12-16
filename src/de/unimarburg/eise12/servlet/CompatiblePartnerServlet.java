/**
 * A servlet that calculates how well couples match, depending on their names.
 *
 */

package de.unimarburg.eise12.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class CompatiblePartnerServlet implements IServlet{
	
	public void process(Map<String, List<String>> params, PrintWriter output) {
		
		String name1 = params.get("name1").get(0);
		String name2 = params.get("name2").get(0);
		String surname1 = params.get("surname1").get(0);
		String surname2 = params.get("surname2").get(0);
		
		int compability = calcCompability(name1, name2, surname1, surname2);
		
		
		output.append(name1 + " " + surname1 + " and " + name2 + " " + surname2);
		output.append(" match this well: " + compability + " %\n");
	}
	
	private int calcCompability(String name1, String name2, 
								   String surname1, String surname2){
		
		char[] name1charArr = name1.toCharArray();
		char[] name2charArr = name2.toCharArray();
		char[] surname1charArr = surname1.toCharArray();
		char[] surname2charArr = surname2.toCharArray();
		
		int result = 100;
		
		int namelengthCounter;
		if (name1.length() <= name2.length()) 
			namelengthCounter = name1.length();
		else 
			namelengthCounter = name2.length();
		
		int i = 0;
		while (i < namelengthCounter){
			result = result - Math.abs(name1charArr[i] - name2charArr[i]);
			i += 1;
		}
		
		int surnamelengthCounter;
		if (surname1.length() <= surname2.length()) 
			surnamelengthCounter = name1.length();
		else 
			surnamelengthCounter = surname2.length();

		int j = 0;
		while (j < surnamelengthCounter){
			result = result - Math.abs(surname1charArr[j] - surname2charArr[j]);
			j+= 1;
		}

		if (result < 0) result = 0;
	
		return result;
	}
	
}

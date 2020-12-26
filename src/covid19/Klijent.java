package covid19;

import java.io.*;
import java.net.*;

public class Klijent implements Runnable {

	static Socket soketZaKomunikaciju = null;
	static BufferedReader serverInput = null;
	static PrintStream serverOutput = null;
	static BufferedReader unosSaTastature = null;
	static String input;
	
	public static void main(String[] args) {
		
		try {
			
			soketZaKomunikaciju = new Socket("localhost", 4545);
			
			serverInput = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			serverOutput = new PrintStream(soketZaKomunikaciju.getOutputStream());
			
			unosSaTastature = new BufferedReader(new InputStreamReader(System.in));
			
			// u main pisemo prijem poruka od servera, a run pisemo slanje poruka
			
			new Thread(new Klijent()).start();
			
			
			
			while(true) {
				input = serverInput.readLine();
				System.out.println(input);
				
				// proveravamo da li poruka od servera pocinje sa dovidjenja
				if(input.startsWith("Dovidjenja")) {
					break;
				}
			}
			
			soketZaKomunikaciju.close();
			
			
		} catch (UnknownHostException e) {
			System.out.println("NEPOZNAT HOST!");
		} catch (IOException e) {
			System.out.println("SERVER JE PAO!");
		}
	}

	@Override
	public void run() {

		String poruka;
		
		while(true) {
			try {
				poruka = unosSaTastature.readLine();
				
			
				serverOutput.println(unosZaMeni(poruka));
				
					
				
			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
	}
	
	public String unosZaMeni(String poruka) {
	
		try {
			switch (Integer.parseInt(poruka)) {
			case 1:
				return "1";
			case 2:
				return "2";
			case 3:
				return "3";
			default:
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println("Nepostojeca opcija, pokusajte ponovo.");
			System.out.println();
			System.out.println();
			return "GRESKA: POGRESNA OPCIJA";
		}

		return "GRESKA: POGRESNA OPCIJA";
	}
		
}



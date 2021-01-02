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
			
			while (true) {
				
				input = serverInput.readLine();
				
				System.out.println(input);

				// proveravamo da li je poruka od servera
				if (input.equals("Dovidjenja")) {
					break;
				}
			}
				
			
			
			soketZaKomunikaciju.close();
			
			
		} catch (UnknownHostException e) {
			System.out.println("NEPOZNAT HOST!");
		} catch (IOException e) {
			System.out.println("SERVER JE PAO! UKOLIKO SE NISTE DO KRAJA REGISTROVALI ILI TESTIRALI, MORACETE TO PONOVO DA UCINITE KADA BUDEMO AKTIVNI.");
		}
	}

	@Override
	public void run() {
	
		String poruka;

		while (true) {

			try {
				poruka = unosSaTastature.readLine();
				
				serverOutput.println(poruka);

				if (poruka.equals("3")) {
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
}



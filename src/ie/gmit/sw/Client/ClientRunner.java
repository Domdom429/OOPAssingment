package ie.gmit.sw.Client;

import java.util.Scanner;
import ie.gmit.sw.Client.Configuration.*;

public class ClientRunner{
	public static void main(String[] args) throws Throwable{
		// Declaration of variables
		Scanner console = new Scanner(System.in);
		int selection;
		
		// XML Configuration fike parsing
		Context ctx = new Context();
		ContextParser cp = new ContextParser(ctx);
		cp.parse();
		
		// Declare & instantiate a UserInterface object
		Interface UI = new Interface();
		
		ClientFile clientService = new ClientFile(ctx);
		while(UI.isRunning()){
			
			
			// displays the user input
			UI.userMenu();
			// recods the user input
			selection = console.nextInt();
			
	
			switch(selection){
				case 1: 
					clientService.connectToServer();
					break;
				case 2:
					clientService.requestListOfFiles();
					break;
				case 3:
					clientService.requestDownload();
					break;
				case 4:
					UI.quit(clientService.quit());
					break;
				default:
					System.out.println("\n\tOption dooes not exist!\n\tPlease try again");
					break;
			}// End of switch
		}// End of while
		// Console termination
		console.close();
	}// End of main
}// End of ClientRunner Class

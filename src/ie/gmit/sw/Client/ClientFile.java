package ie.gmit.sw.Client;

import java.io.*;
import java.net.*;
import java.util.*;
import ie.gmit.sw.Drivers.*;
import ie.gmit.sw.Client.Configuration.Context;

public class ClientFile implements DrivableClient{
	
	
	
	// declaring variables

	private Socket soc;
	private String hostAddress;
	private int portNo;
	private String downloadDir;
	private String clientAdress;
	private File[] listFile;
	
	
	public ClientFile(Context ctx){
		this.hostAddress = ctx.getServerHostAddress();
		this.portNo = ctx.getServerPortNumber();
		this.downloadDir = ctx.getDownloadDirectory();
	}
	
	@Override
	public void connectToServer(){
		try{
			// Declare & instantiate a new Socket object
			soc = new Socket(hostAddress, portNo);
			// Acquiring Client IP Address
			clientAdress = soc.getLocalAddress().getHostAddress();
			// Serialise a request to the server
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			// Output of serialised stream
			out.writeObject(new ConnectDrive(clientAdress));
			// Ensuring all data is sent by flushing buffers
			out.flush();
			// Causing thread to pause for a while
			Thread.yield();
			// Server response handling
			ObjectInputStream serverResponse = new ObjectInputStream(soc.getInputStream());
			// Deserialization of object
	        String response = (String)serverResponse.readObject();
			
	        // Informing client of current connection with a detailed message
	        System.out.println(response);
		}// End of try
		
		catch(Exception e){
			e.printStackTrace();
		}// End of catch
	}// End of connectToServer

	
	
	@Override
	public void requestListOfFiles(){
		try {
			soc = new Socket(hostAddress, portNo);
			clientAdress = soc.getLocalAddress().getHostAddress();
			
			//Serialise a request to the server
	        ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
	        // Output of serialised stream
	        out.writeObject(new ListDriver(clientAdress));
			// Ensuring all data is sent by flushing buffers
	        out.flush();
	        // Causing thread to pause for a while
	        Thread.yield();
	        
	        // Server response handling
	        ObjectInputStream serverResponse = new ObjectInputStream(soc.getInputStream());
	        // Deserialization of objects
	        listFile = (File[]) serverResponse.readObject();
	        // Loop to print individual objects in the array list
	        for(int i = 0; i < listFile.length; i++){
	        	// Display objects
	        	System.out.println(listFile[i].getName());
	        }// End of for
		}// End of try
		
		catch(Exception e){
			e.printStackTrace();
		}// End of catch
	}// End of requestListOfFiles

	@Override
	public void requestDownload(){
		// Declaration of local variable
		Scanner console = new Scanner(System.in);
		// Displaying user instruction
		System.out.print("\nPlease enter name of the file you wish to download: ");
		// Recording user input
		String fileName = console.nextLine();
		
		// Looping through array list of files
		for(int i = 0; i < listFile.length; i++){
			// Searching for Name match is in the list
			if(fileName.equals(listFile[i].getName())){
				try {
					soc = new Socket(hostAddress, portNo);
					clientAdress = soc.getLocalAddress().getHostAddress();
					
					//Serialise/marshal a request to the server
			        ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			        // Output of serialised stream
			        out.writeObject(new DlDrive(clientAdress, fileName));
					// Ensuring all data is sent by flushing buffers
			        out.flush();
			        // Causing thread to pause for a while
			        Thread.yield();
			        
			        // Server response handling
			        ObjectInputStream serverResponse = new ObjectInputStream(soc.getInputStream());
			        // Reading an byteArray object into an array list
			        byte[] byteArray = (byte[])serverResponse.readObject();
			        
			        FileOutputStream output = new FileOutputStream(downloadDir + "/" + fileName);
			        // Output of stream
			        output.write(byteArray);
			        // Closure of stream
			        output.close();
				}// End of try
				
				catch(Exception e){
					e.printStackTrace();
				}// End of catch
			}// End of if
		}// End of for
	}// End of requestDownload

	@Override
	public boolean quit(){
		// Declaration & initialisation of local variables
		boolean confirmation = true;
		String userInput;
		Scanner console = new Scanner(System.in);
		
		do{
			// Displaying warning message for user
			System.out.println("\tAre you sure you wish to terminate...?[y/n]");
			userInput = console.nextLine().toLowerCase();
			//
			if(userInput.equals("n")){
			}// End of if
			
			//
			else if(userInput.equals("y")){
				// Display application termination user message
				System.out.println("\tApplication is terminating...");
				confirmation = false;
			}// End of else if
			
			//
			else{
				System.out.println("\n\tUse y/Y to confirm or n/N to decline only!\n\tPlease try again");
			}// End of else
		}while(userInput.equals('y') || userInput.equals('n')); // End of do/while
		
		// Return boolean value
		return confirmation;
	}// End of quit
}// End of FileServer Class

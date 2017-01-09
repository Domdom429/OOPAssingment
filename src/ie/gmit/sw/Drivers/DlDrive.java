package ie.gmit.sw.Drivers;

import java.io.*;

public class DlDrive extends RequestDriver{
	// Declaration of a private static final variables
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	private static final String query = "Download";
	// Declaration of private variables
	private String pathFile;
	private String nameFile;
	
	public DlDrive(String clientIpAddress, String fileName){
		super(clientIpAddress);
		this.nameFile = fileName;
	}// End of DownloadDriver Constructor

	@Override
	public void run(){
		File file = new File(pathFile + "/" + nameFile);
		byte[] byteArray = new byte[(int)file.length()];
		
		try{
			// Selection of input file for loading
			FileInputStream fileInput = new FileInputStream(file);
			// Reading the input stream
			BufferedInputStream bufferInputFile = new BufferedInputStream(fileInput);
			// Creation of a copy of the file into byteArray
			bufferInputFile.read(byteArray,0,byteArray.length);
			
			ObjectOutputStream output = new ObjectOutputStream(super.getSocketNumber().getOutputStream());
			// Output of stream
			output.writeObject(byteArray);
			// Ensuring all data is sent by flushing buffers
			output.flush();
			// Closure of stream
			output.close();
		}// End of try
		
		catch (FileNotFoundException e){
			e.printStackTrace();
		}// End of catch
		
		catch(IOException e){
			e.printStackTrace();
		}// End of catch
	}// End of run
	
	public String getFilePath(){
		return pathFile;
	}// End of getFilePath

	public void setFilePath(String filePath){
		this.pathFile = filePath;
	}// End of setFilePath
	
	@Override
	public String toString(){
		return "<" + query + "> " + " | Requested by: " + super.getClientIpAddress() + " | At: " + super.getDate().toString() + " |";
	}// End of toString
}// End of DownloadDriver Class

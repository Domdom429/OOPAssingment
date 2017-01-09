package ie.gmit.sw.Client.Configuration;

public class Context{
	public static final String CONF_FILE = "Conf.xml";
	private String userN;
	private String serverAdress;
	private int portNumber;
	private String downloadDirec;
	
	public Context(){
		super();
	}// End of Context
	
	public String getUsername(){
		return userN;
	}// End of getUsername
	
	public void setUsername(String username){
		this.userN = username;
	}// End of setUsername
	
	public String getServerHostAddress(){
		return serverAdress;
	}// End of getServerHost
	
	public void setServerHostAddress(String serverHost){
		this.serverAdress = serverHost;
	}// End of setServerHost
	
	public int getServerPortNumber(){
		return portNumber;
	}// End of getServerPortNumber
	
	public void setServerPortNumber(int serverPort){
		this.portNumber = serverPort;
	}// End of setServerPortNumber
	
	public String getDownloadDirectory(){
		return downloadDirec;
	}// End of getDownloadDirectory
	
	public void setDownloadDirectory(String downloadDirectory){
		this.downloadDirec = downloadDirectory;
	}// End of setDownloadDirectory
	
	@Override
	public String toString(){
		return "Context [Username: " + userN 
				+ "| Server Host Address: " + serverAdress 
				+ "| Server Port Number: " + portNumber
				+ "| Download Directory: " + downloadDirec + "]";
	}// End of toString
}// End of Context Class

package ie.gmit.sw.Drivers;

import java.net.*;
import java.util.Date;
import java.io.Serializable;

public abstract class RequestDriver implements Serializable, Runnable{
	// Declaration of a private static variable
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	// Declaration of private variables
	private Date date;
	private Socket socketNo;
	private String userN;
	private String query;
	private String clientAdress;
	private String hostAddress;
	private int portNo;
	
	
	
	
	
	public RequestDriver(String clientIpAddress){
		date = new Date();
		this.clientAdress = clientIpAddress;
	}// End of RequestDriver Constructor
	
	public Date getDate(){
		return date;
	}// End of getDate
	
	public void setDate(Date date){
		this.date = date;
	}// End of setDate
	
	public Socket getSocketNumber(){
		return socketNo;
	}// End of getSocketNumber
	
	public void setSocketNumber(Socket socketNumber){
		this.socketNo = socketNumber;
	}// End of setSocketNumber
	
	public String getUsername(){
		return userN;
	}// End of getUsername
	
	public void setUsername(String username){
		this.userN = username;
	}// End of setUsername
	
	public String getQuery(){
		return query;
	}// End of getQuery
	
	public void setQuery(String query){
		this.query = query;
	}// End of setQuery
	
	public String getClientIpAddress(){
		return clientAdress;
	}// End of getClientIpAddress
	
	public void setClientIpAddress(String clientIpAddress){
		this.clientAdress = clientIpAddress;
	}// End of setClientIpAddress
	
	public String getHostAddress(){
		return hostAddress;
	}// End of getHostAddress
	
	public void setHostAddress(String hostAddress){
		this.hostAddress = hostAddress;
	}// End of setHostAddress
	
	public int getPortNumber(){
		return portNo;
	}// End of getPortNumber
	
	public void setPortNumber(int portNumber){
		this.portNo = portNumber;
	}// End of setPortNumber
}// End of Abstract Request Class
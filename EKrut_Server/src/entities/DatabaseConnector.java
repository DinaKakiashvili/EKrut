package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.MissionPack;
import ocsf.server.ConnectionToClient;
import server.ServerMissionAnalyze;

public class DatabaseConnector {

	private static DatabaseConnector dataBaseConnector = null;
	private Connection connection = null;
	private boolean isConnected = false;
	private List<String> connectionDetailsList = new ArrayList<>();

	private DatabaseConnector() {
	}

	public static DatabaseConnector getDatabaseConnectorInstance() {
		if (dataBaseConnector == null) {
			dataBaseConnector = new DatabaseConnector();
		}
		return dataBaseConnector;
	}

	public Connection getConnection() {
		if (connection == null) {
			configDriver();
			connect();
		}
		return connection;
	}

	public static void parsingToData(MissionPack obj, ConnectionToClient client) {
			try {
				ServerMissionAnalyze.MissionsAnalyze(obj, client);
				System.out.println("parsing");
			} catch (SQLException e) {e.printStackTrace(); }
	}
	
	/**
	 * connecting to the database with the correct information that given from the
	 * server screen. if the information is incorrect, it will throw SQLException.
	 * 
	 * @return string depending on the information
	 */
	public String connect() {
		StringBuffer buff = new StringBuffer();
		buff.append(configDriver());
		if (buff.toString().equals("\nDriver definition failed\n"))
			return buff.toString();
	// add check if length smaller than 4
		if (connectionDetailsList.size() == 4) {
			String ip = connectionDetailsList.get(0);
			String dbName = connectionDetailsList.get(1);
			String dbUsername = connectionDetailsList.get(2);
			String dbPassword = connectionDetailsList.get(3);
			connectToDatebase(buff, ip, dbName, dbUsername, dbPassword);
		}
		else {
			buff.append("missing arguments");
		}
		return buff.toString();
	}

	public void setConnectionDetailsList(List<String> detailsList) {
		for (String currentDetail : detailsList) {
			connectionDetailsList.add(currentDetail);
		}
	}

	public boolean disconnect() {
		if (isConnected) {
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (SQLException e) {
					return false;
				}
			}
		}
		isConnected = false;
		return true;
	}

	private void connectToDatebase(StringBuffer buff, String ip, String dbName, String dbUsername, String dbPassword) {
		try {
			connection = DriverManager.getConnection(dbName , dbUsername, dbPassword); // URL, Username, Password+changed url with message "&useSSL=false"
			buff.append("\nDatabase connection succeeded!\n");
			isConnected = true;
		} catch (SQLException e) {
			buff.append("\nDatabase connection failed!\n");
			isConnected = false;
		}
	}

	/**
	 * configures the driver for the JDBC API
	 * 
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	private String configDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			return "\nDriver definition succeed\n";
		} catch (Exception ex) {
			/* handle the error */
			return "\nDriver definition failed\n";
		}
	}

	public void clearConnectionDetailsList() {
		connectionDetailsList.clear();
	}

}

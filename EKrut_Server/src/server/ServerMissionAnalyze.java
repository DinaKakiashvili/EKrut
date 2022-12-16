package server;

import java.sql.SQLException;

import common.ClientStatus;
import common.MissionPack;
import entities.DatabaseConnector;
import ocsf.server.ConnectionToClient;

public class ServerMissionAnalyze {
	@SuppressWarnings("incomplete-switch")
	public static void MissionsAnalyze(MissionPack obj,ConnectionToClient client) throws SQLException {
		switch (obj.getMission()) {
		case GET_SUBSCRIBERS: {
			QueryExecutor.getSubscribersData(obj, DatabaseConnector.getDatabaseConnectorInstance().getConnection());
			break;
		}

		case EDIT_SUBSCRIBERS_DATA: {
			QueryExecutor.updateClientInDatabase(obj, DatabaseConnector.getDatabaseConnectorInstance().getConnection());
			break;
		}

		case SEND_CONNECTION_DETAILS: {
			QueryExecutor.updateClientList(obj, client, ClientStatus.CONNECTED);
			break;
		}

		case SEND_DISCONNECTION_DETAILS: {
			QueryExecutor.updateClientList(obj, client, ClientStatus.DISCONNECTED);
			break;
		}
		
		case IDENTIFY_USER: {
			QueryExecutor.identifyUser(obj,DatabaseConnector.getDatabaseConnectorInstance().getConnection());
			break;
		}
		case ADD_CUSTOMER_DATA: {
			QueryExecutor.AddingNewCustomer(obj,DatabaseConnector.getDatabaseConnectorInstance().getConnection());
			break;
		}
		case FROM_CUSTOMER_TO_SUBSCRIBER: {
			QueryExecutor.changeToSubscriber(obj,DatabaseConnector.getDatabaseConnectorInstance().getConnection());
			break;
		}
		
		}
		
		
	}
}

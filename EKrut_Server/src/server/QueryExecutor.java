package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.*;
import entities.ConnectedClient;
import entities.Subscriber;
import entities.Customer;
import gui.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import ocsf.server.ConnectionToClient;

public class QueryExecutor {
	public static void getSubscribersData(MissionPack obj, Connection con) {
		List<Subscriber> subscribers = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ekrut.subscriber;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subscribers.add(new Subscriber(rs.getString("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("phoneNumber"), rs.getString("emailAddress"), rs.getString("creditCardNumber"),
						rs.getString("subscriberNumber")));
			}
			rs.close();
			if (subscribers.size() > 0) {
				obj.setResponse(Response.FOUND_SUBSCRIBERS);
				obj.setInformation(subscribers);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Importing orders from E-krut.subscriber has failed!");
			obj.setResponse(Response.DIDNT_FOUND_SUBSCRIBERS);
		}
	}

	public static void updateClientInDatabase(MissionPack obj, Connection con) {
		Subscriber subscriber = (Subscriber) obj.getInformation();
		PreparedStatement ps = null;
		boolean idExists = false;

		try {
			ps = con.prepareStatement("SELECT subscriber.id FROM ekrut.subscriber;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("id").equals(subscriber.getId())) {
					idExists = true;
					break;
				}

			}
		} catch (SQLException sqlException) {
			System.out.println("Statement failure");
		}

		if (subscriber.getId().isBlank() || !idExists) {
			obj.setResponse(Response.EDIT_SUBSCRIBERS_FAILD);
			return;
		}

		try {
			ps = con.prepareStatement(
					"UPDATE ekrut.subscriber SET creditCardNumber = ?, subscriberNumber = ? WHERE id = ?");
		} catch (SQLException sqlException) {
			System.out.println("Statement failure");
		}

		// System.out.println(subscriber.getCreditCardNumber()+"
		// "+subscriber.getSubscriberNumber()+" "+subscriber.getId());

		try {
			ps.setString(1, subscriber.getCreditCardNumber());
			ps.setString(2, subscriber.getSubscriberNumber());
			ps.setString(3, subscriber.getId());
			ps.executeUpdate();

			obj.setInformation(subscriber);
			obj.setResponse(Response.EDIT_SUBSCRIBERS_SUCCESS);
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Executing statement-Updating credit card and subscriber number has failed");
			obj.setResponse(Response.EDIT_SUBSCRIBERS_FAILD);
		}

	}

	public static void updateClientList(MissionPack obj, ConnectionToClient client, ClientStatus connectionStatus) {
		ObservableList<ConnectedClient> clientList = ServerConfiguration.getClientList();

		for (int i = 0; i < clientList.size(); i++) {
			/* Comparing clients by IP addresses */
			if (clientList.get(i).getIp().equals(client.getInetAddress().getHostAddress()))
				clientList.remove(i);
		}

		/*
		 * In both cases of Connect and Disconnected we will need to add Client into the
		 * list so this function covers both of them simultaneously
		 */

		boolean hasAdd = clientList.add(new ConnectedClient(client.getInetAddress().getHostAddress(),
				client.getInetAddress().getHostName(), connectionStatus));
		if (hasAdd)
			obj.setResponse(Response.UPDATE_CONNECTION_SUCCESS);
		else
			obj.setResponse(Response.UPDATE_CONNECTION_FAILD);
		ServerConfiguration.setClientList(clientList);

	}

	public static void identifyUser(MissionPack obj, Connection con) throws SQLException {
		String[] data = (String[]) obj.getInformation();
		String username = data[0];
		String password = data[1];
		String role = null, phoneNumber = null, firstName = null;
		String[] newData = new String[5];

		PreparedStatement ps = null;
		boolean exists = false;

		try {
			ps = con.prepareStatement("SELECT users.username, password, role,phoneNumber,firstName FROM ekrut.users;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
					exists = true;
					role = rs.getString("role");
					phoneNumber = rs.getString("phoneNumber");
					firstName = rs.getString("firstName");
					// System.out.println(username+" "+role);
				}

			}
		} catch (SQLException sqlException) {
			System.out.println("Statement failure");
		}

		if (exists == true) {
			obj.setResponse(Response.LOGIN_SUCCEED);
			newData[0] = username;
			newData[1] = password;
			newData[2] = role;
			newData[3] = phoneNumber;
			newData[4] = firstName;

			obj.setInformation(newData);
			try {
				ps = con.prepareStatement("UPDATE ekrut.users SET isLoggedIn=\"1\" WHERE id=?");
				ps.setString(1, username);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				obj.setResponse(Response.LOGIN_FAILED);
			}
		} else {
			obj.setResponse(Response.LOGIN_FAILED);
		}
	}

	public static void AddingNewCustomer(MissionPack obj, Connection con) {
		PreparedStatement ps = null;
		Customer ourcus = (Customer) obj.getInformation();
		String username = ourcus.getId();
		String password = "1234";
		String FirstName = ourcus.getFirstName();
		String LastName = ourcus.getLastName();
		String EmailAddress = ourcus.getEmailAddress();
		String PhoneNumber = ourcus.getPhoneNumber();
		String ID = ourcus.getId();
		try {

			ps = con.prepareStatement(
					"INSERT INTO ekrut.users (username,password,firstName,lastName,role,email,phoneNumber,isLoggedIn,storeName,id) VALUES (?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, FirstName);
			ps.setString(4, LastName);
			ps.setString(5, "RequsestToBeCustomer");
			ps.setString(6, EmailAddress);
			ps.setString(7, PhoneNumber);
			ps.setString(8, "0");
			ps.setString(9, null);
			ps.setString(10, ID);
			ps.executeUpdate();
			obj.setInformation(ourcus);
			obj.setResponse(Response.ADD_CUSTOMER_DATA_SUCCESS);
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Exception-Executing statement-Add CUSTOMER_DATA_FAILD");
			obj.setResponse(Response.ADD_CUSTOMER_DATA_FAILD);
		}
	}

	public static void changeToSubscriber(MissionPack obj, Connection con) {
		PreparedStatement ps = null;
		boolean NotExist = true;
		String ID = (String) obj.getInformation();
		try {
			ps = con.prepareStatement("SELECT users.id,role FROM ekrut.users ;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (rs.getString("role").equals("customer")) {
					ps = con.prepareStatement("UPDATE ekrut.users SET role=\"subscriber\" WHERE id=?");
					ps.setString(1, ID);
					ps.executeUpdate();
					obj.setInformation(ID);
					obj.setResponse(Response.FROM_CUSTOMER_TO_SUBSCRIBER_SUCCESS);
					NotExist = false;
					break;
				}
			}
			if (NotExist) {
				obj.setResponse(Response.FROM_CUSTOMER_TO_SUBSCRIBER_FAILD);
				System.out.println("Executing statement-FROM_CUSTOMER_TO_SUBSCRIBER_FAILD");
			}

		} catch (SQLException sqlException) {
			System.out.println("Exception- Executing statement-FROM_CUSTOMER_TO_SUBSCRIBER_FAILD");
			obj.setResponse(Response.FROM_CUSTOMER_TO_SUBSCRIBER_FAILD);
		}

	}

	public static void logOut(MissionPack obj, Connection con) {
		PreparedStatement ps = null;
		String id=(String)obj.getInformation();
		obj.setResponse(Response.LOG_OUT_SUCCESS);
		try {
			ps = con.prepareStatement("UPDATE ekrut.users SET isLoggedIn=\"0\" WHERE id=?");
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			obj.setResponse(Response.LOG_OUT_FAILED);
		}
		
	}
	
	public static void CREATE_LOCAL_ORDER(MissionPack obj, Connection con) {
		int i=0;
		ProductInStock[] products=new ProductInStock[12];
		PreparedStatement ps = null;
		String store=(String)obj.getInformation();
		obj.setResponse(Response.LOCAL_ORDER_OPENED);
		try {
			ps = con.prepareStatement("SELECT productName,quantity FROM ekrut.product_stock WHERE facility=?");
			ps.setString(1, store);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products[i]=new ProductInStock(rs.getString("productName"),rs.getInt("quantity"));
				i++;
			}
			obj.setInformation(products);	
		} catch (SQLException e) {
			e.printStackTrace();
			obj.setResponse(Response.LOCAL_ORDER_NOTOPENED);
		}
		
	}

}

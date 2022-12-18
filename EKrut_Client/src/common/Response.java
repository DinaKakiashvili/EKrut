package common;

public enum Response {
	FOUND_SUBSCRIBERS("FOUND_SUBSCRIBERS", 0), 
	DIDNT_FOUND_SUBSCRIBERS("DIDNT_FOUND_SUBSCRIBERS", 1),
	EDIT_SUBSCRIBERS_SUCCESS("EDIT_SUBSCRIBERS_SUCCESS", 2), 
	EDIT_SUBSCRIBERS_FAILD("EDIT_SUBSCRIBERS_FAILD", 3),
	UPDATE_CONNECTION_SUCCESS("UPDATE_CONNECTION_SUCCESS", 4), 
	UPDATE_CONNECTION_FAILD("UPDATE_CONNECTION_FAILD", 5),
	LOGIN_SUCCEED("LOGIN_SUCCEED",6),
	LOGIN_FAILED("LOGIN_FAILED",7),
	ADD_CUSTOMER_DATA_FAILD("ADD_CUSTOMER_DATA_FAILD",8),
	ADD_CUSTOMER_DATA_SUCCESS("ADD_CUSTOMER_DATA_SUCCESS",9),
	FROM_CUSTOMER_TO_SUBSCRIBER_FAILD("FROM_CUSTOMER_TO_SUBSCRIBER_FAILD",10),
	FROM_CUSTOMER_TO_SUBSCRIBER_SUCCESS("FROM_CUSTOMER_TO_SUBSCRIBER_SUCCESS",11),
	GET_MONTHLY_ORDERS_REPORT_SUCCESS("GET_MONTHLY_ORDERS_REPORT_SUCCESS",12),
	GET_MONTHLY_ORDERS_REPORT_FAILD("GET_MONTHLY_ORDERS_REPORT_FAILD",13),
	GET_MONTHLY_STOCK_REPORT_SUCCESS("GET_MONTHLY_STOCK_REPORT_SUCCESS",14),
	GET_MONTHLY_STOCK_REPORT_FAILD("GET_MONTHLY_STOCK_REPORT_FAILD",15),
	GET_MONTHLY_CUSTOMER_REPORT_SUCCESS("GET_MONTHLY_CUSTOMER_REPORT_SUCCESS",16),
	GET_MONTHLY_CUSTOMER_REPORT_FAILD("GET_MONTHLY_CUSTOMER_REPORT_FAILD",17);

	private Response(final String mission, final int serialNumber) {
	}
}

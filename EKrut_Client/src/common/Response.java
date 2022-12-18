package common;

public enum Response {
	FOUND_SUBSCRIBERS("FOUND_SUBSCRIBERS", 0), DIDNT_FOUND_SUBSCRIBERS("DIDNT_FOUND_SUBSCRIBERS", 1),
	EDIT_SUBSCRIBERS_SUCCESS("EDIT_SUBSCRIBERS_SUCCESS", 2), EDIT_SUBSCRIBERS_FAILD("EDIT_SUBSCRIBERS_FAILD", 3),
	UPDATE_CONNECTION_SUCCESS("UPDATE_CONNECTION_SUCCESS", 4), UPDATE_CONNECTION_FAILD("UPDATE_CONNECTION_FAILD", 5),
	LOGIN_SUCCEED("LOGIN_SUCCEED",6),LOGIN_FAILED("LOGIN_FAILED",7),
	ADD_CUSTOMER_DATA_FAILD("ADD_CUSTOMER_DATA_FAILD",8),ADD_CUSTOMER_DATA_SUCCESS("ADD_CUSTOMER_DATA_SUCCESS",9),
	FROM_CUSTOMER_TO_SUBSCRIBER_FAILD("FROM_CUSTOMER_TO_SUBSCRIBER_FAILD",10),FROM_CUSTOMER_TO_SUBSCRIBER_SUCCESS("FROM_CUSTOMER_TO_SUBSCRIBER_SUCCESS",11),
	LOG_OUT_SUCCESS("LOG_OUT_SUCCESS",12),LOG_OUT_FAILED("LOG_OUT_FAILED",13),
	LOCAL_ORDER_OPENED("LOCAL_ORDER_OPENED",14),LOCAL_ORDER_NOTOPENED("LOCAL_ORDER_NOTOPENED",15),
	REMOTE_ORDER_NOTOPENED("REMOTE_ORDER_NOTOPENED",16),REMOTE_ORDER_OPENED("REMOTE_ORDER_OPENED",17);
	
	private String responeName;
	private int responeNumber;

	private Response(final String responeName, final int responeNumber) {
		this.responeName = responeName;
		this.responeNumber = responeNumber;
	}

	public String toString() {
		return this.responeName;
	}
}

package common;

public enum Response {
	FOUND_SUBSCRIBERS("FOUND_SUBSCRIBERS", 0), DIDNT_FOUND_SUBSCRIBERS("DIDNT_FOUND_SUBSCRIBERS", 1),
	EDIT_SUBSCRIBERS_SUCCESS("EDIT_SUBSCRIBERS_SUCCESS", 2), EDIT_SUBSCRIBERS_FAILD("EDIT_SUBSCRIBERS_FAILD", 3),
	UPDATE_CONNECTION_SUCCESS("UPDATE_CONNECTION_SUCCESS", 4), UPDATE_CONNECTION_FAILD("UPDATE_CONNECTION_FAILD", 5),
	LOGIN_SUCCEED("LOGIN_SUCCEED",6),LOGIN_FAILED("LOGIN_FAILED",7);

	//private String responeName;
	//private int responeNumber;

	private Response(final String responeName, final int responeNumber) {
		//this.responeName = responeName;
		//this.responeNumber = responeNumber;
	}

	/*public String toString() {
		return this.responeName;
	}*/
}
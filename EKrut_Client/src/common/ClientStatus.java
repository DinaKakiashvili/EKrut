package common;

public enum ClientStatus {
	CONNECTED("Connected", 0), 
	DISCONNECTED("Disconnected", 1);

	private ClientStatus(final String mission, final int serialNumber) {
	}
}

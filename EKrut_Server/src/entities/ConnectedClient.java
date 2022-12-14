package entities;

import common.ClientStatus;

public class ConnectedClient {
	
	private String ip;
	private String host;
	private ClientStatus status;
	
	public ConnectedClient(String ip, String host, ClientStatus status) {
		this.ip = ip;
		this.host = host;
		this.status = status;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setStatus(ClientStatus status) {
		this.status = status;
	}

	public ClientStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "[ip=" + ip + ", host=" + host + ", status=" + status + "]";
	}
}
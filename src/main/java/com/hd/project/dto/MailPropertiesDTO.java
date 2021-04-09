package com.hd.project.dto;

public class MailPropertiesDTO {

	private Long id;
	private String host;
	private String from;
	private String password;
	private String port;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailPropertiesDTO [id=").append(id).append(", host=").append(host).append(", from=")
				.append(from).append(", password=").append(password).append(", port=").append(port).append("]");
		return builder.toString();
	}

}

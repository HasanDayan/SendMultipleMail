package com.hd.project.dto;

public class MailRecordDTO {

	private Long id;
	private String mail;
	private boolean active = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailRecordDTO [id=").append(id).append(", mail=").append(mail).append(", active=")
				.append(active).append("]");
		return builder.toString();
	}

}

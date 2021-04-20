package com.hd.project.dto;

public class SentMailDTO {

	private Long id;
	private String subject;
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SentMailDTO [id=").append(id).append(", subject=").append(subject).append(", content=")
				.append(content).append("]");
		return builder.toString();
	}
	
}

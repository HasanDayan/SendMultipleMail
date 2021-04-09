package com.hd.project.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseModel implements Serializable {

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate = LocalDateTime.now();

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime updateDate = LocalDateTime.now();

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseModel [createDate=").append(createDate).append(", updateDate=").append(updateDate)
				.append("]");
		return builder.toString();
	}
	
}

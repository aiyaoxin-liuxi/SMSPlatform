package com.zhl.sms.pojo;

import java.io.Serializable;

public class LogInfo implements Serializable{
	
	private String logId;
	
	private String logCentent;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogCentent() {
		return logCentent;
	}

	public void setLogCentent(String logCentent) {
		this.logCentent = logCentent;
	}
	
	

}

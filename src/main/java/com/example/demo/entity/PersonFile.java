package com.example.demo.entity;

import java.io.Serializable;

public class PersonFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7138025847570936626L;
	private String serverUrl;
	private String serverId;
	private String imageUrl;
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}

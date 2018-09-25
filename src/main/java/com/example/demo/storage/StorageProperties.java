package com.example.demo.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";
    private String appurl = "";
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public String getWechaturl() {
		return appurl;
	}

	public void setWechaturl(String appurl) {
		this.appurl = appurl;
	}

}

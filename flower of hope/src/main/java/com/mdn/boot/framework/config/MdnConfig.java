package com.danfan.boot.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mdn")
public class MdnConfig {

	private Boolean log;

	public Boolean getLog() {
		return log;
	}

	public void setLog(Boolean log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "Mdn [log=" + log + "]";
	}
	
}

/**
 * 
 */
package com.workllama.main.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author skpradhan
 *
 */
@Component
@ConfigurationProperties("limit-service")
public class LimitConf {
	private int minimum;
	private int maximum;

	public LimitConf(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public LimitConf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

}

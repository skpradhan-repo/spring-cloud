/**
 * 
 */
package com.workllama.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.workllama.main.config.LimitConf;
import com.workllama.main.entity.LimitConfiguration;

/**
 * @author skpradhan
 *
 */
@RestController
public class LimitConfigurationController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LimitConf config;

	/**
	 * 
	 */
	public LimitConfigurationController() {
	}

	@GetMapping(path = "/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		logger.info("{}", config);
		return new LimitConfiguration(config.getMaximum(), config.getMinimum());

	}

	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not Available");
	}

	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(1, 11);
	}
}
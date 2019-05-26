/**
 * 
 */
package com.workllama.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.workllama.main.entity.ExchangeValue;
import com.workllama.main.repository.ExchangeValueRepository;

/**
 * @author skpradhan
 *
 */
@RestController
public class CurrencyExchangeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repo;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue value = repo.findByFromAndTo(from, to);
		value.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}", value);
		return value;
	}

	/*
	 * private ExchangeValue buildCurrencyExchangeValue(String from, String to) {
	 * ExchangeValue value = new ExchangeValue(1001l, from, to,
	 * BigDecimal.valueOf(65));
	 * value.setPort(Integer.parseInt(environment.getProperty("local.server.port")))
	 * ; logger.info("{}", value); return value; }
	 */
}

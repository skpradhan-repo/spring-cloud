/**
 * 
 */
package com.workllama.main.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.workllama.main.entity.CurrencyConversionBean;
import com.workllama.main.service.CurrencyExchangeServiceProxy;

/**
 * @author skpradhan
 *
 */
@RestController
public class CurrencyConversionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy serviceProxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean currencyConverter(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
		ResponseEntity<CurrencyConversionBean> reponseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariable);
		CurrencyConversionBean response = reponseEntity.getBody();
		return new CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(),
				response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), 8100);
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean currencyConverterFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConversionBean response = serviceProxy.retrieveExchangeValue(from, to);
		logger.info("{}",response);
		return new CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(),
				response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()),
				response.getPort());
	}

}

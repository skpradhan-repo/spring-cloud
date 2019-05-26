/**
 * 
 */
package com.workllama.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workllama.main.entity.ExchangeValue;

/**
 * @author skpradhan
 *
 */
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
	ExchangeValue findByFromAndTo(String from, String to);
}

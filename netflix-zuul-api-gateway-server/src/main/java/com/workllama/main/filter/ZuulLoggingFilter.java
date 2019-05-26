/**
 * 
 */
package com.workllama.main.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author skpradhan
 *
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		// Responsible to decide whether to execute this filter or not
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request ->{} request uri -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		// post, pre, error
		return "pre";
	}

	@Override
	public int filterOrder() {
		// For setting the priority order between multiple filters such as -
		// LoggingFilter,SecurityFilter,AuthorizationFilter,ExceptionFilter, etc...
		return 1;
	}

}

/**
 * Netflix Zuul API Gateway Server Logging Class
 */

package com.metrix.apigatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter extends ZuulFilter {

    /**
     *
     *
     * Logger Declaration for Zuul Logging Filter
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     *
     * Filter Method for Zuul Logging Filter
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *
     *
     * HTTP Request for Zuul Logging Filter
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
        return null;
    }

    /**
     *
     *
     * Filter Type for Zuul Logging Filter
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     *
     *
     * Filter Order for Zuul Logging Filter
     */
    @Override
    public int filterOrder() {
        return 1;
    }

}

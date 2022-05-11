package com.yu.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 访问过滤器
 *
 * @author yu
 * @date 2022-05-11 21:27
 */
@Component
public class VisitFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(VisitFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURI()));
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            logger.warn("token is empty!");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        logger.info("allow visit");
        return null;
    }
}

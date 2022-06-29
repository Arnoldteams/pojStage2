package com.cskaoyan.configuration;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author xyg2597@163.com
 * @since 2022/06/28 10:33
 */
public class MarketSessionManager extends DefaultWebSessionManager {

    private static final String MARKET_ADMIN_TOKEN = "X-CskaoyanMarket-Admin-Token";
    private static final String MARKET_WX_TOKEN = "X-CskaoyanMarket-Token";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String sessionId = httpServletRequest.getHeader(MARKET_ADMIN_TOKEN);
        if (sessionId != null & !"".equals(sessionId)) {
            return sessionId;
        }
        String sessionId2 = httpServletRequest.getHeader(MARKET_WX_TOKEN);
        if (sessionId2 != null & !"".equals(sessionId2)) {
            return sessionId2;
        }
        return super.getSessionId(request, response);
    }
}

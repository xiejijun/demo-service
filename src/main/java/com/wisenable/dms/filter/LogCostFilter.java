package com.wisenable.dms.filter;

import com.wisenable.dms.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LogCostFilter", urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "filterPath",
                value = "/user/login#/user/weixinLogin#/user/weixinBind#" +
                        "/user/registerUser#/user/randomImage#" +
                        "/platformConfig/getPlatform#/v2/api-docs#/webjars#" +
                        "/systemConfig/static#/api/plugin/wechat/weChat/share#" +
                        "/api/plugin/general-ledger/pdf/voucher")})
public class LogCostFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LogCostFilter.class);
    private static final String FILTER_PATH = "filterPath";

    private static final String OPEN_API = "/api/v1";

    private String[] allowUrls;
    @Resource
    private RedisService redisService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filterPath = filterConfig.getInitParameter(FILTER_PATH);
        if (!StringUtils.isEmpty(filterPath)) {
            allowUrls = filterPath.contains("#") ? filterPath.split("#") : new String[]{filterPath};
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String requestUrl = servletRequest.getRequestURI();
        //具体，比如：处理若用户未登录，则跳转到登录页
        Object userId = redisService.getObjectFromSessionByKey(servletRequest, "userId");

        if (userId != null) { //如果已登录，不阻止
            chain.doFilter(request, response);
            return;
        }
        if (requestUrl != null && (requestUrl.contains("/doc.html") ||
                requestUrl.contains("/user/login") || requestUrl.contains("/user/register"))) {
            chain.doFilter(request, response);
            return;
        }

        if (null != allowUrls && allowUrls.length > 0) {
            for (String url : allowUrls) {
                if (requestUrl.startsWith(url)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        if (requestUrl.startsWith(OPEN_API)) {
            // api
            log.info("Api request {}", requestUrl);
            chain.doFilter(request, response);
            return;
        }

        if ("OPTIONS".equalsIgnoreCase(servletRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        servletResponse.setStatus(500);
        if (requestUrl != null && !requestUrl.contains("/user/logout") && !requestUrl.contains("/function/findMenuByPNumber")) {
            servletResponse.getWriter().write("loginOut");
        }
    }

    @Override
    public void destroy() {

    }
}
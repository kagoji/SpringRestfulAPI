package com.kagoji.atfarestfulapis.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kagoji.atfarestfulapis.logger.AccessLogFilter;
import com.kagoji.atfarestfulapis.logger.RequestLoggingFilter;



import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/api/*");  // Define which URLs should be logged
        System.out.println("Filter Set");
        return registrationBean;
    }
	
}
*/

//@Configuration
//public class FilterConfig {
//
//	@Bean
//	public FilterRegistrationBean<AccessLogFilter> loggingFilter() {
//	    FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>();
//
//	    registrationBean.setFilter(new AccessLogFilter());
//	    registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs
//
//	    return registrationBean;
//	}
//}
package com.piyoro.personalweb.common.config;

import com.piyoro.personalweb.common.filter.RequestFilter;
import com.piyoro.personalweb.common.filter.TrxBaseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean
	public FilterRegistrationBean<RequestFilter> getRequestFilterRegistrationBean() {
		FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new RequestFilter());
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<TrxBaseFilter> getTrxBaseFilterRegistrationBean() {
		FilterRegistrationBean<TrxBaseFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TrxBaseFilter());
		registrationBean.setOrder(2);
		return registrationBean;
	}

}

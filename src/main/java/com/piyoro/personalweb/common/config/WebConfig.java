package com.piyoro.personalweb.common.config;

import com.piyoro.personalweb.common.filter.RequestFilter;
import com.piyoro.personalweb.common.filter.TrxBaseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

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

	/**
	 * validation 용 메세지 소스
	 * @return
	 */
	@Bean
	public MessageSource validationMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:/messages/validator/validation");
//		messageSource.addBasenames("classpath:/ValidationMessages");
		// classpath:/messages/validator/validation 커스텀 메세지 프로퍼티를 사용하지 않는다면
		// classpath:/ValidationMessages 는 추가해줄 필요가 없지만
		// 이 messageSource 를 이용하면 추가해줘야한다.
		messageSource.addBasenames("classpath:/ValidationMessages", "classpath:/messages/validator/validation");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
		return messageSource;
	}

	/**
	 * validator factory
	 * classpath 에 ValidationMessages.properties 를 따로 두어 기본 메세지를 재정의하면
	 * 변경된 메세지가 적용되지만, LocalValidatorFactoryBean 에 ValidationMessageSource 를 따로 설정하면
	 * 메세지 재정의 기능이 동작하지 않는다.
	 * @return
	 */
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validationMessageSource());
		return bean;
	}
}

package com.metrix.apigatewayserver.config;

import com.metrix.apigatewayserver.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// For implementing security on api endpoints
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().and().antMatcher("/**").authorizeRequests().anyRequest()
				.authenticated().and().addFilterAfter(new JwtFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests();
	}

	// For ignoring security of some api endpoints
	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/", "/assets/**", "/*.js", "/*.css", "/*.eot", "/*.svg", "/*.woff2",
				"/*.ttf", "/*.woff", "/*.jpg", "/*.png", "/*.html", "/user/api/v1/googlelogin",
				"/user/api/v1/gitlablogin", "/user/api/v1/clientdashboard", "/user/api/v1/admindashboard",
				"/stream/api/v1/**","/metrixLC1.png","/websocket/**");
	}
}

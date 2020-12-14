package com.pelucasistemas.anoringa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
public class AnoringaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnoringaApplication.class, args);
	}
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// Don't do this in production, use a proper list  of allowed origins
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	// added for postgresql
	/*
	@Bean
	public DataSource dataSource(){
		System.out.println(driverClass+" "+ url+" "+username+" "+password);
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(driverClass);
		source.setUrl(url);
		source.setUsername(username);
		source.setPassword(password);
		return source;
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
		return namedParameterJdbcTemplate;
	}
	 */
}

package com.java.example.tanzu.wherefordinner.trafficgen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.java.example.tanzu.wherefordinner.trafficgen.exchange.AvailabilityClient;
import com.java.example.tanzu.wherefordinner.trafficgen.exchange.SearchClient;
import com.java.example.tanzu.wherefordinner.trafficgen.exchange.UIClient;


@Configuration
public class DeclarativeClientConfig 
{
	@Value("${where-for-dinner.search.service.identifier:}")
	protected String serviceServiceIdentifier;
	
	@Value("${where-for-dinner.ui.service.identifier:}")
	protected String uiServiceIdentifier;	
	
	@Value("${where-for-dinner.availability.service.identifier:}")
	protected String availabilityServiceIdentifier;		
	
	@Bean
	public WebClient.Builder webClientBuilder() 
	{
	    return WebClient.builder();
	 }
	
	@Bean
	public SearchClient getSearchClient(WebClient.Builder builder)
	{
		final var client = builder.baseUrl(serviceServiceIdentifier).build();
		final var factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();

		return factory.createClient(SearchClient.class);
	}
	
	@Bean
	public UIClient getUIClient(WebClient.Builder builder)
	{
		final var client = builder.baseUrl(uiServiceIdentifier).build();
		final var factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();

		return factory.createClient(UIClient.class);
	}
	
	@Bean
	public AvailabilityClient getAvialabilityClient(WebClient.Builder builder)
	{
		final var client = builder.baseUrl(availabilityServiceIdentifier).build();
		final var factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();

		return factory.createClient(AvailabilityClient.class);
	}
}

package com.java.example.tanzu.wherefordinner.trafficgen.exchange;

import org.springframework.web.service.annotation.GetExchange;

import reactor.core.publisher.Mono;


public interface UIClient 
{
	@GetExchange("/search")
	public Mono<String> getLandingPage();
}

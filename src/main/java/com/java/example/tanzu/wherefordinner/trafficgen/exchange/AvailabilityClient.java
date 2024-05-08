package com.java.example.tanzu.wherefordinner.trafficgen.exchange;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.java.example.tanzu.wherefordinner.trafficgen.model.Availability;

import reactor.core.publisher.Flux;

public interface AvailabilityClient 
{
	@GetExchange("/availability/{searchName}")
	public Flux<Availability> getSearchAvailabilty(@PathVariable("searchName") String searchName);
}

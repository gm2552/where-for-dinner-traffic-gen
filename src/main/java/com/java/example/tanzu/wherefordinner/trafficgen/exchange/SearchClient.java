package com.java.example.tanzu.wherefordinner.trafficgen.exchange;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.java.example.tanzu.wherefordinner.trafficgen.model.Search;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchClient 
{	
	@GetExchange("/search")
	public Flux<Search> getAllSearches();
	
	@PutExchange("/search")  
	public Mono<Search> addSearch(@RequestBody Search search);
	
	@DeleteExchange("/search/{id}") 
	public Mono<Void> deleteSearch(@PathVariable("id") Long id);
}

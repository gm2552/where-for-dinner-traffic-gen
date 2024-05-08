package com.java.example.tanzu.wherefordinner.trafficgen.task;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.java.example.tanzu.wherefordinner.trafficgen.exchange.AvailabilityClient;
import com.java.example.tanzu.wherefordinner.trafficgen.exchange.SearchClient;
import com.java.example.tanzu.wherefordinner.trafficgen.exchange.UIClient;
import com.java.example.tanzu.wherefordinner.trafficgen.model.Search;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadTask {

	@Autowired
	protected UIClient uiClient;
	
	@Autowired
	protected SearchClient searchClient;
	
	@Autowired
	protected AvailabilityClient availClient;
	
	@Scheduled(initialDelayString="${where-for-dinner.load-task.initial-delay}", fixedDelayString="${where-for-dinner.load-task.interval}")
	public void runLoad()
	{
		log.info("Starting scheduled load task");
		
		// Hit the UI first to generate network load to the UI workload
		uiClient.getLandingPage();
		
		// create a single search
		var search = new Search();
		
		search.setName(UUID.randomUUID().toString());
		search.setPostalCode("58349");
		search.setRadius(10);
		search.setStartTime(Instant.now().toEpochMilli());
		search.setEndTime(Instant.now().plus(24, ChronoUnit.HOURS).toEpochMilli());
		
		log.info("Creating random search with name {}", search.getName());
		
		var addedSearch = searchClient.addSearch(search).block();
		
		// Sleep to let the search get processed
		log.info("Sleeping 5 seconds to let some search process.");
		
		try 
		{
		    TimeUnit.SECONDS.sleep(5);
		} 
		catch (InterruptedException ie) 
		{
		    Thread.currentThread().interrupt();
		}
		
		// get availability for the search
		log.info("Waking up and checking for availability.");
		
		availClient.getSearchAvailabilty(search.getName()).collectList().block();
		
		// clean up
		log.info("Cleaning up and delete search {}", search.getName());
		
		searchClient.deleteSearch(addedSearch.getId()).block();
	}
}

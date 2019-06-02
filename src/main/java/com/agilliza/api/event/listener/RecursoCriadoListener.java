package com.agilliza.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agilliza.api.event.ApiAplicationEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<ApiAplicationEvent> {

	@Override
	public void onApplicationEvent(ApiAplicationEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		
		adicioanarHeaderLocation(response, id);
	}

	private void adicioanarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}

package com.devsuperior.demo.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventServices service;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_CLIENT')")
	public EventDTO adiciona(@Valid @RequestBody EventDTO dto) {
		return service.adiciona(dto);
	}
	
	@GetMapping
	public Page<EventDTO> adiciona(Pageable pageable) {
		return service.lista(pageable);
	}
}

package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public List<CityDTO> lista() {
	    List<City> ListEntity = repository.findAll(Sort.by("name"));
		return ListEntity.stream().map(city -> new CityDTO(city)).toList();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public CityDTO adiciona(CityDTO dto) {		
		City city = new City();
		city.setName(dto.getName());
		return new CityDTO(repository.save(city));
	}

	
	
	
}

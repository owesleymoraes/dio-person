package one.digitalinnovation.personapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.personapi.exeception.PersonNotFoundExcepition;
import one.digitalinnovation.personapi.request.dto.PersonDTO;
import one.digitalinnovation.personapi.response.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {

		this.personService = personService;
	}
   
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@Valid @RequestBody PersonDTO personDto) {
		return personService.createPerson(personDto);

	}
	
	@GetMapping
	public List<PersonDTO> listAll(){
		return personService.listAll();
	}
	
	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundExcepition {
		return personService.findById(id);
	}
	
	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDto) throws PersonNotFoundExcepition {
		
		return personService.updateById(id , personDto);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws PersonNotFoundExcepition {
		
		
		personService.delete(id);
		
	}

}

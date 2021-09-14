package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exeception.PersonNotFoundExcepition;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.request.dto.PersonDTO;
import one.digitalinnovation.personapi.response.dto.MessageResponseDTO;

@Service
public class PersonService {

	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	public MessageResponseDTO createPerson(PersonDTO personDto) {

		Person personToSave = personMapper.toModel(personDto);

		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO.builder().
				message("Created person with Id" + savedPerson.getId()).build();
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople.stream().
				map(personMapper::toDTO).
				collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundExcepition {
		
		Optional<Person> optionalPerson = personRepository.findById(id);
		if (optionalPerson.isEmpty()) {
			throw new PersonNotFoundExcepition(id);

		}

		return personMapper.toDTO(optionalPerson.get());
	}
	

	public void delete(Long id) throws PersonNotFoundExcepition {
		
		Optional<Person> personById = personRepository.findById(id);
		
		if(personById.isEmpty()) {
			
			throw new PersonNotFoundExcepition(id);
		} else {
			personRepository.deleteById(id);
		}
		
	

	}

	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDto) 
			throws PersonNotFoundExcepition {
		
			Optional<Person> optionalPerson = personRepository.findById(id);
			
			if(optionalPerson.isEmpty()) {
				throw new PersonNotFoundExcepition(id);
			} 
		
		Person personToUpdate = personMapper.toModel(personDto);

		Person updatePerson = personRepository.save(personToUpdate);
		return MessageResponseDTO.builder().
				message("Update person with Id" + updatePerson.getId()).build();
			
	}

}

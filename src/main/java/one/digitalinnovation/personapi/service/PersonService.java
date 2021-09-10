package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.bytebuddy.implementation.bytecode.Throw;
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
		return MessageResponseDTO.builder().message("Created person with Id" + savedPerson.getId()).build();
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople.stream().
				map(personMapper::toDTO).
				collect(Collectors.toList());
	}

	

	public PersonDTO findById(Long id) throws PersonNotFoundExcepition {
	Optional<Person> optionalPerson = personRepository.findById(id);
	if(optionalPerson.isEmpty()) {
		throw new PersonNotFoundExcepition(id); 
		
	}
		
		return personMapper.toDTO(optionalPerson.get());
	}

}

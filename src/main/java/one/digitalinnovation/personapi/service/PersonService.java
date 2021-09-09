package one.digitalinnovation.personapi.service;

import org.springframework.stereotype.Service;
import one.digitalinnovation.personapi.entity.Person;
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

}

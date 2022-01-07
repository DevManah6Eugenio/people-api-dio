package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person personSave = personRepository.save(person);
        return MessageResponseDTO.builder()
                .Message("Created person with ID " + personSave.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPerson = personRepository.findAll();
        return allPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
         return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}

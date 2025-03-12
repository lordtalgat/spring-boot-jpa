package talgat.home.service.impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import talgat.home.dto.PersonDto;
import talgat.home.entity.Person;
import talgat.home.exception.ResourceNotFoundException;
import talgat.home.mapper.PersonMapper;
import talgat.home.repository.PersonRepository;
import talgat.home.service.PersonService;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonRepository personRepository;

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = PersonMapper.mapToPerson(personDto);
        Person personSaved = personRepository.save(person);
        LOGGER.info("Person saved: {}", personSaved);
        return PersonMapper.mapToPersonDto(personSaved);
    }

    @Override
    public PersonDto getPerson(Long id) {
        Person person = findPerson(id);
        return PersonMapper.mapToPersonDto(person);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        var persons = personRepository.findAll();
        return persons.stream().map(x -> PersonMapper.mapToPersonDto(x)).toList();
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Person person = findPerson(id);
        person.setAddress(personDto.address());
        person.setAvatarUrl(personDto.avatarUrl());
        Person personSaved = personRepository.save(person);
        LOGGER.info("Person updated: {}", personSaved);
        return PersonMapper.mapToPersonDto(personSaved);
    }

    @Override
    public void deletePerson(Long id) {
        findPerson(id);
        personRepository.deleteById(id);
        LOGGER.info("Person deleted: {}", id);
    }

    private Person findPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not Found : " + id));
    }
}

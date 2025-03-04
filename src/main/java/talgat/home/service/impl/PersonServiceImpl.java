package talgat.home.service.impl;

import lombok.AllArgsConstructor;
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

    private PersonRepository personRepository;

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = PersonMapper.mapToPerson(personDto);
        Person personSaved = personRepository.save(person);
        return PersonMapper.mapToPersonDto(personSaved);
    }

    @Override
    public PersonDto getPerson(Long id) {
        Person person = findPerson(id);
        return PersonMapper.mapToPersonDto(person);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(x -> PersonMapper.mapToPersonDto(x)).toList();
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Person person = findPerson(id);
        person.setAddress(personDto.getAddress());
        person.setAvatarUrl(personDto.getAvatarUrl());
        Person personSaved = personRepository.save(person);
        return PersonMapper.mapToPersonDto(personSaved);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    private Person findPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found : " + id));
    }
}

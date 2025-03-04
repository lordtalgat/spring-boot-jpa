package talgat.home.service;

import talgat.home.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto createPerson(PersonDto employeeDto);

    PersonDto getPerson(Long id);

    List<PersonDto> getAllPersons();

    PersonDto updatePerson(Long id, PersonDto employeeDto);

    void deletePerson(Long id);
}

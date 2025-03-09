package talgat.home.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import talgat.home.dto.PersonDto;
import talgat.home.entity.Employee;
import talgat.home.entity.Person;
import talgat.home.repository.PersonRepository;
import talgat.home.service.impl.PersonServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSuccessfullyGetPerson() {
        Employee employee = new Employee();
        employee.setId(1L);
        Person person = new Person(1L, "Dostyk 3, Almaty, KZ", "https://egov.kz/img.jpg", employee);
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        PersonDto dto = personService.getPerson(person.getId());
        assertNotNull(dto);
        assertEquals(person.getId(), dto.id());
        assertEquals(person.getAvatarUrl(), dto.avatarUrl());
        assertEquals(person.getAddress(), dto.address());
        verify(personRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldSuccessfullyGetAllPersons() {
        Employee employee1 = new Employee();
        employee1.setId(1L);

        Employee employee2 = new Employee();
        employee2.setId(2L);

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(employee1.getId(), "Dostyk 3, Almaty, KZ", "https://egov.kz/img1.jpg", employee1));
        persons.add(new Person(employee2.getId(), "Dostyk 4, Almaty, KZ", "https://egov.kz/img2.jpg", employee2));
        when(personRepository.findAll()).thenReturn(persons);
        List<PersonDto> dtos = personService.getAllPersons();
        assertNotNull(dtos);
        assertEquals(persons.size(), dtos.size());
        assertEquals(persons.get(0).getId(), dtos.get(0).id());
        assertEquals(persons.get(1).getId(), dtos.get(1).id());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void shouldSuccessfullyUpdateOrCreateCompany() {
        Employee employee = new Employee();
        employee.setId(1L);
        PersonDto dto = new PersonDto(1L, "Dostyk 3, Almaty, KZ", "https://egov.kz/img1.jpg", 1L);
        Person person = new Person(1L, "Dostyk 3, Almaty, KZ", "https://egov.kz/img1.jpg", employee);
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);
        PersonDto savedDto = personService.createPerson(dto);
        assertNotNull(savedDto);
        assertEquals(person.getId(), savedDto.id());
        assertEquals(person.getAvatarUrl(), savedDto.avatarUrl());
        assertEquals(person.getAddress(), savedDto.address());
        verify(personRepository, times(1)).save(any(Person.class));

    }
}
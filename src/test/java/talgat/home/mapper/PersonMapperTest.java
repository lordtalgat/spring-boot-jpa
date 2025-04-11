package talgat.home.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import talgat.home.dto.PersonDto;
import talgat.home.entity.Employee;
import talgat.home.entity.Person;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Test
    void shouldMapPersonDtoToPerson() {
        PersonDto dto = new PersonDto(1L, "Dostyk 3, Almaty, KZ", "https://egov.kz/img.jpg", 1L);
        Person person = PersonMapper.mapToPerson(dto);

        assertNotNull(person);
        assertEquals(dto.address(), person.getAddress());
        assertEquals(dto.avatarUrl(), person.getAvatarUrl());
        assertEquals(dto.employeeId(), person.getEmployee().getId());
    }

    @Test
    void shouldMapPersonToPersonDto() {
        Person person = new Person();
        person.setId(1L);
        person.setAddress("Dostyk 3, Almaty, KZ");
        person.setAvatarUrl("https://egov.kz/img.jpg");
        Employee employee = new Employee();
        employee.setId(1L);
        person.setEmployee(employee);
        PersonDto dto = PersonMapper.mapToPersonDto(person);

        assertNotNull(dto);
        assertEquals(person.getId(), dto.id());
        assertEquals(person.getAddress(), dto.address());
        assertEquals(person.getAvatarUrl(), dto.avatarUrl());
        assertEquals(person.getEmployee().getId(), dto.employeeId());
    }
}
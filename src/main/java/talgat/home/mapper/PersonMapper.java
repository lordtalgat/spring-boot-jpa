package talgat.home.mapper;

import talgat.home.dto.PersonDto;
import talgat.home.entity.Employee;
import talgat.home.entity.Person;

public class PersonMapper {
    public static PersonDto mapToPersonDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getAddress(),
                person.getAvatarUrl(),
                person.getEmployee().getId()
        );
    }

    public static Person mapToPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.id());
        person.setAddress(personDto.address());
        person.setAvatarUrl(personDto.avatarUrl());
        Employee employee = new Employee();
        employee.setId(personDto.employeeId());
        person.setEmployee(employee);
        return person;
    }
}

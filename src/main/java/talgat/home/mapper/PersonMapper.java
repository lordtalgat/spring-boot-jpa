package talgat.home.mapper;

import talgat.home.dto.PersonDto;
import talgat.home.entity.Person;

public class PersonMapper {
    public static PersonDto mapToPersonDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getAddress(),
                person.getAvatarUrl()
        );
    }

    public static Person mapToPerson(PersonDto personDto) {
        return new Person(
                personDto.getId(),
                personDto.getAddress(),
                personDto.getAvatarUrl()
        );
    }
}

package talgat.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talgat.home.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}

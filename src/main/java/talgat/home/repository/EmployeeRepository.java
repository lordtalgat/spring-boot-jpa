package talgat.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talgat.home.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByEmailContainingIgnoreCase(String email);
}

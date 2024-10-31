package talgat.home.ems_spring_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talgat.home.ems_spring_hibernate.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

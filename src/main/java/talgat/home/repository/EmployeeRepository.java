package talgat.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talgat.home.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

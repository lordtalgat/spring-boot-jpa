package talgat.home.mapper;

import talgat.home.dto.EmployeeDto;
import talgat.home.entity.Company;
import talgat.home.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee is null");
        }
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getCompany().getId()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new NullPointerException("Employee Dto is null");
        }
        Employee employee = new Employee();
        employee.setId(employeeDto.id());
        employee.setFirstName(employeeDto.firstName());
        employee.setLastName(employeeDto.lastName());
        employee.setEmail(employeeDto.email());
        Company company = new Company();
        company.setId(employeeDto.companyId());
        employee.setCompany(company);
        return employee;
    }
}

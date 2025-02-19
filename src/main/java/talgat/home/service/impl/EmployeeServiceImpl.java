package talgat.home.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import talgat.home.dto.EmployeeDto;
import talgat.home.entity.Employee;
import talgat.home.exception.ResourceNotFoundException;
import talgat.home.mapper.EmployeeMapper;
import talgat.home.repository.EmployeeRepository;
import talgat.home.service.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = findEmployee(id);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(e -> EmployeeMapper.mapToEmployeeDto(e)).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = findEmployee(id);

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        findEmployee(id);
        employeeRepository.deleteById(id);
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found : " + id));
    }
}

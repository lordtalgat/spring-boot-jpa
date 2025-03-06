package talgat.home.dto;



public record EmployeeDto (
    Long id,
    String firstName,
    String lastName,
    String email,
    Integer companyId
) {}

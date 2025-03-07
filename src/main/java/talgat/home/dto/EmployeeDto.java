package talgat.home.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record EmployeeDto (
    Long id,
    @NotEmpty(message = "Firstname should not be empty")
    String firstName,
    @NotEmpty(message = "Lastname should not be empty")
    String lastName,
    @Email(message = "Email should be correct")
    String email,
    @NotEmpty
    Integer companyId
) {}

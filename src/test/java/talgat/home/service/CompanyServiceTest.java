package talgat.home.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import talgat.home.dto.CompanyDto;
import talgat.home.entity.Company;
import talgat.home.repository.CompanyRepository;
import talgat.home.service.impl.CompanyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CompanyServiceTest {

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSuccessfullyGetCompany() {
        Integer companyId = 1;
        var company = new Company(1, "Apple", null);
        when(companyRepository.findById(companyId.longValue())).thenReturn(Optional.of(company));

        CompanyDto dto = companyService.getCompany(companyId);
        assertEquals(dto.id(), company.getId());
        assertEquals(dto.name(), company.getName());
        verify(companyRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldSuccessfullyGetAllCompanies() {
        Company company1 = new Company(1, "Apple", null);
        Company company2 = new Company(1, "IBM", null);
        Company company3 = new Company(1, "TSMC", null);

        var companies = List.of(company1, company2, company3);
        when(companyRepository.findAll()).thenReturn(companies);

        var listDto = companyService.getCompanies();
        assertEquals(listDto.size(), companies.size());
        assertEquals(listDto.get(0).id(), company1.getId());
        assertEquals(listDto.get(0).name(), company1.getName());
        assertEquals(listDto.get(1).id(), company2.getId());
        assertEquals(listDto.get(1).name(), company2.getName());
        assertEquals(listDto.get(2).id(), company3.getId());
        assertEquals(listDto.get(2).name(), company3.getName());
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void shouldSuccessfullyUpdateOrCreateCompany() {
        CompanyDto dto = new CompanyDto(1, "Apple");
        Company company = new Company(1, "Apple", null);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        CompanyDto createdDto = companyService.createCompany(dto);
        assertEquals(createdDto.id(), dto.id());
        assertEquals(createdDto.name(), dto.name());
        verify(companyRepository, times(1)).save(any(Company.class));

    }
}
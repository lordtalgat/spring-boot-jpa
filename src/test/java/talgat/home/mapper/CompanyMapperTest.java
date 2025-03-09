package talgat.home.mapper;

import org.junit.jupiter.api.Test;
import talgat.home.dto.CompanyDto;
import talgat.home.entity.Company;

import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest {

    @Test
    void shouldMapCompanyDtoToCompany() {
        CompanyDto dto = new CompanyDto(1, "Apple");
        Company company = CompanyMapper.toCompany(dto);

        assertNotNull(company);
        assertEquals(dto.name(), company.getName());
    }

    @Test
    void shouldMapCompanyToCompanyDto() {
        Company company = new Company();
        company.setName("Apple");
        company.setId(1);
        CompanyDto dto = CompanyMapper.toCompanyDto(company);

        assertNotNull(dto);
        assertEquals(dto.name(), company.getName());
        assertEquals(dto.id(), company.getId());
    }
}
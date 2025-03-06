package talgat.home.mapper;

import talgat.home.dto.CompanyDto;
import talgat.home.entity.Company;

public class CompanyMapper {
    public static CompanyDto toCompanyDto(Company company) {
            return new CompanyDto(company.getId(),
                    company.getName());
    }

    public static Company toCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.name());
        return company;
    }
}

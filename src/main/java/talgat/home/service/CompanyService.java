package talgat.home.service;

import talgat.home.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto createCompany(CompanyDto companyDto);

    CompanyDto updateCompany(Integer id, CompanyDto companyDto);

    CompanyDto getCompany(Integer id);

    List<CompanyDto> getCompanies();

    void deleteCompany(Integer id);
}

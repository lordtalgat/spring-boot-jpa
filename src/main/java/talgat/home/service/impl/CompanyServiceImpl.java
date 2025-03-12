package talgat.home.service.impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import talgat.home.dto.CompanyDto;
import talgat.home.entity.Company;
import talgat.home.exception.ResourceNotFoundException;
import talgat.home.mapper.CompanyMapper;
import talgat.home.repository.CompanyRepository;
import talgat.home.service.CompanyService;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private CompanyRepository companyRepository;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.toCompany(companyDto);
        Company companySaved = companyRepository.save(company);
        LOGGER.info("Company saved: {}", companySaved);
        return CompanyMapper.toCompanyDto(companySaved);
    }

    @Override
    public CompanyDto updateCompany(Integer id, CompanyDto companyDto) {
        Company company = findCompany(id.longValue());
        company.setName(companyDto.name());
        Company companySaved = companyRepository.save(company);
        LOGGER.info("Company updated: {}", companySaved);
        return CompanyMapper.toCompanyDto(companySaved);
    }

    @Override
    public CompanyDto getCompany(Integer id) {
        return CompanyMapper.toCompanyDto(findCompany(id.longValue()));
    }

    @Override
    public List<CompanyDto> getCompanies() {
        return companyRepository.findAll().stream().map(x -> CompanyMapper.toCompanyDto(x)).toList();
    }

    @Override
    public void deleteCompany(Integer id) {
        findCompany(id.longValue());
        companyRepository.deleteById(id.longValue());
        LOGGER.info("Company deleted: {}", id);
    }

    private Company findCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not Found : " + id));
    }
}

package talgat.home.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.home.dto.CompanyDto;
import talgat.home.service.CompanyService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
       CompanyDto savedCompany = companyService.createCompany(companyDto);
       return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company Deleted");
    }
}

package talgat.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talgat.home.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}

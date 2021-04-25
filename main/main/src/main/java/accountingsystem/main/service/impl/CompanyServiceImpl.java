package accountingsystem.main.service.impl;

import accountingsystem.main.exceptions.CompanyNotFoundException;
import accountingsystem.main.model.Company;
import accountingsystem.main.model.Product;
import accountingsystem.main.model.User;
import accountingsystem.main.model.WorkService;
import accountingsystem.main.repository.CompanyRepository;
import accountingsystem.main.service.CompanyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public List<Company> findByNameLike(String name) {
        return this.companyRepository.findAllByNameLike(name);

    }

    @Override
    public Company save(String name,
                        String founder,
                        String address,
                        LocalDateTime incorporationDate,
                        String taxNumber,
                        String registeredNumber,
                        User user) {
        Company company = new Company(
                name,
                founder,
                address,
                incorporationDate,
                taxNumber,
                registeredNumber,
                user
        );
        return companyRepository.save(company);
    }

    @Override
    public Company deleteById(Long Id) {
        Company company = this.companyRepository.findById(Id).orElseThrow(CompanyNotFoundException::new);
        this.deleteById(Id);
        return company;
    }

    @Override
    public List<Company> findByUser(User user) {
      return this.companyRepository.findAllByUser(user);
    }

    @Override
    public Company save(Company company) {
        return this.companyRepository.save(company);
    }
}

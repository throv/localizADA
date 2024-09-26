package tech.ada.localizada.service.company;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.repository.company.CompanyRepository;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private int id = 1;

    @Override
    public void addCompany(Company company) {
        if (companyRepository.findCompanyByCNPJ(company.getCnpj()).isEmpty()) {
            company.setId(id++);
            companyRepository.save(company);
        } else {
            System.err.println("There is already a company with this CNPJ.");
        }
    }

    @Override
    public Company updateCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findById(company.getId());

        if (existingCompany.isPresent()) {
            return companyRepository.save(company);
        } else {
            System.err.println("Company not found.");
            return null;
        }
    }

    @Override
    public List<Company> findCompanyByName(String name) {
        return companyRepository.findCompanyByName(name);
    }

    @Override
    public List<Company> findCompanyByAddress(String address) {
        return companyRepository.findCompanyByAddress(address);
    }

    @Override
    public Optional<Company> findCompanyByCNPJ(String cnpj) {
        return companyRepository.findCompanyByCNPJ(cnpj);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}

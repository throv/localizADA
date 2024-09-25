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

    @Override
    public void addCompany(Company company) {
        companyRepository.addCompany(company);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.updateCompany(company);
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
        return companyRepository.getAllCompanies();
    }
}

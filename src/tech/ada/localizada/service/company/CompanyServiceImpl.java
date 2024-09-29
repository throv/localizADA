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
    public Company updateCompany(int id, Company company) {
        Optional<Company> existingCompany = companyRepository.findById(id);

        if (existingCompany.isEmpty()) {
            throw new IllegalArgumentException("Client with ID " + id + " was not found.");
        }

        Company companyToUpdate = existingCompany.get();

        companyToUpdate.setName(company.getName());
        companyToUpdate.setCnpj(company.getCnpj());
        companyToUpdate.setAddress(company.getAddress());
        companyToUpdate.setCity(company.getCity());

        return companyRepository.save(companyToUpdate);
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

    @Override
    public void printCompanies() {
        List<Company> companies = companyRepository.findAll();
        for (Company company : companies) {
            System.out.print(company);
        }
    }
}

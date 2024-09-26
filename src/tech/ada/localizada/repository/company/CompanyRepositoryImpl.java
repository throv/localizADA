package tech.ada.localizada.repository.company;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyRepositoryImpl extends RepositoryImpl<Company, Integer> implements CompanyRepository {

    private final List<Company> companies = new ArrayList<>();
    private int id = 1;

    @Override
    public void addCompany(Company company) {
        if (findCompanyByCNPJ(company.getCnpj()).isEmpty()) {
            company.setId(id++);
            companies.add(company);
        } else {
            System.err.println("There is already a company with this CNPJ.");
        }
    }

    @Override
    public void updateCompany(Company company) {
        Optional<Company> existingCompany = findById(company.getId());
        if (existingCompany.isPresent()) {
            companies.remove(existingCompany.get());
            companies.add(company);
        } else {
            System.err.println("Company not found.");
        }
    }

    @Override
    public List<Company> findCompanyByName(String name) {
        return companies.stream()
                .filter(company -> company.getName().toUpperCase().contains(name.toUpperCase()))
                .toList();
    }

    @Override
    public List<Company> findCompanyByAddress(String address) {
        return companies.stream()
                .filter(company -> company.getAddress().toUpperCase().contains(address.toUpperCase()))
                .toList();
    }

    @Override
    public Optional<Company> findCompanyByCNPJ(String cnpj) {
        return companies.stream().
                filter(company -> company.getCnpj().equals(cnpj))
                .findFirst();
    }

    @Override
    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies);
    }

    @Override
    protected List<Company> getList() {
        return List.of();
    }

    @Override
    protected Integer getId(Company entity) {
        return entity.getId();
    }
}

package tech.ada.localizada.repository.company;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyRepositoryImpl extends RepositoryImpl<Company, Integer> implements CompanyRepository {

    public final List<Company> companies = new ArrayList<>();

    @Override
    protected List<Company> getList() {
        return companies;
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
    protected Integer getId(Company entity) {
        return entity.getId();
    }
}

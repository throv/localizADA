package tech.ada.localizada.service.company;

import tech.ada.localizada.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    void addCompany(Company company);
    Company updateCompany(int id, Company company);
    List<Company> findCompanyByName(String name);
    List<Company> findCompanyByAddress(String address);
    Optional<Company> findCompanyByCNPJ(String cnpj);
    List<Company> getAllCompanies();

}

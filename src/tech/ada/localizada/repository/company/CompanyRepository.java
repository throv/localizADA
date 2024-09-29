package tech.ada.localizada.repository.company;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Integer> {

    List<Company> findCompanyByName(String name);
    List<Company> findCompanyByAddress(String address);
    Optional<Company> findCompanyByCNPJ(String cnpj);
}

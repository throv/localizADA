package tech.ada.localizada.Test;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.repository.company.CompanyRepository;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
import tech.ada.localizada.repository.vehicle.VehicleRepositoryImpl;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.company.CompanyServiceImpl;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;
import tech.ada.localizada.view.VehicleSubMenu;

public class MainVehicle {

    public static void main(String[] args) {

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);

        Company company1 = new Company ("Eldorado", "Rua Joao", "Suzao","123");

        companyService.addCompany(company1);

        System.out.println(companyRepository.findAll());

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();
        VehicleServiceImpl vehicleService = new VehicleServiceImpl(vehicleRepository);
        VehicleSubMenu vehicleSubMenu = new VehicleSubMenu(vehicleService, companyService);

        vehicleSubMenu.startMenuVehicle();

    }
}

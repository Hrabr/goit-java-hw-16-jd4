package goit_it.command;

import goit_it.model.Companies;
import goit_it.model.Customers;
import goit_it.model.Developers;
import goit_it.model.Projects;
import goit_it.repository.*;
import goit_it.view.View;

public class Update {
    View view;
    DevelopersRepository developersRepository;
    SkillRepository skillRepository;
    CompanyRepository companyRepository;
    CustomersRepositorty customersRepositorty;
    ProjectsRepository projectsRepository;

    public Update(View view, DevelopersRepository developersRepository, SkillRepository skillRepository, CompanyRepository companyRepository, CustomersRepositorty customersRepositorty, ProjectsRepository projectsRepository) {
        this.view = view;
        this.developersRepository = developersRepository;
        this.skillRepository = skillRepository;
        this.companyRepository = companyRepository;
        this.customersRepositorty = customersRepositorty;
        this.projectsRepository = projectsRepository;
    }

    public void executeUpdateDeveloper() {
        Developers developers = new Developers();
        String name;
        int id;
        while (true) {
            view.write("Enter name of Developer where  you want to change the date");
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id = skillRepository.checkDeveloperId(name)) == 0) {
                view.write("This Developer not exist into table Developers.");

            } else break;
        }
        developers.setId_developers(id);
        String newName;
        while (true) {
            view.write("Enter new name Developer");
            newName = view.read();
            if (newName.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }
        developers.setName(newName);
        view.write("Enter age for change of Developer");
        while (true) {
            try {
                developers.setAge(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number");
            }
        }
        view.write("Enter gender for change  of Developer");
        while (true) {
            String gender = view.read();
            if (gender.equals("male") || gender.equals("female")) {
                developers.setGender(gender);
                break;
            } else {
                view.write("You entered the wrong gender.Please, enter correct gender ");
            }
        }
        view.write("Enter salary for change of Developer");
        while (true) {
            try {
                developers.setSalary(Double.parseDouble(view.read()));
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        String check = (developersRepository.UpdateDevelopers(developers) > 0) ? "Developers update" : "Developers not update";
        view.write(check);
    }

    public void executeUpdateCompany() {
        Companies companies = new Companies();
        view.write("Enter name of Company where  you want to change the date");
        String name;
        int id;
        while (true) {
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id = projectsRepository.checkCompany(name)) == 0) {
                view.write("This name Company not exist");
            } else break;
        }
        companies.setId_companies(id);
        view.write("Enter name of Company for change");
        String newName;

        while (true) {
            newName = view.read();
            if (newName.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }
        companies.setName_companies(newName);
        view.write("Enter Country of Companies");
        String country;
        while (true) {
            country = view.read();
            if (country.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }

        companies.setCountry_companies(country);
        String check = (companyRepository.updateComanies(companies) > 0) ? "Company update" : "Company not update";
        view.write(check);
    }

    public void executeUpdateCustomers() {
        Customers customers = new Customers();
        view.write("Enter name of Customer where  you want to change the date");
        String name;
        int id;
        while (true) {
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id = projectsRepository.checkCustomers(name)) == 0) {
                view.write("This name Customer not exist");
            } else break;
        }
        customers.setId_customers(id);
        view.write("Enter name of Customer for change");
        String newName;
        while (true) {
            newName = view.read();
            if (newName.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }
        customers.setName_customers(newName);
        view.write("Enter Country of Customer");
        String country;
        while (true) {
            country = view.read();
            if (country.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }
        customers.setCountry_customers(country);
        String check = (customersRepositorty.updateCustomers(customers) > 0) ? "Customer update" : "Customer not update";
        view.write(check);
    }

    public void executeUpdateProject() {
        Projects projects = new Projects();
        view.write("Enter name of Project where  you want to change the date");
        String name;
        int id;
        while (true) {
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id = projectsRepository.checkProject(name)) == 0) {
                view.write("This name Project not exist");
            } else break;
        }
        projects.setId_projects(id);
        view.write("Enter name of Project for change");
        String newName;
        while (true) {
            newName = view.read();
            if (newName.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }
        projects.setName_projects(newName);
        view.write("Enter cost for change of Project");
        while (true) {
            try {
                projects.setCost_project(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number");
            }
        }
        view.write("Enter start_date of Project Example: 2020-01-01");
        String date;
        while (true) {
            date = view.read();
            if (date.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }
        projects.setStart_project(date);
        String check = projectsRepository.updateProject(projects) > 0 ? "Project update" : "Project not update";
        view.write(check);
    }
}

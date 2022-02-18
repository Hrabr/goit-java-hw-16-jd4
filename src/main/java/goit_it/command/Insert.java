package goit_it.command;


import goit_it.model.*;

import goit_it.repository.*;
import goit_it.view.View;


public class Insert {
    View view;
    DevelopersRepository developersRepository;
    SkillRepository skillRepository;
    CompanyRepository companyRepository;
    CustomersRepositorty customersRepositorty;
    ProjectsRepository projectsRepository;

    public Insert(View view, DevelopersRepository developersRepository, SkillRepository skillRepository, CompanyRepository companyRepository, CustomersRepositorty customersRepositorty, ProjectsRepository projectsRepository) {
        this.view = view;
        this.developersRepository = developersRepository;
        this.skillRepository = skillRepository;
        this.companyRepository = companyRepository;
        this.customersRepositorty = customersRepositorty;
        this.projectsRepository = projectsRepository;
    }


    public void executeInsertDevelopers() {
        Developers developers = new Developers();
        String name;

        while (true) {
            view.write("Enter name of Developer");
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((skillRepository.checkDeveloperId(name)) > 0) {
                view.write("This Developer exist.Please enter another Developer ");

            } else break;
        }

        developers.setName(name);
        view.write("Enter age of Developer");
        while (true) {
            try {
                developers.setAge(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number");
            }
        }
        view.write("Enter gender of Developer");
        while (true) {
            String gender = view.read();
            if (gender.equals("male") || gender.equals("female")) {
                developers.setGender(gender);
                break;
            } else {
                view.write("You entered the wrong gender.Please, enter correct gender ");
            }
        }
        view.write("Enter salary of Developer");
        while (true) {
            try {
                developers.setSalary(Double.parseDouble(view.read()));
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        String check = (developersRepository.InsertDevelopers(developers) > 0) ? "Developer save" : "Developer not save";
        view.write(check);
    }

    public void executeInsertCompanies() {
        Companies companies = new Companies();
        view.write("Enter name_companies of Companies");
        String name;
        while (true) {
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }

        companies.setName_companies(name);
        view.write("Enter Country of Companies");
        String country;
        while (true) {
            country = view.read();
            if (country.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }

        companies.setCountry_companies(country);
        String check = (companyRepository.insertCompanies(companies) > 0) ? "Companies save" : "Companies not save";
        view.write(check);
    }

    public void executeInsertCustomers() {
        Customers customers = new Customers();
        view.write("Enter name_customers of Customers");
        String name;
        while (true) {
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if (projectsRepository.checkCustomers(name) > 0) {
                view.write("This Customer  exist");
            } else break;
        }

        customers.setName_customers(name);
        view.write("Enter Country of Customers");
        String country;
        while (true) {
            country = view.read();
            if (country.equals("")) {
                view.write("Please, enter not empty name");
            } else break;
        }

        customers.setCountry_customers(country);
        String check = (customersRepositorty.InsertCustomers(customers) > 0) ? "Customers save" : "Customers not save";
        view.write(check);

    }

    public void executeInsertSkill() {

        String name;
        int id;
        while (true) {
            view.write("Enter name of Developer");
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id = skillRepository.checkDeveloperId(name)) == 0) {
                view.write("This Developer not exist into table Developers.");

            } else break;
        }
        Skill skill = new Skill();
        view.write("Enter language of skill Example: C++, Java, Js, C#");
        String language;
        while (true) {
            language = view.read();
            if (language.equals("C++") || language.equals("Java") || language.equals("Js") || language.equals("C#")) {
                skill.setLanguage(language);
                break;
            } else {
                view.write("You entered the wrong Skill. Please, enter correct Skill ");
            }
        }

        view.write("Enter level of Skill Example: Junior, Middle, Senior");
        String level;
        while (true) {
            level = view.read();
            if (level.equals("Junior") || level.equals("Middle") || level.equals("Senior")) {
                skill.setLevel_skill(level);
                break;
            } else {
                view.write("You entered the wrong Skill. Please, enter correct Skill ");
            }
        }

        int id_skill = skillRepository.checkSkillLanguageAndLevelSkill(language, level);
        int y;
        if (id_skill == 0) {

            String check = (skillRepository.insertSkill(skill) > 0) ? "Skill save" : "Skill not save";
            view.write(check);
            y = skillRepository.checkSkillLanguageAndLevelSkill(language, level);
            skillRepository.insertDeveloper_to_Skill(id, y);
            view.write("developer_to_skill save");
        } else {
            skillRepository.deleteDeveloper_to_SkillOfExist(id, id_skill);
            skillRepository.insertDeveloper_to_Skill(id, id_skill);
            view.write("developer_to_skill save");
        }

    }

    public void executeInsertProjects() {
        Projects projects = new Projects();
        String name_developer;
        int id_developer;
        while (true) {
            view.write("Enter name of Developer");
            name_developer = view.read();
            if (name_developer.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id_developer = skillRepository.checkDeveloperId(name_developer)) == 0) {
                view.write("This Developer not exist into table Developers.");

            } else break;
        }
        view.write("Enter name_company ");
        String name_company;
        int id_company;
        while (true) {
            name_company = view.read();
            if (name_company.equals("")) {
                view.write("Please, enter not empty name_company");
            } else if ((id_company = projectsRepository.checkCompany(name_company)) == 0) {
                view.write("This Name_Company not exist into table Company");
            } else break;
        }
        projects.setCompanies_id(id_company);
        view.write("Enter name_customer ");
        String name_customer;
        int id_customer;
        while (true) {
            name_customer = view.read();
            if (name_customer.equals("")) {
                view.write("Please, enter not empty name_customer");
            } else if ((id_customer = projectsRepository.checkCustomers(name_customer)) == 0) {
                view.write("This Name_Customer not exist into table Customer");
            } else break;
        }
        projects.setCustomers_id(id_customer);
        view.write("Enter name of Project");
        String name;

        while (true) {
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");

            } else break;
        }
        projects.setName_projects(name);
        view.write("Enter cost of Project");
        while (true) {
            try {
                projects.setCost_project(Double.parseDouble(view.read()));
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
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

        int s = projectsRepository.checkProject(name);
        if (s == 0) {
            String check = (projectsRepository.insertProject(projects) > 0) ? "Project save" : "Project not save";
            view.write(check);
            projectsRepository.insertDeveloper_to_Project(id_developer, projectsRepository.checkProject(name));
        } else {
            projectsRepository.deleteDeveloper_to_Project(id_developer, projectsRepository.checkProject(name));
            projectsRepository.insertDeveloper_to_Project(id_developer, projectsRepository.checkProject(name));

        }

    }
}

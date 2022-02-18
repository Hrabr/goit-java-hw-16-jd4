package goit_it.command;

import goit_it.model.Skill;
import goit_it.repository.*;
import goit_it.view.View;

public class Delete {
    View view;
    DevelopersRepository developersRepository;
    SkillRepository skillRepository;
    CompanyRepository companyRepository;
    CustomersRepositorty customersRepositorty;
    ProjectsRepository projectsRepository;

    public Delete(View view, DevelopersRepository developersRepository, SkillRepository skillRepository, CompanyRepository companyRepository, CustomersRepositorty customersRepositorty, ProjectsRepository projectsRepository) {
        this.view = view;
        this.developersRepository = developersRepository;
        this.skillRepository = skillRepository;
        this.companyRepository = companyRepository;
        this.customersRepositorty = customersRepositorty;
        this.projectsRepository = projectsRepository;
    }

    public void executeDeleteSkillOrDeveloperSkill() {
        Skill skill = new Skill();
        String q;
        int id_developer;
        while (true) {
            view.write("Enter name of Developer");
            q = view.read();
            if (q.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id_developer = skillRepository.checkDeveloperId(q)) == 0) {
                view.write("This Developer not exist into table Developers.");

            } else break;
        }
        view.write("Enter language of skill Example: C++, Java, Js, C#");
        String language;
        while (true) {
            language = view.read();
            if (language.equals("C++") || language.equals("Java") || language.equals("JS") || language.equals("C#")) {
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
        skillRepository.deleteDeveloper_to_SkillOfExist(id_developer, id_skill);

        if (0 == skillRepository.checkDeveloperSkill(id_skill)) {
            skillRepository.deleteSkill(id_skill);
            System.out.println("Skill deleted");
        } else {
            System.out.println("Skill have another Developer");
        }
    }

    public void executeDeleteDeveloper() {
        String q;
        int id_developer;
        while (true) {
            view.write("Enter name of Developer");
            q = view.read();
            if (q.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id_developer = skillRepository.checkDeveloperId(q)) == 0) {
                view.write("This Developer not exist into table Developers.");

            } else break;
        }
        if ((skillRepository.checkSkillFromDeveloperSkill(id_developer) == 0) && (skillRepository.checkProjectFromDeveloperSkill(id_developer) == 0)) {
            developersRepository.deleteDeveloper(id_developer);
            view.write("Developer deleted");
        } else {
            view.write("The Developer  is associated with a Project or Skill, delete them first");
        }
    }

    public void executeDeleteCustomer() {
        String name;
        int id_customer;
        while (true) {
            view.write("Enter name_customers of Customer");
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id_customer = projectsRepository.checkCustomers(name)) == 0) {
                view.write("This Customer not exist");
            } else break;
        }
        projectsRepository.updateCustomerIdInProject(id_customer);
        int i = customersRepositorty.deleteCustomer(id_customer);
        if (i > 0) {
            view.write("Customer deleted");
        } else {
            view.write(" Customer not deleted");
        }
    }

    public void executeDeleteCompany() {
        String name;
        int id_company;
        while (true) {
            view.write("Enter name_company of Company");
            name = view.read();
            if (name.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id_company = projectsRepository.checkCompany(name)) == 0) {
                view.write("This Company not exist");
            } else break;
        }
        projectsRepository.updateCompanyIdInProject(id_company);
        int i = companyRepository.deleteCompany(id_company);
        if (i > 0) {
            view.write("Company deleted");
        } else {
            view.write("Company not deleted");
        }
    }

    public void executeDeleteDeveloperProjectOrProject() {
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
        String name_project;
        int id_project;
        while (true) {
            view.write("Enter name of Project");
            name_project = view.read();
            if (name_project.equals("")) {
                view.write("Please, enter not empty name");
            } else if ((id_project = projectsRepository.checkProject(name_project)) == 0) {
                view.write("This Project not exist into table Projects.");
            } else break;
        }
        int i = projectsRepository.deleteDeveloper_to_Project(id_developer, id_project);
        if (i > 0) {
            view.write("Developer_to_Project deleted");
        } else {
            view.write("Developer_to_project not found");
        }
        if (projectsRepository.checkCountIdDeveloperInDeveloperToProject(id_project) == 0) {
             int q = projectsRepository.deleteProject(id_project);
             if(q>0){
                 view.write("Project deleted");
             }else {
                 view.write("project not deleted");
             }
        }
    }
}

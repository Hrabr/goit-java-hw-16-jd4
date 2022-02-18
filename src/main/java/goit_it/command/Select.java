package goit_it.command;

import goit_it.model.*;
import goit_it.repository.*;
import goit_it.view.View;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;

public class Select {
    View view;
    DevelopersRepository developersRepository;
    SkillRepository skillRepository;
    CompanyRepository companyRepository;
    CustomersRepositorty customersRepositorty;
    ProjectsRepository projectsRepository;

    public Select(View view, DevelopersRepository developersRepository, SkillRepository skillRepository, CompanyRepository companyRepository, CustomersRepositorty customersRepositorty, ProjectsRepository projectsRepository) {
        this.view = view;
        this.developersRepository = developersRepository;
        this.skillRepository = skillRepository;
        this.companyRepository = companyRepository;
        this.customersRepositorty = customersRepositorty;
        this.projectsRepository = projectsRepository;
    }

    public void executeSelectAllDepository() {
        List<Developers> list = developersRepository.selectAllDevelopers();
        for (Developers developers : list) {

            System.out.println("Name of Developer: " + developers.getName());
            System.out.println("Age of Developer: " + developers.getAge());
            System.out.println("Gender of Developer: " + developers.getGender());
            System.out.println(" Salary of Developer: " + developers.getSalary());
            System.out.println("------------------");
        }
    }

    public void executeSelectAllCompanies() {
        List<Companies> list = companyRepository.selectAllCompanies();
        for (Companies companies : list) {
            System.out.println("Name of Company: " + companies.getName_companies());
            System.out.println("Country of Company: " + companies.getCountry_companies());
            System.out.println("-------------------");
        }
    }

    public void executeSelectAllCustomers() {
        List<Customers> list = customersRepositorty.selectAllCustomers();
        for (Customers customers : list) {
            System.out.println("Name of Customer: " + customers.getName_customers());
            System.out.println("Country of Customer: " + customers.getCountry_customers());
            System.out.println("-------------------");
        }
    }

    public void executeSelectAllSkill() {
        List<Skill> list = skillRepository.selectAllSkill();
        for (Skill skill : list) {
            System.out.println("Language of Skill: " + skill.getLanguage());
            System.out.println("Level Skill of Skill: " + skill.getLevel_skill());
            System.out.println("-------------------");
        }
    }

    public void executeSelectAllProject() {
        List<Projects> list = projectsRepository.selectAllProjects();
        for (Projects projects : list) {
            System.out.println("Name of Project: " + projects.getName_projects());
            System.out.println("Start of Project: " + projects.getStart_project());
            System.out.println("Cost of Project: " + projects.getCost_project());
            System.out.println("-------------------");
        }
    }

    public void executeSelectSumSalaryDeveloperByProject() {
        String q;

        while (true) {
            view.write("Enter name of Developer");
            q = view.read();
            if (q.equals("")) {
                view.write("Please, enter not empty name");
            } else if (projectsRepository.checkProject(q) == 0) {
                view.write("This Developer not exist into table Developers.");

            } else break;
        }
        double d = developersRepository.selectSumSalaryDevelopersByProject(q);
        System.out.println("Project Name: " + q);
        System.out.println("Salary all Developer in this Project: " + d);

    }

    public void executeSelectNameDevelopersByProject() {
        view.write("Enter name_project ");
        String name_project;
        while (true) {
            name_project = view.read();
            if (name_project.equals("")) {
                view.write("Please, enter not empty name_company");
            } else if (projectsRepository.checkProject(name_project) == 0) {
                System.out.println("This name_project is not exist.Please enter name_project");
            } else break;
        }
        List<String> list = developersRepository.selectNameDevelopersByProject(name_project);
        if (list.size() == 0) {
            System.out.println("The project has not developers");
        } else {
            for (String l : list) {
                System.out.println("Name Developer in this Project: " + l);
                System.out.println("-----------");
            }
        }
    }

    public void executeSelectNameDevelopersBySkillLanguage() {
        view.write("Enter language ");
        String language;
        while (true) {
            language = view.read();
            if (language.equals("")) {
                view.write("Please, enter not empty language");
            } else break;
        }
        List<String> list = developersRepository.selectNameDevelopersBySkillLanguage(language);
        if (list.size() == 0) {
            System.out.println("This language has not developers or language no exist");
        } else {
            for (String l : list) {
                System.out.println("Name Developer with " + language + ": " + l);
                System.out.println("-----------");
            }
        }
    }

    public void selectNameDevelopersByLevelSkill() {
        view.write("Enter level_skill ");
        String level;
        while (true) {
            level = view.read();
            if (level.equals("")) {
                view.write("Please, enter not empty level_skill");
            } else break;
        }
        List<String> list = developersRepository.selectNameDevelopersByLevelSkill(level);
        if (list.size() == 0) {
            System.out.println("This level_skill has not developers or level_skill no exist");
        } else {
            for (String l : list) {
                System.out.println("Name Developer with " + level + ": " + l);
                System.out.println("-----------");
            }
        }
    }

    public void selectProjectWithCountDevelopers() {
        Map<Projects, Integer> projectsIntegerMap = projectsRepository.selectProjectsWithCountDevelopers();
        LinkedHashMap<Projects, Integer> collect = projectsIntegerMap.entrySet().stream().sorted(Comparator.comparing(i -> i.getKey().getStart_project()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        if (collect.size() == 0) {
            System.out.println("Projects has not date");
        } else {
            for (Map.Entry<Projects, Integer> map : collect.entrySet()) {
                System.out.println("Start project: " + map.getKey().getStart_project());
                System.out.println("Name Project: " + map.getKey().getName_projects());
                System.out.println("Count developers: " + map.getValue());
                System.out.println("---------------");
            }
        }
    }
}
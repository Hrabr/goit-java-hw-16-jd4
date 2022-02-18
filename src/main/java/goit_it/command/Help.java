package goit_it.command;

import goit_it.view.View;

import static goit_it.command.Commands.*;

public class Help {
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    public void process() {
        view.write("Enter " + "'" + INSERT.getName() + "'" + " to enter the menu. There you will add the data");
        view.write("Enter " + "'" + DELETE.getName() + "'" + " to enter the menu. There you will delete the data");
        view.write("Enter " + "'" + UPDATE.getName() + "'" + " to enter the menu. There you will update the data");
        view.write("Enter " + "'" + SELECT.getName() + "'" + " to enter the menu. There you will select the data");
        view.write("Enter " + "'" + EXIT.getName() + "'" + " to exit");
    }

    public void processInsert() {
        view.write("Enter " + INSERT_DEVELOPERS.getName() + " to add developer");
        view.write("Enter " + INSERT_SKILL.getName() + " to add skill");
        view.write("Enter " + INSERT_COMPANIES.getName() + " to add company");
        view.write("Enter " + INSERT_CUSTOMERS.getName() + " to add customer");
        view.write("Enter " + INSERT_PROJECTS.getName() + " to add project");
        view.write("Enter " + EXIT.getName() + " to go to the top menu ");
    }
    public void processUpdate(){
        view.write("Enter " + UPDATE_DEVELOPERS.getName() + " to update developer");
        view.write("Enter " + UPDATE_COMPANIES.getName() + " to update company");
        view.write("Enter " + UPDATE_CUSTOMERS.getName() + " to update customer");
        view.write("Enter " + EXIT.getName() + " to go to the top menu ");
    }
    public void processSelect(){
        view.write("Enter " + SELECT_ALL_DEVELOPERS.getName() + " to select developers");
        view.write("Enter " + SELECT_ALL_SKILLS.getName() + " to select skills");
        view.write("Enter " + SELECT_ALL_COMPANIES.getName() + " to select companies");
        view.write("Enter " + SELECT_ALL_CUSTOMERS.getName() + " to select customers");
        view.write("Enter " + SELECT_ALL_PROJECTS.getName() + " to select projects");
        view.write("Enter " + SELECT_SUM_SALARY_BY_PROJECT.getName() + " to select sum salary by project");
        view.write("Enter " + SELECT_DEVELOPERS_BY_PROJECT.getName() + " to select developers by project");
        view.write("Enter " + SELECT_DEVELOPERS_BY_LANGUAGE.getName() + " to select developers by language");
        view.write("Enter " + SELECT_DEVELOPERS_BY_LEVEL_SKILL.getName() + " to select developers by level_skill");
        view.write("Enter " + SELECT_PROJECT_WITH_COUNT_DEVELOPERS.getName() + " to select projects with count developers");
        view.write("Enter " + EXIT.getName() + " to go to the top menu ");

    }
    public void processDelete(){
        view.write("Enter " + DELETE_DEVELOPER.getName() + " to delete developer");
        view.write("Enter " + DELETE_SKILL.getName() + " to delete skill");
        view.write("Enter " + DELETE_COMPANY.getName() + " to delete company");
        view.write("Enter " + DELETE_CUSTOMER.getName() + " to delete customer");
        view.write("Enter " + DELETE_PROJECT.getName() + " to delete project");
        view.write("Enter " + EXIT.getName() + " to go to the top menu ");
    }
}

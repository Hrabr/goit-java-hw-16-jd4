package goit_it.controller;

import goit_it.command.*;
import goit_it.view.View;

import static goit_it.command.Commands.*;


public class Controller {
    private final View view;
    private final Insert insert;
    private final Update update;
    private final Select select;
    private final Delete delete;
    private final Help help;

    public Controller(View view, Insert insert, Update update, Select select, Delete delete, Help help) {
        this.view = view;
        this.insert = insert;
        this.update = update;
        this.select = select;
        this.delete = delete;
        this.help = help;
    }

    public void run() {
        view.write("Welcome");
        execute();
    }

    private void execute() {

        while (true) {
            view.write("Please, enter help to see available commands:");
            String input = view.read();
//  insert
            if (input.equals(INSERT.getName())) {
                while (true) {
                    view.write("Please, enter help to see available commands for Insert:");
                    String inputInsert = view.read();
                    if (inputInsert.equals(INSERT_DEVELOPERS.getName())) {
                        insert.executeInsertDevelopers();
                    } else if (inputInsert.equals(INSERT_SKILL.getName())) {
                        insert.executeInsertSkill();
                    } else if (inputInsert.equals(INSERT_COMPANIES.getName())) {
                        insert.executeInsertCompanies();
                    } else if (inputInsert.equals(INSERT_CUSTOMERS.getName())) {
                        insert.executeInsertCustomers();
                    } else if (inputInsert.equals(INSERT_PROJECTS.getName())) {
                        insert.executeInsertProjects();
                    } else if (inputInsert.equals(EXIT.getName())) {
                        break;
                    } else if (inputInsert.equals(HELP.getName())) {
                        help.processInsert();
                    } else {
                        view.write("Incorrect command. Please, try again.");
                    }
                }
//  update
            } else if (input.equals(UPDATE.getName())) {
                while (true) {
                    view.write("Please, enter help to see available commands for Update:");
                    String inputUpdate = view.read();
                    if (inputUpdate.equals(UPDATE_DEVELOPERS.getName())) {
                        update.executeUpdateDeveloper();
                    } else if (inputUpdate.equals(UPDATE_COMPANIES.getName())) {
                        update.executeUpdateCompany();
                    } else if (inputUpdate.equals(UPDATE_CUSTOMERS.getName())) {
                        update.executeUpdateCustomers();
                    } else if (inputUpdate.equals(UPDATE_PROJECT.getName())) {
                        update.executeUpdateProject();
                    } else if (inputUpdate.equals(EXIT.getName())) {
                        break;
                    } else if (inputUpdate.equals(HELP.getName())) {
                        help.processUpdate();
                    } else {
                        view.write("Incorrect command. Please, try again.");
                    }
                }
//  select
            } else if (input.equals(SELECT.getName())) {
                while (true) {
                    view.write("Please, enter help to see available commands for Select:");
                    String inputSelect = view.read();
                    if (inputSelect.equals(SELECT_ALL_DEVELOPERS.getName())) {
                        select.executeSelectAllDepository();
                    } else if (inputSelect.equals(SELECT_ALL_COMPANIES.getName())) {
                        select.executeSelectAllCompanies();
                    } else if (inputSelect.equals(SELECT_ALL_CUSTOMERS.getName())) {
                        select.executeSelectAllCustomers();
                    } else if (inputSelect.equals(SELECT_ALL_SKILLS.getName())) {
                        select.executeSelectAllSkill();
                    } else if (inputSelect.equals(SELECT_ALL_PROJECTS.getName())) {
                        select.executeSelectAllProject();
                    } else if (inputSelect.equals(SELECT_SUM_SALARY_BY_PROJECT.getName())) {
                        select.executeSelectSumSalaryDeveloperByProject();
                    } else if (inputSelect.equals(SELECT_DEVELOPERS_BY_PROJECT.getName())) {
                        select.executeSelectNameDevelopersByProject();
                    } else if (inputSelect.equals(SELECT_DEVELOPERS_BY_LANGUAGE.getName())) {
                        select.executeSelectNameDevelopersBySkillLanguage();
                    } else if (inputSelect.equals(SELECT_DEVELOPERS_BY_LEVEL_SKILL.getName())) {
                        select.selectNameDevelopersByLevelSkill();
                    } else if (inputSelect.equals(SELECT_PROJECT_WITH_COUNT_DEVELOPERS.getName())) {
                        select.selectProjectWithCountDevelopers();
                    } else if (inputSelect.equals(EXIT.getName())) {
                        break;
                    } else if (inputSelect.equals(HELP.getName())) {
                        help.processSelect();
                    } else {
                        view.write("Incorrect command. Please, try again.");
                    }
                }
//  delete
            } else if (input.equals(DELETE.getName())) {
                while (true) {
                    view.write("Please, enter help to see available commands for Delete:");
                    String inputDelete = view.read();
                    if (inputDelete.equals(DELETE_SKILL.getName())) {
                        delete.executeDeleteSkillOrDeveloperSkill();
                    } else if (inputDelete.equals(DELETE_DEVELOPER.getName())) {
                        delete.executeDeleteDeveloper();
                    } else if (inputDelete.equals(DELETE_CUSTOMER.getName())) {
                        delete.executeDeleteCustomer();
                    } else if (inputDelete.equals(DELETE_COMPANY.getName())) {
                        delete.executeDeleteCompany();
                    } else if (inputDelete.equals(DELETE_PROJECT.getName())) {
                        delete.executeDeleteDeveloperProjectOrProject();
                    } else if (inputDelete.equals(HELP.getName())) {
                        help.processDelete();
                    } else if (inputDelete.equals(EXIT.getName())) {
                        break;
                    } else {
                        view.write("Incorrect command. Please, try again.");
                    }
                }
            } else if (input.equals(HELP.getName())) {
                help.process();
            } else if (input.equals(EXIT.getName())) {
                view.write("Goodbye!!!");
                break;
            } else {
                view.write("Incorrect command. Please, try again.");
            }
        }
    }
}

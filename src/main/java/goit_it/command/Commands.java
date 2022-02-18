package goit_it.command;

public enum Commands {
    INSERT_DEVELOPERS("developer"),
    INSERT_CUSTOMERS("customer"),
    INSERT_COMPANIES("company"),
    INSERT_SKILL("skill"),
    INSERT_PROJECTS("project"),
    UPDATE_DEVELOPERS("developer"),
    UPDATE_COMPANIES("companies"),
    UPDATE_CUSTOMERS("customers"),
    UPDATE_PROJECT("project"),
    SELECT_ALL_DEVELOPERS("allDevelopers"),
    SELECT_ALL_COMPANIES("allCompanies"),
    SELECT_ALL_SKILLS("allSkill"),
    SELECT_ALL_CUSTOMERS("allCustomers"),
    SELECT_ALL_PROJECTS("allProjects"),
    SELECT_SUM_SALARY_BY_PROJECT("sumSalaryByProject"),
    SELECT_DEVELOPERS_BY_PROJECT("developersByProject"),
    SELECT_DEVELOPERS_BY_LANGUAGE("developersByLanguage"),
    SELECT_DEVELOPERS_BY_LEVEL_SKILL("developerByLevelSkill"),
    SELECT_PROJECT_WITH_COUNT_DEVELOPERS("projectWithCountDevelopers"),
    DELETE_SKILL("skill"),
    DELETE_DEVELOPER("developer"),
    DELETE_CUSTOMER("customer"),
    DELETE_COMPANY("company"),
    DELETE_PROJECT("project"),
    EXIT("exit"),
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    SELECT("select"),
    HELP("help");





    private String name;

    Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Commands{" +
                "name='" + name + '\'' +
                '}';
    }
}

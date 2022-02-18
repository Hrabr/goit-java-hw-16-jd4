package goit_it.model;

public class Projects {
    private int id_projects;
    private String name_projects;
    private double cost_project;
    private String start_project;
    private int companies_id;
    private int customers_id;

    public int getId_projects() {
        return id_projects;
    }

    public void setId_projects(int id_projects) {
        this.id_projects = id_projects;
    }

    public String getName_projects() {
        return name_projects;
    }

    public void setName_projects(String name_projects) {
        this.name_projects = name_projects;
    }

    public double getCost_project() {
        return cost_project;
    }

    public void setCost_project(double cost_project) {
        this.cost_project = cost_project;
    }

    public String getStart_project() {
        return start_project;
    }

    public void setStart_project(String create_project) {
        this.start_project = create_project;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "id_projects=" + id_projects +
                ", name_projects='" + name_projects + '\'' +
                ", cost=" + cost_project +
                ", create_project=" + start_project +
                '}';
    }
}

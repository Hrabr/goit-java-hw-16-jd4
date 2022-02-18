package goit_it.repository;

import goit_it.connector.DatabaseManager;
import goit_it.model.Companies;
import goit_it.model.Projects;
import goit_it.model.Skill;

import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ProjectsRepository {
    DatabaseManager connector;

    public ProjectsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    private static final String INSERT_PROJECT = "INSERT INTO projects(name_projects,cost_project,start_project,companies_id,customers_id) VALUES(?,?,?,?,?)";
    private static final String UPDATE_ID_CUSTOMER_IN_PROJECT = "UPDATE projects SET customers_id=null  WHERE customers_id=?";
    private static final String UPDATE_ID_COMPANY_IN_PROJECT = "UPDATE projects SET companies_id=null WHERE companies_id=?";
    private static final String UPDATE_PROJECT = "UPDATE projects SET name_projects=?,cost_project=?,start_project=? WHERE id_projects=?";
    private static final String SELECT_COMPANIES_ID = "SELECT id_companies FROM companies WHERE name_companies=?";
    private static final String SELECT_CUSTOMERS_ID = "SELECT id_customers FROM customers WHERE name_customers=?";
    private static final String SELECT_COUNT_ID_DEVELOPER_IN_DEVELOPER_TO_PROJECT = "SELECT COUNT(developer_id) FROM developers_projects WHERE projects_id=?";
    private static final String SELECT_PROJECT_ID = "SELECT id_projects FROM projects WHERE name_projects=?";
    private static final String INSERT_DEVELOPER_TO_PROJECT = "INSERT INTO developers_projects(developer_id,projects_id) VALUES(?,?)";
    private static final String DELETE_PROJECT_BY_ID = "DELETE FROM projects WHERE id_projects=?";
    private static final String DELETE_DEVELOPER_TO_PROJECT = "DELETE FROM developers_projects WHERE developer_id=? AND projects_id=?";
    private static final String SELECT_ALL_PROJECTS = "SELECT * FROM projects";
    private static final String SELECT_ALL_PROJECTS_WITH_COUNT_DEVELOPERS = "SELECT p.start_project, p.name_projects,COUNT(d.name) AS count_developers FROM projects p" +
            " LEFT JOIN developers_projects d_p ON p.id_projects =d_p.projects_id" +
            " LEFT JOIN developers d ON d.id_developers=d_p.developer_id" +
            " GROUP BY start_project,name_projects" +
            " ORDER BY start_project";

    public int insertProject(Projects projects) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT)) {
            preparedStatement.setString(1, projects.getName_projects());
            preparedStatement.setDouble(2, projects.getCost_project());
            preparedStatement.setDate(3, Date.valueOf(projects.getStart_project()));
            preparedStatement.setInt(4, projects.getCompanies_id());
            preparedStatement.setInt(5, projects.getCustomers_id());
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException r) {
            System.out.println("Date format entered incorrectly");
        }
        return i;
    }

    public int checkCompany(String name_company) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANIES_ID)) {
            preparedStatement.setString(1, name_company);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i = resultSet.getInt("id_companies");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int checkCustomers(String name_customers) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_ID)) {
            preparedStatement.setString(1, name_customers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i = resultSet.getInt("id_customers");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int checkProject(String name_project) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_ID)) {
            preparedStatement.setString(1, name_project);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i = resultSet.getInt("id_projects");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void insertDeveloper_to_Project(int developer_id, int projects_id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEVELOPER_TO_PROJECT)) {
            preparedStatement.setInt(1, developer_id);
            preparedStatement.setInt(2, projects_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int deleteDeveloper_to_Project(int developer_id, int projects_id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEVELOPER_TO_PROJECT)) {
            preparedStatement.setInt(1, developer_id);
            preparedStatement.setInt(2, projects_id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Projects> selectAllProjects() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Projects> resultDevelopers(ResultSet resultSet) throws SQLException {
        List<Projects> list = new ArrayList<>();
        while (resultSet.next()) {
            Projects projects = new Projects();
            projects.setName_projects(resultSet.getString("name_projects"));
            projects.setStart_project(resultSet.getString("start_project"));
            projects.setCost_project(resultSet.getDouble("cost_project"));
            list.add(projects);
        }
        return list;
    }

    public Map<Projects, Integer> selectProjectsWithCountDevelopers() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECTS_WITH_COUNT_DEVELOPERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultProject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<Projects, Integer> resultProject(ResultSet resultSet) throws SQLException {
        Map<Projects, Integer> list = new HashMap<>() {
        };
        while (resultSet.next()) {
            Projects projects = new Projects();
            projects.setStart_project(resultSet.getString("start_project"));
            projects.setName_projects(resultSet.getString("name_projects"));
            int i = resultSet.getInt("count_developers");
            list.put(projects, i);
        }
        return list;
    }

    public void updateCustomerIdInProject(int customer_id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ID_CUSTOMER_IN_PROJECT)) {
            preparedStatement.setInt(1, customer_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCompanyIdInProject(int company_id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ID_COMPANY_IN_PROJECT)) {
            preparedStatement.setInt(1, company_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteProject(int id_project) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT_BY_ID)) {
            preparedStatement.setInt(1, id_project);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int checkCountIdDeveloperInDeveloperToProject(int project_id) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_ID_DEVELOPER_IN_DEVELOPER_TO_PROJECT)) {
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateProject(Projects projects) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT)) {
            preparedStatement.setString(1, projects.getName_projects());
            preparedStatement.setDouble(2, projects.getCost_project());
            preparedStatement.setDate(3, Date.valueOf(projects.getStart_project()));
            preparedStatement.setInt(4, projects.getId_projects());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
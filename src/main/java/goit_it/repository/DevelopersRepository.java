package goit_it.repository;


import goit_it.connector.DatabaseManager;
import goit_it.model.Developers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersRepository {
    DatabaseManager connector;
    private static final String INSERT_DEVELOPERS = "INSERT INTO developers (name,age,gender,salary) VALUES(?,?,?,?)";
    private static final String UPDATE_DEVELOPERS = "UPDATE developers SET name=?,age=?,gender=?,salary=? WHERE id_developers=?";
    private static final String SELECT_ALL_DEVELOPERS = "SELECT * FROM developers";
    private static final String SELECT_SUM_DEVELOPERS_SALARY_BY_PROJECT = "SELECT SUM(d.salary) AS all_salary FROM developers d" +
            " INNER JOIN developers_projects d_p ON d.id_developers=d_p.developer_id INNER JOIN projects p ON d_p.projects_id=id_projects WHERE name_projects=?";
    private static final String SELECT_DEVELOPERS_BY_PROJECT = "SELECT d.name  FROM developers d"
            + " INNER JOIN developers_projects d_p ON d.id_developers=d_p.developer_id INNER JOIN projects p ON d_p.projects_id=id_projects WHERE name_projects=?";
    private static final String SELECT_DEVELOPERS_BY_SKILL_LANGUAGE = "SELECT name FROM developers " +
            "INNER JOIN developers_skill d_s ON id_developers =d_s.developer_id INNER JOIN skill  ON d_s.skill_id=id_skill WHERE language=?";
    private static final String SELECT_DEVELOPERS_BY_LEVEL_SKILL = "SELECT name FROM developers " +
            "INNER JOIN developers_skill d_s ON id_developers =d_s.developer_id INNER JOIN skill  ON d_s.skill_id=id_skill WHERE level_skill=?";
    private static final String DELETE_DEVELOPER = "DELETE FROM developers WHERE id_developers=?";

    public DevelopersRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    public int InsertDevelopers(Developers developers) {
        int i = 0;
        try (Connection conection = connector.getConnection();
             PreparedStatement preparedStatement = conection.prepareStatement(INSERT_DEVELOPERS)) {
            preparedStatement.setString(1, developers.getName());
            preparedStatement.setInt(2, developers.getAge());
            preparedStatement.setString(3, developers.getGender());
            preparedStatement.setDouble(4, developers.getSalary());
            try {
                i = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("The developer is exist. Please enter other name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int UpdateDevelopers(Developers developers) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVELOPERS)) {
            preparedStatement.setString(1, developers.getName());
            preparedStatement.setInt(2, developers.getAge());
            preparedStatement.setString(3, developers.getGender());
            preparedStatement.setDouble(4, developers.getSalary());
            preparedStatement.setInt(5, developers.getId_developers());
            try {
                i = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("The developer is exist. Please enter other name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<Developers> selectAllDevelopers() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEVELOPERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Developers> resultDevelopers(ResultSet resultSet) throws SQLException {
        List<Developers> list = new ArrayList<>();
        while (resultSet.next()) {
            Developers developers = new Developers();
            developers.setName(resultSet.getString("name"));
            developers.setAge(resultSet.getInt("age"));
            developers.setGender(resultSet.getString("gender"));
            developers.setSalary(resultSet.getDouble("salary"));
            list.add(developers);
        }
        return list;
    }

    public double selectSumSalaryDevelopersByProject(String name) {

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUM_DEVELOPERS_SALARY_BY_PROJECT)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("all_salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> selectNameDevelopersByProject(String name) {
        List<String> list = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_PROJECT)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> selectNameDevelopersBySkillLanguage(String language) {
        List<String> list = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_SKILL_LANGUAGE)) {
            preparedStatement.setString(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> selectNameDevelopersByLevelSkill(String level) {
        List<String> list = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_LEVEL_SKILL)) {
            preparedStatement.setString(1, level);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteDeveloper(int id_developer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEVELOPER)) {
            preparedStatement.setInt(1, id_developer);
              preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
package goit_it.repository;

import goit_it.connector.DatabaseManager;
import goit_it.model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    DatabaseManager connector;
    private static final String INSERT_SKILL = "INSERT INTO SKILL(language,level_skill) VALUES(?,?)";
    private static final String SELECT_DEVELOPER_ID = "SELECT  id_developers FROM developers WHERE name=?";
    private static final String SELECT_SKILL_ID = "SELECT id_skill FROM skill WHERE language=?AND level_skill=? ";
    private static final String INSERT_DEVELOPERS_TO_SKILL = "INSERT INTO DEVELOPERS_SKILL (developer_id,skill_id) VALUES(?,?)";
    private static final String DELETE_DEVELOPER_to_SKILL = "DELETE FROM developers_skill WHERE developer_id=? AND skill_id=?";
    private static final String SELECT_ALL_SKILL = "SELECT * FROM skill";
    private static final String DELETE_SKILL = "DELETE FROM skill WHERE id_skill=?";
    private static final String SELECT_COUNT_DEVELOPER_DEVELOPER_TO_SKILL = "SELECT COUNT(developer_id) FROM developers_skill WHERE skill_id=?";
    private static final String SELECT_COUNT_SKILLS_DEVELOPER_TO_SKILL="SELECT COUNT(skill_id) FROM developers_skill WHERE developer_id=?";
    private static final String SELECT_COUNT_PROJECTS_DEVELOPER_TO_PROJECTS="SELECT COUNT(projects_id) FROM developers_projects WHERE developer_id=?";
    public SkillRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    public int insertSkill(Skill skill) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SKILL)) {
            preparedStatement.setString(1, skill.getLanguage());
            preparedStatement.setString(2, skill.getLevel_skill());
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void insertDeveloper_to_Skill(int developer_id, int skill_id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEVELOPERS_TO_SKILL)) {
            preparedStatement.setInt(1, developer_id);
            preparedStatement.setInt(2, skill_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkDeveloperId(String name) {
        int id = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER_ID)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id_developers");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int checkSkillLanguageAndLevelSkill(String language, String level_skill) {
        int id = 0;

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SKILL_ID)) {
            preparedStatement.setString(1, language);
            preparedStatement.setString(2, level_skill);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id_skill");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void deleteDeveloper_to_SkillOfExist(int developer_id, int skill_id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEVELOPER_to_SKILL)) {
            preparedStatement.setInt(1, developer_id);
            preparedStatement.setInt(2, skill_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Skill> selectAllSkill() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SKILL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Skill> resultDevelopers(ResultSet resultSet) throws SQLException {
        List<Skill> list = new ArrayList<>();
        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setLanguage(resultSet.getString("language"));
            skill.setLevel_skill(resultSet.getString("level_skill"));

            list.add(skill);
        }
        return list;
    }

    public int deleteSkill(int id_skill) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SKILL)) {
            preparedStatement.setInt(1, id_skill);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int checkDeveloperSkill(int id_skill) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_DEVELOPER_DEVELOPER_TO_SKILL)) {
            preparedStatement.setInt(1, id_skill);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int checkSkillFromDeveloperSkill(int id_developer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_SKILLS_DEVELOPER_TO_SKILL)) {
            preparedStatement.setInt(1, id_developer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int checkProjectFromDeveloperSkill(int id_developer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_PROJECTS_DEVELOPER_TO_PROJECTS)) {
            preparedStatement.setInt(1, id_developer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

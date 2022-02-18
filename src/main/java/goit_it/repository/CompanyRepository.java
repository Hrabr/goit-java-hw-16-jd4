package goit_it.repository;

import goit_it.connector.DatabaseManager;
import goit_it.model.Companies;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    DatabaseManager connector;
    private static final String INSERT_COMPANIES = "INSERT INTO companies (name_companies,country_companies) VALUES(?,?)";
    private static final String UPDATE_COMPANY = "UPDATE companies SET name_companies=?,country_companies=? WHERE id_companies=?";
    private static final String SELECT_ALL_COMPANY = "SELECT * FROM companies";
    private static final String DELETE_COMPANIES = "DELETE FROM companies WHERE id_companies=?";

    public CompanyRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    public int insertCompanies(Companies companies) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMPANIES)) {
            preparedStatement.setString(1, companies.getName_companies());
            preparedStatement.setString(2, companies.getCountry_companies());

            try {
                i = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("The Companies is exist. Please enter other name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateComanies(Companies companies) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPANY)) {
            preparedStatement.setString(1, companies.getName_companies());
            preparedStatement.setString(2, companies.getCountry_companies());
            preparedStatement.setInt(3, companies.getId_companies());
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<Companies> selectAllCompanies() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPANY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Companies> resultDevelopers(ResultSet resultSet) throws SQLException {
        List<Companies> list = new ArrayList<>();
        while (resultSet.next()) {
            Companies companies = new Companies();
            companies.setName_companies(resultSet.getString("name_companies"));
            companies.setCountry_companies(resultSet.getString("country_companies"));

            list.add(companies);
        }
        return list;
    }
    public int deleteCompany(int id_companies) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMPANIES)) {
            preparedStatement.setInt(1, id_companies);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
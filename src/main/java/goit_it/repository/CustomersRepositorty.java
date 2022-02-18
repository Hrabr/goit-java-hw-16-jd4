package goit_it.repository;

import goit_it.connector.DatabaseManager;
import goit_it.model.Companies;
import goit_it.model.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersRepositorty {
    DatabaseManager connector;
    private static final String INSERT_CUSTOMERS = "INSERT INTO customers (name_customers,country_customers) VALUES(?,?)";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET name_customers=?,country_customers=? WHERE id_customers=?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE id_customers=?";

    public CustomersRepositorty(DatabaseManager connector) {
        this.connector = connector;
    }

    public int InsertCustomers(Customers customers) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS)) {
            preparedStatement.setString(1, customers.getName_customers());
            preparedStatement.setString(2, customers.getCountry_customers());

            try {
                i = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("The Customers is exist. Please enter other name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateCustomers(Customers customers) {
        int i = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            preparedStatement.setString(1, customers.getName_customers());
            preparedStatement.setString(2, customers.getCountry_customers());
            preparedStatement.setInt(3, customers.getId_customers());
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<Customers> selectAllCustomers() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Customers> resultDevelopers(ResultSet resultSet) throws SQLException {
        List<Customers> list = new ArrayList<>();
        while (resultSet.next()) {
            Customers customers = new Customers();
            customers.setName_customers(resultSet.getString("name_customers"));
            customers.setCountry_customers(resultSet.getString("country_customers"));

            list.add(customers);
        }
        return list;
    }


    public int deleteCustomer(int id_customer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, id_customer);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
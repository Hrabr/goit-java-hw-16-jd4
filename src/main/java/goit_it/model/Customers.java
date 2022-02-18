package goit_it.model;

public class Customers {
    private int id_customers;
    private String name_customers;
    private String country_customers;

    public int getId_customers() {
        return id_customers;
    }

    public void setId_customers(int id_customers) {
        this.id_customers = id_customers;
    }

    public String getName_customers() {
        return name_customers;
    }

    public void setName_customers(String name_customers) {
        this.name_customers = name_customers;
    }

    public String getCountry_customers() {
        return country_customers;
    }

    public void setCountry_customers(String country_customers) {
        this.country_customers = country_customers;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id_customers=" + id_customers +
                ", name_customers='" + name_customers + '\'' +
                ", country_customers='" + country_customers + '\'' +
                '}';
    }
}

package goit_it.model;

public class Companies {
  private int id_companies;
  private String name_companies;
  private String country_companies;

    public int getId_companies() {
        return id_companies;
    }

    public void setId_companies(int id_companies) {
        this.id_companies = id_companies;
    }

    public String getName_companies() {
        return name_companies;
    }

    public void setName_companies(String name_companies) {
        this.name_companies = name_companies;
    }

    public String getCountry_companies() {
        return country_companies;
    }

    public void setCountry_companies(String country_companies) {
        this.country_companies = country_companies;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id_companies=" + id_companies +
                ", name_companies='" + name_companies + '\'' +
                ", country_companies='" + country_companies + '\'' +
                '}';
    }
}

package goit_it;


import goit_it.command.*;
import goit_it.connector.DatabaseManager;

import goit_it.connector.PropertiesUtil;
import goit_it.connector.PropertisProvade;
import goit_it.controller.Controller;
import goit_it.repository.*;
import goit_it.view.Console;
import goit_it.view.View;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesUtil prop = new PropertiesUtil();
        DatabaseManager pm = new PropertisProvade(prop.getHostName(), prop.getSchema(), prop.getPort(), prop.getUser(), prop.getPassword());
        DevelopersRepository developersRepository=new DevelopersRepository(pm);
        SkillRepository skillRepository=new SkillRepository(pm);
        CompanyRepository companyRepository = new CompanyRepository(pm);
        CustomersRepositorty customersRepositorty= new CustomersRepositorty(pm);
        ProjectsRepository projectsRepository=new ProjectsRepository(pm);
        View view =new Console();
        Insert insert=new Insert(view,developersRepository,skillRepository,companyRepository,customersRepositorty,projectsRepository);
       Update update=new Update(view,developersRepository,skillRepository,companyRepository,customersRepositorty,projectsRepository);
        Select select=new Select(view,developersRepository,skillRepository,companyRepository,customersRepositorty,projectsRepository);
        Delete delete=new Delete(view,developersRepository,skillRepository,companyRepository,customersRepositorty,projectsRepository);
        Help help=new Help(view);
        Controller controller=new Controller(view,insert,update,select,delete,help);
        controller.run();
    }
}
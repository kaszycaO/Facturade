package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@EntityScan(basePackages = {"FacturadeDB.Facturade.Client"})
@Controller // This means that this class is a Controller
public class DBController{

    @Autowired
    private ApplicationContext context;
    private MyRepository repo;



    @GetMapping(value = "/all")
    public List<client> getAllClients(){
        return (List<client>) repo.findAll();
    }

    @RequestMapping(value = "/load")
    public String addNewClient(final client client){
        repo.save(client);
        return "Done";
    }

    public MyRepository getRepo(){
        return this.repo;
    }

}

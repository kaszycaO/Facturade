package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.client;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@EntityScan(basePackages = {"FacturadeDB.Facturade.Client"})
@Controller // This means that this class is a Controller
@RequestMapping(value="/rest/clients") // This means URL's start with /demo (after Application path)
public class DBController {
    private Repository repo;

    @GetMapping(value = "/all")
    public List<client> getAll(){
        return (List<client>) repo.findAll();
    }

    @PostMapping(value = "/load")
    public List<client> persist(@RequestBody final client client){
        repo.save(client);
        return (List<client>) repo.findAll();
    }
}

package com.dulasi.ticketsystem.backend.controllers;

import com.dulasi.ticketsystem.backend.models.Configuration;
import com.dulasi.ticketsystem.backend.services.ConfigurationService;
import com.dulasi.ticketsystem.backend.services.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value= "api/configuration")
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolService ticketPoolService;

    @PostMapping(value= "/save")
    public String saveConfig(@RequestBody Configuration configuration) {
        configurationService.saveOrUpdate(configuration);
        ticketPoolService.start(configuration);
        return configuration.getId();
    }

    @GetMapping(value= "/getAll")
    public Iterable<Configuration> getAllStudents(){
        return configurationService.listAll();
    }

//    @GetMapping("/getLast")
//    public Configuration getLastInsertedConfig() {
//        return configurationService.getLastInsertedConfig();
//    }

    @PutMapping(value= "/edit/{id}")
    public String editConfig(@PathVariable(name= "id") String id, @RequestBody Configuration configuration){
        configuration.setId(id);
        configurationService.saveOrUpdate(configuration);
        return configuration.getId();
    }

    @DeleteMapping(value= "/delete/{id}")
    public void deleteConfig(@PathVariable("id") String id){
        configurationService.delete(id);
    }

    @RequestMapping(value= "/search/{id}")
    public Configuration getConfig(@PathVariable(name= "id") String id){
        return configurationService.getConfigByID(id);
    }
}

package com.dulasi.ticketsystem.backend.services;

import com.dulasi.ticketsystem.backend.models.Configuration;
import com.dulasi.ticketsystem.backend.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;


@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveOrUpdate(Configuration config) {
        configurationRepository.save(config);
    }

    public Iterable<Configuration> listAll() {
        return this.configurationRepository.findAll();
    }

    public void delete(String id) {
        configurationRepository.deleteById(id);
    }

    public Configuration getConfigByID(String id){
        return this.configurationRepository.findById(id).get();
    }

    public Configuration getLastInsertedConfig() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "_id"));
        query.limit(1);
        return mongoTemplate.findOne(query, Configuration.class);
    }
}

package com.dulasi.ticketsystem.backend.repositories;

import com.dulasi.ticketsystem.backend.models.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<Configuration, String> {
}

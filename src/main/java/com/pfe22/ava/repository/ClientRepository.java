package com.pfe22.ava.repository;

import com.pfe22.ava.entities.AppUsers.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {

    Client findClientByUserId(String id);
}

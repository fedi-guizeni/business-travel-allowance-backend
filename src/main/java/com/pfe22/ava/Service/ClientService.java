package com.pfe22.ava.Service;

import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.exception.AppUsers.ClientIdExistException;
import com.pfe22.ava.exception.AppUsers.ClientNotFountException;

public interface ClientService {
    Client checkFetchClient(String clientId) throws ClientIdExistException, ClientNotFountException;
}

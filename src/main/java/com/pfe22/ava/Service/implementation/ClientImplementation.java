package com.pfe22.ava.Service.implementation;

import com.pfe22.ava.entities.AppUsers.Client;
import com.pfe22.ava.Service.ClientService;
import com.pfe22.ava.exception.AppUsers.ClientIdExistException;
import com.pfe22.ava.exception.AppUsers.ClientNotFountException;
import com.pfe22.ava.repository.ClientRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClientImplementation implements ClientService {

    private ClientRepository clientRepository;

    public ClientImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client checkFetchClient(String clientId) throws ClientNotFountException {
        Client client = clientRepository.findClientByUserId(clientId);
        if (client==null){
            throw  new ClientNotFountException("client not found");
        }else {

            return client;
        }

    }

    private Client findClientByUserid(String clinetid){
        return  clientRepository.findClientByUserId(clinetid)  ;
    }
    private Client validateClientId(String currentClientId , String newClientId) throws ClientIdExistException {
        Client ClientByNewId = findClientByUserid(  newClientId);
        if (StringUtils.isNotBlank(currentClientId)){
            Client currentclient = findClientByUserid(currentClientId);

            if (ClientByNewId!=null && currentclient.getUserId().equals(currentclient.getUserId())){
                throw new ClientIdExistException( currentclient.getFirstName() +currentclient.getLastName()  +"client exist");
            }

            return currentclient;

        }else {
            if (ClientByNewId != null){
                throw new ClientIdExistException("client already have an account");
            }

            return null;
        }
    }
}

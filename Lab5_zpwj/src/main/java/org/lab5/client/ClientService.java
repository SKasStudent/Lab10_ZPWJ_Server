package org.lab5.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepo repo;

    public ClientService(ClientRepo repo){this.repo = repo;}

    public Client add(Client client){
        client.setActive(true);
        return repo.save(client);
    }

    public List<Client> getAll() {
        return repo.findAll();
    }

    public Client getOne(int id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + id + " not found"));
    }

    public Client update(int id, Client client){
        if(repo.update(id, client) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + id + " not found");
        return getOne(id);
    }

    public void makeInactive(int id){
        if(repo.makeInactive(id) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + id + " not found");
    }

    public void delete(int id){
        if(repo.delete(id) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + id + " not found");
    }
}

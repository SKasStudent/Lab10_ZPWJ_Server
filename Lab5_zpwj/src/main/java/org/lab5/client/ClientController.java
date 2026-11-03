package org.lab5.client;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController{

    private final ClientService service;

    public ClientController(ClientService service){this.service = service;}

    @GetMapping()
    public List<Client> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable("id") int id){
        return service.getOne(id);
    }

    @PostMapping
    public ResponseEntity<Client> add(@RequestBody Client client) {
        Client saved = service.add(client);
        return ResponseEntity.created(URI.create("/api/clients/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable("id") int id, @RequestBody Client client){ return service.update(id, client);}

    @PatchMapping("inactive/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeInactive(@PathVariable("id") int id) {
        service.makeInactive(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

}

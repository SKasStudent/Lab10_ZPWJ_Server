package org.lab5.wood;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WoodService {
    private final WoodRepo repo;

    public WoodService(WoodRepo repo){this.repo = repo;}

    public Wood add(Wood wood){
        wood.setAvailable(true);
        return repo.save(wood);
    }

    public List<Wood> getAll() {
        return repo.findAll();
    }

    public Wood getOne(int id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wood with id " + id + " not found"));
    }

    public Wood update(int id, Wood wood){
        if(repo.update(id, wood) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wood with id " + id + " not found");
        return getOne(id);
    }

    public void makeUnavailable(int id){
        if(repo.makeUnavailable(id) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wood with id " + id + " not found");
    }

    public void delete(int id){
        if(repo.delete(id) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wood with id " + id + " not found");
    }
}

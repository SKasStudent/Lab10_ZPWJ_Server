package org.lab5.wood;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/wood")
public class WoodController{

    private final WoodService service;

    public WoodController(WoodService service){this.service = service;}

    @GetMapping()
    public List<Wood> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Wood getOne(@PathVariable("id") int id){
        return service.getOne(id);
    }

    @PostMapping
    public ResponseEntity<Wood> add(@RequestBody Wood wood) {
        Wood saved = service.add(wood);
        return ResponseEntity.created(URI.create("/api/wood/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Wood update(@PathVariable("id") int id, @RequestBody Wood wood){ return service.update(id, wood);}

    @PatchMapping("unavailable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeUnavailable(@PathVariable("id") int id) {
        service.makeUnavailable(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

}
package com.rest.webservices.restful_web_services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Predicate;

import static com.rest.webservices.restful_web_services.user.UserDAOService.users;


@RestController
public class UserResource {

    private UserDAOService service;

    public UserResource(UserDAOService service){
        this.service = service;
    }

    @GetMapping(path="/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping(path="/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        User user = service.findUser(id);

        if(user == null)
            throw new UserNotFoundException("id:"+id);

        return service.findUser(id);
//        return service.findUser(id-1);
    }

    @PostMapping(path="/users")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}

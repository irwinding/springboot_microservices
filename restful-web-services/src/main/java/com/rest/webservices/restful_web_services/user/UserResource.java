package com.rest.webservices.restful_web_services.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        return service.findUser(id);
//        return service.findUser(id-1);
    }

}

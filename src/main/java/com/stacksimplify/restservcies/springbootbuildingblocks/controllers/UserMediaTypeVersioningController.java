package com.stacksimplify.restservcies.springbootbuildingblocks.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservcies.springbootbuildingblocks.dtos.UserDtoV1;
import com.stacksimplify.restservcies.springbootbuildingblocks.dtos.UserDtoV2;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.services.UserService;

@RestController
@RequestMapping("/versioning/mediatype/users")
public class UserMediaTypeVersioningController {

    // Autowire the UserServcie
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;

    // Media Type based Versioning - V1
    // getUserById
    @GetMapping(value =  "/{id}", produces="application/vnd.stacksimplify.app-v1+json")
    public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {

            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
        return userDtoV1;
    }

    // Media Type based Versioning - V2
    // getUserById2
    @GetMapping(value =  "/{id}", produces="application/vnd.stacksimplify.app-v2+json")
    public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {

            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
        return userDtoV2;
    }
}

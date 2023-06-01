package com.prinved.agroApp.controller;

import com.prinved.agroApp.model.UserModel;
import com.prinved.agroApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel userModel){

        if(userModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        UserModel user = userRepository.save(userModel);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findAllUser")
    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id){

        UserModel userModel = userRepository.findById(id).orElse(null);
        if (userModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel){

        UserModel user = userRepository.findById(id).orElse(null);
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        user.setEmail(userModel.getEmail());
        user.setNom(userModel.getNom());
        user.setPrenom(userModel.getPrenom());
        user.setLogin(userModel.getLogin());

        UserModel updateUser = userRepository.save(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "Deleted Successfully";
    }
}

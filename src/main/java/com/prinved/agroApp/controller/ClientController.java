package com.prinved.agroApp.controller;

import com.prinved.agroApp.model.ClientModel;
import com.prinved.agroApp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/addClient")
    public ResponseEntity<ClientModel> saveClient(@RequestBody ClientModel clientModel){

        if(clientModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ClientModel client = clientRepository.save(clientModel);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/findAllClient")
    public List<ClientModel> getAllClient(){
        return clientRepository.findAll();
    }

    @GetMapping("/findClientById/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable("id") Long id){

        ClientModel clientModel = clientRepository.findById(id).orElse(null);
        if (clientModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(clientModel, HttpStatus.OK);
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable("id") Long id, @RequestBody ClientModel clientModel){

        ClientModel client = clientRepository.findById(id).orElse(null);
        if (client == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        client.setAcheter(clientModel.getAcheter());
        client.setEmail(clientModel.getEmail());
        client.setNom(clientModel.getNom());
        client.setPrenom(clientModel.getPrenom());
        client.setTel(clientModel.getTel());

        ClientModel updateClient = clientRepository.save(client);
        return new ResponseEntity<>(updateClient, HttpStatus.OK);
    }

}

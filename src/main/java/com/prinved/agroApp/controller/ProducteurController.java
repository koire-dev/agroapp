package com.prinved.agroApp.controller;

import com.prinved.agroApp.model.ProducteurModel;
import com.prinved.agroApp.model.SemenceModel;
import com.prinved.agroApp.model.VarieteModel;
import com.prinved.agroApp.repository.ProducteurRepository;
import com.prinved.agroApp.repository.VarieteRepository;
import com.prinved.agroApp.service.VarieteService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProducteurController {

    @Autowired
    private ProducteurRepository producteurRepository;

    @Autowired
    private VarieteRepository varieteRepository;

    @Autowired
    private VarieteService varieteService;

    @PostMapping("/addProducteur")
    public ResponseEntity<ProducteurModel> saveProducteur(@RequestBody ProducteurModel producteurModel){

        if(producteurModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ProducteurModel producteur = producteurRepository.save(producteurModel);
        return new ResponseEntity<>(producteur, HttpStatus.OK);
    }

    @GetMapping("/findAllProducteur")
    public List<ProducteurModel> getAllProducteur(){
        return producteurRepository.findAll();
    }

    @GetMapping("/findProducteurById/{id}")
    public ResponseEntity<ProducteurModel> getProducteurById(@PathVariable("id") Long id){

        ProducteurModel producteurModel = producteurRepository.findById(id).orElse(null);
        if (producteurModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(producteurModel, HttpStatus.OK);
    }

    @GetMapping("/findProducteurByNom")
    public List<ProducteurModel> getProducteurByNom(@PathParam("nom") String nom){
        return producteurRepository.findProducteurByNom(nom);
    }

    @GetMapping("/findAllVarieteFromProducteur/{id}")
    public List<VarieteModel> getAllVarieteFromProducteur(@PathVariable("id") Long id){

        ProducteurModel producteurModel = producteurRepository.findById(id).orElse(null);
        if (producteurModel == null){
            return null;
        }
        List<VarieteModel> variete = new ArrayList<>();
        for (VarieteModel var : varieteService.getAllVariete()){
            VarieteModel varieteModel = varieteRepository.findById(var.getIdVariete()).orElse(null);
            if (varieteModel.getProducteur().getIdProducteur() == producteurModel.getIdProducteur()){
                variete.add(new VarieteModel(varieteModel.getIdVariete(), varieteModel.getLabel(), varieteModel.getPrix(), varieteModel.getQuantite()));
            }
        }
        return variete;
    }

    @PutMapping("/updateProducteur/{id}")
    public ResponseEntity<ProducteurModel> updateProducteur(@PathVariable("id") Long id, @RequestBody ProducteurModel producteurModel){

        ProducteurModel producteur = producteurRepository.findById(id).orElse(null);
        if (producteur == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        producteur.setEmail(producteurModel.getEmail());
        producteur.setPrenom(producteurModel.getPrenom());
        producteur.setNom(producteurModel.getNom());
        producteur.setTel(producteurModel.getTel());

        ProducteurModel updateProducteur = producteurRepository.save(producteur);
        return new ResponseEntity<>(updateProducteur, HttpStatus.OK);
    }
}

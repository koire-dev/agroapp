package com.prinved.agroApp.controller;

import com.prinved.agroApp.model.SemenceModel;
import com.prinved.agroApp.model.VarieteModel;
import com.prinved.agroApp.repository.SemenceRepository;
import com.prinved.agroApp.repository.VarieteRepository;
import com.prinved.agroApp.service.SemenceService;
import com.prinved.agroApp.service.VarieteService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SemenceController {

    @Autowired
    private SemenceRepository semenceRepository;

    @Autowired
    private SemenceService semenceService;

    @Autowired
    private VarieteRepository varieteRepository;

    @Autowired
    private VarieteService varieteService;

    @PostMapping("/addSemence")
    public ResponseEntity<SemenceModel> saveSemence(@RequestBody SemenceModel semenceModel){

        if (semenceModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        SemenceModel semence = semenceRepository.save(semenceModel);
        return new ResponseEntity<>(semence, HttpStatus.OK);
    }

    @GetMapping("/findAllSemence")
    public List<SemenceModel> getAllSemence(){
        return semenceRepository.findAll();
    }

    @GetMapping("/findAllVarieteFromSemence/{id}")
    public List<VarieteModel> getAllVarieteFromSemence(@PathVariable("id") Long id){

        SemenceModel semenceModel = semenceRepository.findById(id).orElse(null);
        if (semenceModel == null){
            return null;
        }
        List<VarieteModel> variete = new ArrayList<>();
        for (VarieteModel var : varieteService.getAllVariete()){
            VarieteModel varieteModel = varieteRepository.findById(var.getIdVariete()).orElse(null);
            if (varieteModel.getSemence().getIdSemence() == semenceModel.getIdSemence()){
                variete.add(new VarieteModel(varieteModel.getIdVariete(), varieteModel.getLabel(), varieteModel.getPrix(), varieteModel.getQuantite()));
            }
        }
        return variete;
    }

    @GetMapping("/findSemenceByLabel")
    public SemenceModel getSemenceByLabel(@PathParam("label") String label){
        return semenceRepository.findSemenceByLabel(label);
    }

    @PutMapping("/updateSemence/{id}")
    public ResponseEntity<SemenceModel> updateSemence(@PathVariable("id") Long id, @RequestBody SemenceModel semenceModel){

        SemenceModel updateSemence = semenceService.updateSemence(id, semenceModel);
        return new ResponseEntity<>(updateSemence, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSemence/{id}")
    public String deleteSemence(@PathVariable("id") Long id){
        semenceRepository.deleteById(id);
        return "Deleted Successfully";
    }
}

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

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

@RestController
public class VarieteController {

    @Autowired
    private VarieteRepository varieteRepository;
    @Autowired
    private SemenceRepository semenceRepository;
    @Autowired
    private SemenceService semenceService;
    @Autowired
    private VarieteService varieteService;

    @PostMapping("/addVariete")
    public ResponseEntity<VarieteModel> saveVariete(@RequestBody VarieteModel varieteModel){

        if (varieteModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        SemenceModel semenceModel = varieteModel.getSemence();

        Long id = semenceModel.getIdSemence();
        SemenceModel updateSemence = semenceService.updateSemence(id, semenceModel);
        varieteModel.setSemence(updateSemence);
        VarieteModel variete = varieteRepository.save(varieteModel);
        return new ResponseEntity<>(variete, HttpStatus.OK);
    }

    @GetMapping("/findAllVariete")
    public List<VarieteModel> getAllVariete(){
        return varieteService.getAllVariete();
    }

    @GetMapping("/findVarieteById/{id}")
    public ResponseEntity<VarieteModel> getVarieteById(@PathVariable("id") Long id){

        VarieteModel varieteModel = varieteRepository.findById(id).orElse(null);
        if (varieteModel == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(varieteModel, HttpStatus.OK);
    }

    @GetMapping("/findVarieteByLabel")
    public ResponseEntity<VarieteModel> getVarieteByLabel(@PathParam("label") String label){
        VarieteModel varieteModel = varieteRepository.findVarieteByLabel(label);
        if (varieteModel == null){
            return new ResponseEntity<>(null);
        }
        return new ResponseEntity<>(varieteModel, HttpStatus.OK);
    }

    @PutMapping("/updateVariete/{id}")
    public ResponseEntity<VarieteModel> updateVariete(@PathVariable("id") Long id, @RequestBody VarieteModel varieteModel){

        return varieteService.updateVariete(id, varieteModel);
    }

    @DeleteMapping("/deleteVariete/{id}")
    public String deleteVariete(@PathVariable("id") Long id){
        varieteRepository.deleteById(id);
        return "Deleted Successfully";
    }
}

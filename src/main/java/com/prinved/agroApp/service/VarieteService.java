package com.prinved.agroApp.service;

import com.prinved.agroApp.model.VarieteModel;
import com.prinved.agroApp.repository.VarieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class VarieteService {

    @Autowired
    private VarieteRepository varieteRepository;

    public List<VarieteModel> getAllVariete(){
        return varieteRepository.findAll();
    }

    public ResponseEntity<VarieteModel> updateVariete(Long id, VarieteModel varieteModel){
        VarieteModel variete = varieteRepository.findById(id).orElse(null);
        if (variete == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        variete.setQuantite(varieteModel.getQuantite());
        variete.setLabel(varieteModel.getLabel());
        variete.setPrix(varieteModel.getPrix());
        variete.setSemence(varieteModel.getSemence());

        VarieteModel updateVariete = varieteRepository.save(variete);
        return new ResponseEntity<>(updateVariete, HttpStatus.OK);
    }
}

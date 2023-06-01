package com.prinved.agroApp.controller;

import com.prinved.agroApp.model.Acheter;
import com.prinved.agroApp.model.VarieteModel;
import com.prinved.agroApp.repository.AcheterRepository;
import com.prinved.agroApp.repository.VarieteRepository;
import com.prinved.agroApp.service.VarieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class AcheterController {

    @Autowired
    private AcheterRepository acheterRepository;

    @Autowired
    private VarieteRepository varieteRepository;

    @Autowired
    private VarieteService varieteService;

    @PostMapping("/addAcheter")
    public ResponseEntity<Acheter> saveAcheter(@RequestBody Acheter acheter){
        if(acheter == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        int quantiteAcheter = acheter.getQuantite();
        Long id = acheter.getVariete().getIdVariete();
        VarieteModel varieteModel = varieteRepository.findById(id).orElse(null);

        int quantiteVariete = varieteModel.getQuantite();

        if(quantiteVariete<quantiteAcheter){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        quantiteVariete = quantiteVariete - quantiteAcheter;
        varieteModel.setQuantite(quantiteVariete);

        Double prixVariete = varieteModel.getPrix();
        Double prixTotal = acheter.prixTotal(quantiteAcheter, prixVariete);
        acheter.setVariete(varieteModel);
        acheter.setClient(acheter.getClient());
        acheter.setPrixTotal(prixTotal);
        Long idAcheter = acheter.getId();
        varieteService.updateVariete(id,varieteModel);
        Acheter saveAcheter = acheterRepository.save(acheter);
        return new ResponseEntity<>(saveAcheter, HttpStatus.OK);
    }

    @GetMapping("/findAllAcheter")
    public List<Acheter> getAllAcheter(){
        return acheterRepository.findAll();
    }

    @GetMapping("/findAcheterById/{id}")
    public ResponseEntity<Acheter> getAcheterById(@PathVariable("id") Long id){

        Acheter acheter = acheterRepository.findById(id).orElse(null);
        if(acheter == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(acheter, HttpStatus.OK);
    }

    @GetMapping("/findAcheterByDate/{date}")
    public List<Acheter> getAcheterByDate(@PathVariable("date")Date date){
        return acheterRepository.findVarieteByDate(date);
    }

    @PutMapping("/updateAcheter/{id}")
    public ResponseEntity<Acheter> updateAcheter(@PathVariable("id") Long id, @RequestBody Acheter acheter){

        Acheter acheter1 = acheterRepository.findById(id).orElse(null);
        if (acheter1 == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        int quantite = acheter.getQuantite();
        Double prix = acheter.getVariete().getPrix();
        acheter1.setVariete(acheter.getVariete());
        acheter1.setQuantite(acheter.getQuantite());
        acheter1.setDate(acheter.getDate());
        acheter1.setClient(acheter.getClient());
        acheter1.setPrixTotal(acheter.prixTotal(quantite, prix));

        Acheter updateAcheter = acheterRepository.save(acheter1);
        return new ResponseEntity<>(updateAcheter, HttpStatus.OK);
    }
}

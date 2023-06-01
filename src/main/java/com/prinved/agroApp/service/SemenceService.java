package com.prinved.agroApp.service;

import com.prinved.agroApp.model.SemenceModel;
import com.prinved.agroApp.repository.SemenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemenceService {

    @Autowired
    private SemenceRepository semenceRepository;

    public SemenceModel updateSemence(Long id, SemenceModel semenceModel){

        SemenceModel semence = semenceRepository.findById(id).orElse(null);
        semence.setLabel(semenceModel.getLabel());
        //semence.setVariete(semenceModel.getVariete());
        SemenceModel updateSemence = semenceRepository.save(semence);
        return updateSemence;
    }
}

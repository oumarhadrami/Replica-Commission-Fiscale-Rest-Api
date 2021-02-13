package com.mohamed.hadramy.springbootapp.controller;

import java.util.Optional;

import com.mohamed.hadramy.springbootapp.model.CommissionDescription;
import com.mohamed.hadramy.springbootapp.model.Ministre;
import com.mohamed.hadramy.springbootapp.repository.CommissionDescriptionRepository;
import com.mohamed.hadramy.springbootapp.repository.MinistreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presentation_api/v1/")
public class PresentationController {

    @Autowired
    CommissionDescriptionRepository commissionDescriptionRepository;

    @Autowired
    MinistreRepository ministreRepository;
    
    @GetMapping("presentation/commissiondesciption/{id}")
    public ResponseEntity<CommissionDescription> getCommissionDescription(@PathVariable("id") long id){
        
        Optional<CommissionDescription> commissionDescription = commissionDescriptionRepository.findById(id);
        if(commissionDescription.isPresent()) return new ResponseEntity<>(commissionDescription.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("presentation/ministre/{id}")
    public ResponseEntity<Ministre> getMinistre(@PathVariable("id") long id){
        
        Optional<Ministre> ministre = ministreRepository.findById(id);
        if(ministre.isPresent()) return new ResponseEntity<>(ministre.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    
}

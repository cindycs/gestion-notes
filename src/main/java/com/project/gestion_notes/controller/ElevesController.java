package com.project.gestion_notes.controller;


import com.project.gestion_notes.entity.Eleves;

import com.project.gestion_notes.repository.ElevesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ElevesController {
    @Autowired
    private ElevesRepository elevesRepository;

    @GetMapping("/eleves")
    public List<Eleves> ListeEleves() {
        return elevesRepository.findAll();
    }
}

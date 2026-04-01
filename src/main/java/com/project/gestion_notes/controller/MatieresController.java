package com.project.gestion_notes.controller;

import com.project.gestion_notes.entity.Matieres;
import com.project.gestion_notes.repository.MatieresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatieresController {

    @Autowired
    private MatieresRepository matieresRepository;

    @GetMapping("/matieres")
    public List<Matieres> ListesMatieres() { return matieresRepository.findAll(); }
}



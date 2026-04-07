package com.project.gestion_notes.web.controller;


import com.project.gestion_notes.entity.Eleves;

import com.project.gestion_notes.repository.ElevesRepository;
import com.project.gestion_notes.web.dto.EleveRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ElevesController {
    @Autowired
    private ElevesRepository elevesRepository;

    @GetMapping("/eleves")
    @Operation(summary = "Récupérer tous les élèves")
    public ResponseEntity<List<Eleves>> getAllEleves() {
        return ResponseEntity.ok((elevesRepository.findAll()));
    }

    @GetMapping("/eleves/{id}")
    @Operation(summary = "Récupérer une Eleve par son id")
    public ResponseEntity<Eleves> getEleveById(@PathVariable int id){

        return elevesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/eleves/{id}")
    @Operation(summary = "Mettre à jour un élève")
    public ResponseEntity<Eleves> updateEleve(
            @PathVariable int id,
            @RequestBody EleveRequest request) {

        Eleves eleve = elevesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Élève introuvable"));

        eleve.setNom(request.getNom());
        eleve.setPrenom(request.getPrenom());

        Eleves updated = elevesRepository.save(eleve);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/eleves/{id}")
    @Operation(summary = "Supprimer un élève selon son id")
    public ResponseEntity<?> deleteEleve(@PathVariable int id){

        return elevesRepository.findById(id)
                .map(eleve -> {
                    elevesRepository.delete(eleve);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/eleves")
    @Operation(summary = "Créer un élève")
    public ResponseEntity<Eleves> createEleve(@RequestBody EleveRequest request) {

        Eleves eleve = new Eleves();
        eleve.setNom(request.getNom());
        eleve.setPrenom(request.getPrenom());

        Eleves saved = elevesRepository.save(eleve);

        return ResponseEntity.status(201).body(saved);
    }


}

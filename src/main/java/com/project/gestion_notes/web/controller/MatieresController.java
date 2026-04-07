package com.project.gestion_notes.web.controller;

import com.project.gestion_notes.entity.Matieres;
import com.project.gestion_notes.repository.MatieresRepository;
import com.project.gestion_notes.web.dto.MatiereRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatieresController {

    @Autowired
    private MatieresRepository matieresRepository;

    @GetMapping("/matieres")
    @Operation(summary = "Récupérer toutes les matieres")
    public ResponseEntity<List<Matieres>> getAllMatieres() {
        return ResponseEntity.ok(matieresRepository.findAll());
    }

    @GetMapping("/matieres/{id}")
    @Operation(summary = "Récupérer une matière par son id")
    public ResponseEntity<Matieres> getMatiereById(@PathVariable int id) {

        return matieresRepository.findById(id)
                .map(ResponseEntity::ok) // 200 si trouvé
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 sinon
    }

    @PostMapping("/matieres")
    @Operation(summary = "Créer une matiere")
    public ResponseEntity<Matieres> createMatiere(@RequestBody MatiereRequest request){

        Matieres matiere = new Matieres();
        matiere.setMatiere(request.getMatiere());

        Matieres saved = matieresRepository.save(matiere);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/matieres/{id}")
    @Operation(summary = "Mettre à jour une matière")
    public ResponseEntity<Matieres> updateMatiere(
            @PathVariable int id,
            @RequestBody MatiereRequest request) {

        return matieresRepository.findById(id)
                .map(matiere -> {
                    matiere.setMatiere(request.getMatiere());
                    Matieres updated = matieresRepository.save(matiere);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/matieres/{id}")
    @Operation(summary = "Supprimer un élève selon id")
    public ResponseEntity<?> deleteMatiere(@PathVariable int id) {

        return matieresRepository.findById(id)
                .map(matiere -> {
                    matieresRepository.delete(matiere);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}



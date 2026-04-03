package com.project.gestion_notes.web.controller;


import com.project.gestion_notes.entity.Eleves;
import com.project.gestion_notes.entity.Matieres;
import com.project.gestion_notes.entity.Notes;
import com.project.gestion_notes.repository.ElevesRepository;
import com.project.gestion_notes.repository.MatieresRepository;
import com.project.gestion_notes.repository.NotesRepository;
import com.project.gestion_notes.web.dto.NoteRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {

    //Permet à Spring d’injecter automatiquement le repository.
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private MatieresRepository matieresRepository;
    @Autowired
    private ElevesRepository elevesRepository;

    /*@GetMapping("/notes")
    @Operation(summary = "Récupérer toutes les notes")
    public ResponseEntity<List<Notes>> listeNotes() {
        List<Notes> notes = notesRepository.findAll();

        if (notes.isEmpty()) {
            // Si aucune note n'est trouvée, on renvoie 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // Sinon, on renvoie la liste avec 200 OK
            return ResponseEntity.ok(notes);
        }
    }*/

    @GetMapping("/notes")
    @Operation(summary = "Récupérer toutes les notes")
    public ResponseEntity<List<Notes>>getAllNotes() {
        return ResponseEntity.ok((notesRepository.findAll()));
    }

    @GetMapping("/notes/{id}")
    @Operation(summary = "Récupérer une note par son id")
    public ResponseEntity<Notes> getNote(@PathVariable int id) {

        return notesRepository.findById(id)
                .map(note -> ResponseEntity.ok(note))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/notes")
    @Operation(summary = "Ajoute une note")
    public ResponseEntity<Notes> ajouterNote(@RequestBody NoteRequest request) {

        // Récupération de l'élève ou 404
        Eleves eleve = elevesRepository.findById(request.getEleveId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Élève introuvable"));

        // Récupération de la matière ou 404
        Matieres matiere = matieresRepository.findById(request.getMatiereId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Matière introuvable"));

        // Création de la note
        Notes note = new Notes();
        note.setNote(request.getValeur());
        note.setEleve(eleve);
        note.setMatiere(matiere);

        // Sauvegarde en base
        Notes saved = notesRepository.save(note);

        // Réponse 201 Created avec la note créée
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/notes/{id}")
    @Operation(summary = "Modifier une note")
    public ResponseEntity<Notes> updateNote(
            @PathVariable int id,
            @RequestBody NoteRequest request) {

        Notes note = notesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Note introuvable"));

        Eleves eleve = elevesRepository.findById(request.getEleveId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Eleve introuvable"));

        Matieres matiere = matieresRepository.findById(request.getMatiereId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Matière introuvable"));

        note.setNote(request.getValeur());
        note.setEleve(eleve);
        note.setMatiere(matiere);

        Notes updated = notesRepository.save(note);

        return ResponseEntity.ok(updated); // 200
    }

    @DeleteMapping("/notes/{id}")
    @Operation(summary = "Supprimer une note selon son id")
    public ResponseEntity<?> deleteNotes(@PathVariable int id){

        return notesRepository.findById(id)
                .map(note -> {
                    notesRepository.delete(note);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

}
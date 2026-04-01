package com.project.gestion_notes.controller;


import com.project.gestion_notes.entity.Notes;
import com.project.gestion_notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotesController {

    //Permet à Spring d’injecter automatiquement le repository.
    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> listeNotes() {
        return notesRepository.findAll();
    }

}
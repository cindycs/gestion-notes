package com.project.gestion_notes.web.controller;


import com.project.gestion_notes.entity.Notes;
import com.project.gestion_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    //Permet à Spring d’injecter automatiquement le repository.
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Notes> listeNotes() {
        return noteRepository.findAll();
    }

}
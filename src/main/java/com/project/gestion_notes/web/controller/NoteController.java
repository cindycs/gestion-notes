package com.project.gestion_notes.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @GetMapping("/notes")
    public String listeNotes() {
        return "Liste des notes";
    }

}

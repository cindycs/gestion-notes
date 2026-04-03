package com.project.gestion_notes.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequest {

    private int valeur;
    private int eleveId;
    private int matiereId;
}
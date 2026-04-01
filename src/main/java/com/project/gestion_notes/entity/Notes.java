package com.project.gestion_notes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_notes;
    private int note;

    //Création des clés étrangères
    @ManyToOne
    @JoinColumn(name = "id_eleve")
    private Eleves eleve;

    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matieres matiere;
}
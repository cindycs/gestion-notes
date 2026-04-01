package com.project.gestion_notes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Eleves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_eleve;
    private String nom;
    private String prenom;

    @ManyToMany
    @JoinTable(
            name = "eleves_matieres",
            joinColumns = @JoinColumn(name = "id_eleve"),
            inverseJoinColumns = @JoinColumn(name = "id_matiere")
    )
    private List<Matieres> matieres;
}

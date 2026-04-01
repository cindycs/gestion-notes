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

public class Matieres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_matiere;
    private String matiere;

    @ManyToMany(mappedBy = "matieres")
    private List<Eleves> eleves;
}

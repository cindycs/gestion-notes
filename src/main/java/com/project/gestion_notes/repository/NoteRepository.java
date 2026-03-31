package com.project.gestion_notes.repository;

import com.project.gestion_notes.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Notes, Integer> {
}

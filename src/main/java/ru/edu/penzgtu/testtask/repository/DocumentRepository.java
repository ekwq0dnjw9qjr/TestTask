package ru.edu.penzgtu.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.testtask.entity.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Query("SELECT d FROM Document d WHERE d.title LIKE %:name%")
    List<Document> findDocumentByTitle(@Param("name") String title);

    @Query("SELECT d FROM Document d WHERE d.title = ?1 AND d.type = ?2 AND d.date = ?3 AND d.author = ?4 AND d.number = ?5")
    Optional<Document> findByUniqueAttributes(String title, String type, LocalDate date, String author, Long number);
}

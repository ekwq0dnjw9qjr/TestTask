package ru.egartech.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egartech.document.entity.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE LOWER(d.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Document> findDocumentByTitle(@Param("title") String title);

    @Query("SELECT d FROM Document d WHERE LOWER(d.type) LIKE LOWER(CONCAT('%', :type, '%'))")
    List<Document> findDocumentByType(@Param("type") String type);

    @Query("SELECT d FROM Document d WHERE LOWER(d.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Document> findDocumentByAuthor(@Param("author") String author);

    @Query("SELECT d FROM Document d WHERE d.date = CAST(:date AS DATE)")
    List<Document> findDocumentByDate(@Param("date") LocalDate date);


    @Query("SELECT d FROM Document d WHERE d.title = ?1 AND d.type = ?2 AND d.date = ?3 AND d.author = ?4 AND d.number = ?5")
    Optional<Document> findByUniqueAttributes(String title, String type, LocalDate date, String author, Long number);
}

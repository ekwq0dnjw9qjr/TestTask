package ru.edu.penzgtu.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.testtask.entity.Document;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Query("SELECT d FROM Document d WHERE d.title LIKE %:name%")
    List<Document> findDocumentByTitle(@Param("name") String title);
}

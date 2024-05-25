package ru.edu.penzgtu.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.testtask.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

}

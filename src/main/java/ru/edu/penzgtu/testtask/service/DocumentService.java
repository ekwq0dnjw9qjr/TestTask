package ru.edu.penzgtu.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.entity.Document;
import ru.edu.penzgtu.testtask.repository.DocumentRepository;
import ru.edu.penzgtu.testtask.service.mapper.DocumentMapper;

import java.util.List;

@Service

public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public DocumentDto getDocumentDto(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return documentMapper.toDto(document);
    }

    public List<DocumentDto> getAllDocumentDtos() {
        List<Document> documents = documentRepository.findAll();
        return documentMapper.toDtos(documents);
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document = documentMapper.toEntity(documentDto);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    public DocumentDto updateDocument(Long id, DocumentDto documentDto) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setTitle(documentDto.getTitle());
        document.setType(documentDto.getType());
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
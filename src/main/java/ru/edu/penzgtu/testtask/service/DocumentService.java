package ru.edu.penzgtu.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.entity.Document;
import ru.edu.penzgtu.testtask.exception.CurrentException;
import ru.edu.penzgtu.testtask.exception.ErrorType;
import ru.edu.penzgtu.testtask.repository.DocumentRepository;
import ru.edu.penzgtu.testtask.service.mapper.DocumentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;


    public DocumentDto getDocumentDto(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new CurrentException(ErrorType.NOT_FOUND,"Document with id: " + id + " not found"));
        return documentMapper.toDto(document);
    }


    public List<DocumentDto> findDocumentsByTitle(String title) {
        List<Document> documents = documentRepository.findDocumentByTitle(title);
        return documentMapper.toListDto(documents);
    }

    public List<DocumentDto> getAllDocuments() {
        List<Document> documents = documentRepository.findAll();
        return documentMapper.toListDto(documents);
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        if (documentRepository.findByUniqueAttributes(documentDto.getTitle(), documentDto.getType(),
                documentDto.getDate(), documentDto.getAuthor(), documentDto.getNumber()).isPresent()) {
            throw new IllegalArgumentException("Document with the same attributes already exists");
        }
        Document document = documentMapper.toEntity(documentDto);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    public DocumentDto updateDocument(Long id, DocumentDto documentDto) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new CurrentException(ErrorType.NOT_FOUND,"Document with id: " + id + " not found"));
        document.setTitle(documentDto.getTitle());
        document.setType(documentDto.getType());
        document.setAuthor(documentDto.getAuthor());
        document.setDate(documentDto.getDate());
        document.setNumber(documentDto.getNumber());
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}

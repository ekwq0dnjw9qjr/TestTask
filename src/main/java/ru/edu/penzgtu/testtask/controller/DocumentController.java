package ru.edu.penzgtu.testtask.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")

public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @GetMapping("/{id}")
    public DocumentDto getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentDto(id);
    }

    @GetMapping
    public List<DocumentDto> getAllDocuments() {
        return documentService.getAllDocumentDtos();
    }

    @PostMapping
    public DocumentDto createDocument(@RequestBody @Valid DocumentDto documentDto) {
        return documentService.createDocument(documentDto);
    }

    @PutMapping("/{id}")
    public DocumentDto updateDocument(@PathVariable Long id, @RequestBody @Valid DocumentDto documentDto) {
        return documentService.updateDocument(id, documentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }
}

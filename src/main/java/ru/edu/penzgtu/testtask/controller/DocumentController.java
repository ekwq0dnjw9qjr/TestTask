package ru.edu.penzgtu.testtask.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.testtask.baseresponse.BaseResponseService;
import ru.edu.penzgtu.testtask.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    private final BaseResponseService baseResponseService;

    @GetMapping("/{id}")
    public ResponseWrapper<DocumentDto> getDocumentById(@PathVariable Long id) {
        return baseResponseService.wrapSuccessResponse(documentService.getDocumentDto(id));
    }

    @GetMapping("/documents")
    public ResponseWrapper<List<DocumentDto>> getDocumentsByTitle(@RequestParam String title) {
        return baseResponseService.wrapSuccessResponse(documentService.findDocumentsByTitle(title));
    }

    @GetMapping
    public List<DocumentDto> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(@RequestBody @Valid DocumentDto documentDto) {
        try {
            DocumentDto createDocument = documentService.createDocument(documentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createDocument);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/{id}")
    public DocumentDto updateDocument(@PathVariable Long id, @RequestBody @Valid DocumentDto documentDto) {
        return documentService.updateDocument(id, documentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }


}

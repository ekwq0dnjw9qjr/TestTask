package ru.edu.penzgtu.testtask.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.testtask.baseresponse.BaseResponseService;
import ru.edu.penzgtu.testtask.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.entity.Document;
import ru.edu.penzgtu.testtask.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Tag(name = "Documents", description = "Operations on documents")
public class DocumentController {
    private final DocumentService documentService;
    private final BaseResponseService baseResponseService;

    @GetMapping("/{id}")
    public ResponseWrapper<DocumentDto> getDocumentById(@PathVariable Long id) {
        return baseResponseService.wrapSuccessResponse(documentService.getDocumentDto(id));
    }
    @Operation(
            summary = "Получение художника по имени",
            description = "Позволяет выгрузить одного художника по имени из БД"
    )
    @GetMapping("/documents")
    public ResponseWrapper<List<DocumentDto>> getDocumentsByName(@RequestParam String title) {
        return baseResponseService.wrapSuccessResponse(documentService.findDocumentsByTitle(title));
    }

    @GetMapping
    public List<DocumentDto> getAllDocuments() {
        return documentService.getAllDocumentDtos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentDto createDocument(@RequestBody @Valid DocumentDto documentDto) {
        return documentService.createDocument(documentDto);
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

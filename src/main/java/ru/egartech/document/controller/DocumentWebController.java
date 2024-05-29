package ru.egartech.document.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egartech.document.dto.DocumentDto;
import ru.egartech.document.service.DocumentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentWebController {
    private final DocumentService documentService;

    @GetMapping
    public String listDocuments(Model model) {
        model.addAttribute("documents", documentService.getAllDocuments());
        return "documentsList";
    }

    @GetMapping("/search")
    public String searchDocuments(@RequestParam("searchType") String searchType,
                                  @RequestParam("query") String query,
                                  Model model) {
        List<DocumentDto> documentDtos;
        switch (searchType.toLowerCase()) {
            case "title":
                documentDtos = documentService.findDocumentsByTitle(query);
                break;
            case "type":
                documentDtos = documentService.findDocumentsByType(query);
                break;
            case "author":
                documentDtos = documentService.findDocumentsByAuthor(query);
                break;
            default:
                documentDtos = new ArrayList<>();
                break;
        }
        model.addAttribute("documents", documentDtos);
        return "documentsList";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("document", new DocumentDto());
        return "createDocument";
    }

    @PostMapping("/save")
    public String saveDocument(@ModelAttribute DocumentDto documentDto) {
        documentService.createDocument(documentDto);
        return "redirect:/documents";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        DocumentDto documentDto = documentService.getDocumentDto(id);
        if (documentDto == null) {
            return "redirect:/documents";
        }
        model.addAttribute("document", documentDto);
        return "editDocument";
    }

    @PostMapping("/update")
    public String updateDocument(@ModelAttribute DocumentDto documentDto) {
        documentService.updateDocument(documentDto.getId(), documentDto);
        return "redirect:/documents";
    }

    @PostMapping("/delete/{id}")
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return "redirect:/documents";
    }
}

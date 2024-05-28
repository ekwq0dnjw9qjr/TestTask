package ru.edu.penzgtu.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.service.DocumentService;

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
    public String searchDocuments(@RequestParam("query") String query, Model model) {
        model.addAttribute("documents", documentService.findDocumentsByTitle(query));
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


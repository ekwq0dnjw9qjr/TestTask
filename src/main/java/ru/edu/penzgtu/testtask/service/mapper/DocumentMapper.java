package ru.edu.penzgtu.testtask.service.mapper;

import org.mapstruct.Mapper;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.entity.Document;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DocumentMapper {

    public abstract DocumentDto toDto(Document document);

    public abstract Document toEntity(DocumentDto documentDto);

    public abstract List<DocumentDto> toListDto(List<Document> documents);

    public abstract List<Document> toEntities(List<DocumentDto> documentDtos);
}

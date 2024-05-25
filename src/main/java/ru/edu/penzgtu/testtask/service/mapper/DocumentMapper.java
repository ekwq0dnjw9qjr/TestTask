package ru.edu.penzgtu.testtask.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.edu.penzgtu.testtask.dto.DocumentDto;
import ru.edu.penzgtu.testtask.entity.Document;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentDto toDto(Document document);

    Document toEntity(DocumentDto documentDto);

    List<DocumentDto> toDtos(List<Document> documents);

    List<Document> toEntities(List<DocumentDto> documentDtos);



}

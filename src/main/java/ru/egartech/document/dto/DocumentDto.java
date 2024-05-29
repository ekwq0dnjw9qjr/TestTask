package ru.egartech.document.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    @NotBlank
    private String title;

    @JsonProperty("type")
    @NotBlank
    private String type;

    @JsonProperty("date")
    @NotNull
    private LocalDate date;

    @JsonProperty("author")
    @NotBlank
    private String author;

    @JsonProperty("number")
    @Positive
    private Long number;

}


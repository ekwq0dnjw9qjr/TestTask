package ru.edu.penzgtu.testtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
    @NotBlank
    private String date;

    @JsonProperty("author")
    @NotBlank
    private String author;

    @JsonProperty("number")
    @Positive
    private Long number;


}


package ru.edu.penzgtu.testtask.baseresponse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    private String code;
    private String title;
    private String text;
}

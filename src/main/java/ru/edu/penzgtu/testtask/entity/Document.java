package ru.edu.penzgtu.testtask.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "type")
    @NotBlank
    private String type;

    public Long getId() {
        return this.id;
    }

    public @NotBlank String getTitle() {
        return this.title;
    }

    public @NotBlank String getType() {
        return this.type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public void setType(@NotBlank String type) {
        this.type = type;
    }
}
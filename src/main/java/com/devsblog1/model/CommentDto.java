package com.devsblog1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CommentDto {
    private String comment;

    public CommentDto() {
    }

    public CommentDto(String comment) {
        this.comment = comment;
    }
}

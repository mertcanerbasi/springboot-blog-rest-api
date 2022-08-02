package com.springboot.blog.payload.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private Long id;

    //Email should not be null or less than 5 chars
    @NotEmpty
    @Size(min = 4,message = "Comment name should have at least 4 characters")
    private String name;

    //Email valid and not empty
    @NotEmpty
    @Email
    private String email;

    //Email should not be null or less than 5 chars
    @NotEmpty
    @Size(min = 5,message = "Comment messageBody should have at least 5 characters")
    private String messageBody;
}

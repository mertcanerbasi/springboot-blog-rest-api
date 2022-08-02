package com.springboot.blog.payload.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private Long id;

    //Title should not be null or less than 5 chars
    @NotEmpty
    @Size(min = 4,message = "Post title should have at least 4 characters")
    private String title;

    //Description should not be null or less than 5 chars
    @NotEmpty
    @Size(min = 10,message = "Post description should have at least 10 characters")
    private String description;

    //Content must not be empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}

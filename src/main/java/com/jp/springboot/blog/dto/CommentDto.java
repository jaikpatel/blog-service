package com.jp.springboot.blog.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "Comment model information")
public class CommentDto {

    @ApiModelProperty(value = "Comment ID")
    private long id;

    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Comment name should not be NULL or empty")
    private String name;

    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Comment email should not be NULL or empty")
    @Email
    private String email;

    @ApiModelProperty(value = "Comment body")
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}

package com.app.blog.service.dto;

import com.app.blog.domain.Post;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * This data transfer object contains the information of a single post
 * entry and specifies validation rules that are used to ensure that only
 * valid information can be saved to the used database.
 */
public final class PostDTO {
    private String id;

    @NotEmpty
    @Size(max = Post.MAX_LENGTH_TITLE)
    private String title;

    @Size(max = Post.MAX_LENGTH_CONTENT)
    private String content;

    public PostDTO() {
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format(
                "PostDTO[id=%s, title=%s, content=%s]",
                this.id,
                this.title,
                this.content
        );
    }
}
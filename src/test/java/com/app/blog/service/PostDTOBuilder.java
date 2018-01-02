package com.app.blog.service;

import com.app.blog.service.dto.PostDTO;

class PostDTOBuilder {

    private String id;
    private String title;
    private String content;

    PostDTOBuilder() {
    }

    PostDTOBuilder content(String content) {
        this.content = content;
        return this;
    }

    PostDTOBuilder id(String id) {
        this.id = id;
        return this;
    }

    PostDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    PostDTO build() {
        PostDTO dto = new PostDTO();

        dto.setId(id);
        dto.setTitle(title);
        dto.setContent(content);

        return dto;
    }
}
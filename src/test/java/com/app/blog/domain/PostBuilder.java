package com.app.blog.domain;

import com.app.blog.domain.Post;
import org.springframework.test.util.ReflectionTestUtils;

public class PostBuilder {

    private String id;
    private String title = "NOT_IMPORTANT";
    private String content;

    public PostBuilder() {
    }

    public PostBuilder content(String content) {
        this.content = content;
        return this;
    }

    public PostBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PostBuilder title(String title) {
        this.title = title;
        return this;
    }

    public Post build() {
        Post post = Post.getBuilder()
                .title(title)
                .content(content)
                .build();

        ReflectionTestUtils.setField(post, "id", id);

        return post;
    }
}
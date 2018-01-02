package com.app.blog.domain;

import com.app.blog.util.Precondition;
import org.springframework.data.annotation.Id;

import static com.app.blog.util.Precondition.isTrue;
import static com.app.blog.util.Precondition.notEmpty;
import static com.app.blog.util.Precondition.notNull;

public final class Post {
    public static final int MAX_LENGTH_CONTENT = 5000;
    public static final int MAX_LENGTH_TITLE = 100;

    @Id
    private String id;

    private String title;

    private String content;

    public Post() {}

    private Post(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void update(String title, String content) {
        checkTitleAndContent(title, content);

        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format(
                "Post[id=%s, title=%s, content=%s]",
                this.id,
                this.title,
                this.content
        );
    }

    public static class Builder {

        private String title;

        private String content;

        private Builder() {}

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Post build() {
            Post build = new Post(this);

            build.checkTitleAndContent(build.getTitle(), build.getContent());

            return build;
        }
    }

    private void checkTitleAndContent(String title, String content) {
        notNull(title, "Title cannot be null");
        notEmpty(title, "Title cannot be empty");
        isTrue(title.length() <= MAX_LENGTH_TITLE,
                "Title cannot be longer than %d characters",
                MAX_LENGTH_TITLE
        );

        if (content != null) {
            isTrue(content.length() <= MAX_LENGTH_CONTENT,
                    "Content cannot be longer than %d characters",
                    MAX_LENGTH_CONTENT
            );
        }
    }
}
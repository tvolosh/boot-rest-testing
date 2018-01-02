package com.app.blog.asserts;

import com.app.blog.domain.Post;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class PostAssert extends AbstractAssert<PostAssert, Post> {
    private PostAssert(Post actual) {
        super(actual, PostAssert.class);
    }

    public static PostAssert assertThatPost(Post actual) {
        return new PostAssert(actual);
    }

    public PostAssert hasContent(String expectedContent) {
        isNotNull();

        String actualContent = actual.getContent();
        assertThat(actualContent)
                .overridingErrorMessage("Expected content to be <%s> but was <%s>",
                        expectedContent,
                        actualContent
                )
                .isEqualTo(expectedContent);

        return this;
    }

    public PostAssert hasId(String expectedId) {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <%s> but was <%s>",
                        expectedId,
                        actualId
                )
                .isEqualTo(expectedId);

        return this;
    }

    public PostAssert hasNoContent() {
        isNotNull();

        String actualContent = actual.getContent();
        assertThat(actualContent)
                .overridingErrorMessage("Expected content to be <null> but was <%s>", actualContent)
                .isNull();

        return this;
    }

    public PostAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }

    public PostAssert hasTitle(String expectedTitle) {
        isNotNull();

        String actualTitle = actual.getTitle();
        assertThat(actualTitle)
                .overridingErrorMessage("Expected title to be <%s> but was <%s>",
                        expectedTitle,
                        actualTitle
                )
                .isEqualTo(expectedTitle);

        return this;
    }
}

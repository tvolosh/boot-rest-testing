package com.app.blog.asserts;

import com.app.blog.service.dto.PostDTO;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDTOAssert extends AbstractAssert<PostDTOAssert, PostDTO> {

    private PostDTOAssert(PostDTO actual) {
        super(actual, PostDTOAssert.class);
    }

    public static PostDTOAssert assertThatPostDTO(PostDTO actual) {
        return new PostDTOAssert(actual);
    }

    public PostDTOAssert hasContent(String expectedContent) {
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

    public PostDTOAssert hasId(String expectedId) {
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

    public PostDTOAssert hasNoContent() {
        isNotNull();

        String actualContent = actual.getContent();
        assertThat(actualContent)
                .overridingErrorMessage("expected content to be <null> but was <%s>", actualContent)
                .isNull();

        return this;
    }

    public PostDTOAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }

    public PostDTOAssert hasTitle(String expectedTitle) {
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

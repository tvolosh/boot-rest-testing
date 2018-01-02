package com.app.blog.domain;

import com.app.blog.util.StringTestUtil;
import org.junit.Test;

import static com.app.blog.asserts.PostAssert.*;

public class PostTest {

    private static final String CONTENT = "content";
    private static final String TITLE = "title";

    private static final int MAX_LENGTH_CONTENT = 5000;
    private static final int MAX_LENGTH_TITLE = 100;

    private static final String UPDATED_CONTENT = "updatedContent";
    private static final String UPDATED_TITLE = "updatedTitle";

    @Test(expected = NullPointerException.class)
    public void build_TitleIsNull_ShouldThrowException() {
        Post.getBuilder()
                .title(null)
                .content(CONTENT)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_TitleIsEmpty_ShouldThrowException() {
        Post.getBuilder()
                .title("")
                .content(CONTENT)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_TitleIsTooLong_ShouldThrowException() {
        String tooLongTitle = StringTestUtil.createStringWithLength(MAX_LENGTH_TITLE + 1);
        Post.getBuilder()
                .title(tooLongTitle)
                .content(CONTENT)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_ContentIsTooLong_ShouldThrowException() {
        String tooLongContent = StringTestUtil.createStringWithLength(MAX_LENGTH_CONTENT + 1);
        Post.getBuilder()
                .title(TITLE)
                .content(tooLongContent)
                .build();
    }

    @Test
    public void build_WithoutContent_ShouldCreateNewPostEntryWithCorrectTitle() {
        Post build = Post.getBuilder()
                .title(TITLE)
                .build();

        assertThatPost(build)
                .hasNoId()
                .hasTitle(TITLE)
                .hasNoContent();
    }

    @Test
    public void build_WithTitleAndContent_ShouldCreateNewPostEntryWithCorrectTitleAndContent() {
        Post build = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        assertThatPost(build)
                .hasNoId()
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test
    public void build_WithMaxLengthTitleAndContent_ShouldCreateNewPostEntryWithCorrectTitleAndContent() {
        String maxLengthTitle = StringTestUtil.createStringWithLength(MAX_LENGTH_TITLE);
        String maxLengthContent = StringTestUtil.createStringWithLength(MAX_LENGTH_CONTENT);

        Post build = Post.getBuilder()
                .title(maxLengthTitle)
                .content(maxLengthContent)
                .build();

        assertThatPost(build)
                .hasNoId()
                .hasTitle(maxLengthTitle)
                .hasContent(maxLengthContent);
    }

    @Test(expected = NullPointerException.class)
    public void update_TitleIsNull_ShouldThrowException() {
        Post updated = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        updated.update(null, UPDATED_CONTENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_TitleIsEmpty_ShouldThrowException() {
        Post updated = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        updated.update("", UPDATED_CONTENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_TitleIsTooLong_ShouldThrowException() {
        Post updated = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        String tooLongTitle = StringTestUtil.createStringWithLength(MAX_LENGTH_TITLE + 1);
        updated.update(tooLongTitle, UPDATED_CONTENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ContentIsTooLong_ShouldThrowException() {
        Post updated = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        String tooLongContent = StringTestUtil.createStringWithLength(MAX_LENGTH_CONTENT + 1);
        updated.update(UPDATED_TITLE, tooLongContent);
    }

    @Test
    public void update_ContentIsNull_ShouldUpdateTitleAndContent() {
        Post updated = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        updated.update(UPDATED_TITLE, null);

        assertThatPost(updated)
                .hasTitle(UPDATED_TITLE)
                .hasNoContent();
    }

    @Test
    public void update_MaxLengthTitleAndContent_ShouldUpdateTitleAndContent() {
        Post updated = Post.getBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        String maxLengthTitle = StringTestUtil.createStringWithLength(MAX_LENGTH_TITLE);
        String maxLengthContent = StringTestUtil.createStringWithLength(MAX_LENGTH_CONTENT);

        updated.update(maxLengthTitle, maxLengthContent);

        assertThatPost(updated)
                .hasTitle(maxLengthTitle)
                .hasContent(maxLengthContent);
    }
}
package com.app.blog.service;

import com.app.blog.domain.Post;
import com.app.blog.domain.PostBuilder;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.dto.PostDTO;
import com.app.blog.service.exception.PostNotFoundException;
import com.app.blog.service.impl.MongoDBPostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.app.blog.asserts.PostAssert.*;
import static com.app.blog.asserts.PostDTOAssert.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MongoDbPostServiceTest {

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";

    @Mock
    private PostRepository repository;

    private MongoDBPostService service;

    @Before
    public void setUp() {
        this.service = new MongoDBPostService(repository);
    }

    @Test
    public void create_ShouldSaveNewPostEntry() {
        PostDTO newPost = new PostDTOBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        when(repository.save(isA(Post.class))).thenAnswer(invocation -> (Post) invocation.getArguments()[0]);

        service.create(newPost);

        ArgumentCaptor<Post> savedPostArgument = ArgumentCaptor.forClass(Post.class);

        verify(repository, times(1)).save(savedPostArgument.capture());
        verifyNoMoreInteractions(repository);

        Post savedPost = savedPostArgument.getValue();
        assertThatPost(savedPost)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test
    public void create_ShouldReturnTheInformationOfCreatedPostEntry() {
        PostDTO newPost = new PostDTOBuilder()
                .title(TITLE)
                .content(CONTENT)
                .build();

        when(repository.save(isA(Post.class))).thenAnswer(invocation -> {
            Post persisted = (Post) invocation.getArguments()[0];
            ReflectionTestUtils.setField(persisted, "id", ID);
            return persisted;
        });

        PostDTO returned = service.create(newPost);

        assertThatPostDTO(returned)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test(expected = PostNotFoundException.class)
    public void delete_PostEntryNotFound_ShouldThrowException() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());

        service.findById(ID);
    }

    @Test
    public void delete_PostEntryFound_ShouldDeleteTheFoundPostEntry() {
        Post deleted = new PostBuilder()
                .id(ID)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(deleted));

        service.delete(ID);

        verify(repository, times(1)).delete(deleted);
    }

    @Test
    public void delete_PostEntryFound_ShouldReturnTheDeletedPostEntry() {
        Post deleted = new PostBuilder()
                .id(ID)
                .title(TITLE)
                .content(CONTENT)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(deleted));

        PostDTO returned = service.delete(ID);

        assertThatPostDTO(returned)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test
    public void findAll_OnePostEntryFound_ShouldReturnTheInformationOfFoundPostEntry() {
        Post expected = new PostBuilder()
                .id(ID)
                .title(TITLE)
                .content(CONTENT)
                .build();

        when(repository.findAll()).thenReturn(Arrays.asList(expected));

        List<PostDTO> postEntries = service.findAll();
        assertThat(postEntries).hasSize(1);

        PostDTO actual = postEntries.iterator().next();
        assertThatPostDTO(actual)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test(expected = PostNotFoundException.class)
    public void findById_PostEntryNotFound_ShouldThrowException() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());

        service.findById(ID);
    }

    @Test
    public void findById_PostEntryFound_ShouldReturnTheInformationOfFoundPostEntry() {
        Post found = new PostBuilder()
                .id(ID)
                .title(TITLE)
                .content(CONTENT)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(found));

        PostDTO returned = service.findById(ID);

        assertThatPostDTO(returned)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test(expected = PostNotFoundException.class)
    public void update_UpdatedPostEntryNotFound_ShouldThrowException() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());

        PostDTO updated = new PostDTOBuilder()
                .id(ID)
                .build();

        service.update(updated);
    }

    @Test
    public void update_UpdatedPostEntryFound_ShouldSaveUpdatedPostEntry() {
        Post existing = new PostBuilder()
                .id(ID)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        PostDTO updated = new PostDTOBuilder()
                .id(ID)
                .title(TITLE)
                .content(CONTENT)
                .build();

        service.update(updated);

        verify(repository, times(1)).save(existing);
        assertThatPost(existing)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }

    @Test
    public void update_UpdatedPostEntryFound_ShouldReturnTheInformationOfUpdatedPostEntry() {
        Post existing = new PostBuilder()
                .id(ID)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        PostDTO updated = new PostDTOBuilder()
                .id(ID)
                .title(TITLE)
                .content(CONTENT)
                .build();

        PostDTO returned = service.update(updated);
        assertThatPostDTO(returned)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasContent(CONTENT);
    }
}
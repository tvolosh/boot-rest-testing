package com.app.blog.service.impl;

import com.app.blog.domain.Post;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.PostService;
import com.app.blog.service.dto.PostDTO;
import com.app.blog.service.exception.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
/**
 * This service class saves {@link com.app.blog.domain.Post} objects
 * to MongoDB database.
 */
@Service
public final class MongoDBPostService implements PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBPostService.class);

    private final PostRepository repository;

    @Autowired
    public MongoDBPostService(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostDTO create(PostDTO post) {
        LOGGER.info("Creating a new post entry with information: {}", post);

        Post persisted = Post.getBuilder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new post entry with information: {}", persisted);

        return convertToDto(persisted);
    }

    @Override
    public PostDTO delete(String id) {
        LOGGER.info("Deleting a post entry with id: {}", id);

        Post deleted = findPostById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted post entry with informtation: {}", deleted);

        return convertToDto(deleted);
    }

    @Override
    public List<PostDTO> findAll() {
        LOGGER.info("Finding all post entries.");

        List<Post> postEntries = repository.findAll();

        LOGGER.info("Found {} post entries", postEntries.size());

        return convertToDTOs(postEntries);
    }

    private List<PostDTO> convertToDTOs(List<Post> models) {
        return models.stream()
                .map(this::convertToDto)
                .collect(toList());
    }

    @Override
    public PostDTO findById(String id) {
        LOGGER.info("Finding post entry with id: {}", id);

        Post found = findPostById(id);

        LOGGER.info("Found post entry: {}", found);

        return convertToDto(found);
    }

    @Override
    public PostDTO update(PostDTO post) {
        LOGGER.info("Updating post entry with information: {}", post);

        Post updated = findPostById(post.getId());
        updated.update(post.getTitle(), post.getContent());
        updated = repository.save(updated);

        LOGGER.info("Updated post entry with information: {}", updated);

        return convertToDto(updated);
    }

    private Post findPostById(String id) {
        Optional<Post> result = repository.findOne(id);
        return result.orElseThrow(() -> new PostNotFoundException(id));

    }

    private PostDTO convertToDto(Post model) {
        PostDTO dto = new PostDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setContent(model.getContent());

        return dto;
    }
}
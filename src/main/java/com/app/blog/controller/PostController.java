package com.app.blog.controller;

import com.app.blog.domain.Post;
import com.app.blog.service.PostService;
import com.app.blog.service.dto.PostDTO;
import com.app.blog.service.exception.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller provides the public API that is used to manage the information
 * of post entries.
 */
@RestController
@RequestMapping("/api/post")
final class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    private final PostService service;

    @Autowired
    PostController(PostService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    PostDTO create(@RequestBody @Valid PostDTO postEntry) {
        LOGGER.info("Creating a new post entry with information: {}", postEntry);

        PostDTO created = service.create(postEntry);
        LOGGER.info("Created a new post entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    PostDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting post entry with id: {}", id);

        PostDTO deleted = service.delete(id);
        LOGGER.info("Deleted post entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PostDTO> findAll() {
        LOGGER.info("Finding all post entries");

        List<PostDTO> postEntries = service.findAll();
        LOGGER.info("Found {} post entries", postEntries.size());

        return postEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    PostDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding post entry with id: {}", id);

        PostDTO postEntry = service.findById(id);
        LOGGER.info("Found post entry with information: {}", postEntry);

        return postEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    PostDTO update(@RequestBody @Valid PostDTO postEntry) {
        LOGGER.info("Updating post entry with information: {}", postEntry);

        PostDTO updated = service.update(postEntry);
        LOGGER.info("Updated post entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePostNotFound(PostNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
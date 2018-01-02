package com.app.blog.service;


import com.app.blog.service.dto.PostDTO;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.app.blog.service.dto.PostDTO} objects.
 */
public interface PostService {

    /**
     * Creates a new post entry.
     * @param post  The information of the created post entry.
     * @return      The information of the created post entry.
     */
    PostDTO create(PostDTO post);

    /**
     * Deletes a post entry.
     * @param id    The id of the deleted post entry.
     * @return      THe information of the deleted post entry.
     * @throws com.app.blog.service.exception.PostNotFoundException if no post entry is found.
     */
    PostDTO delete(String id);

    /**
     * Finds all post entries.
     * @return      The information of all post entries.
     */
    List<PostDTO> findAll();

    /**
     * Finds a single post entry.
     * @param id    The id of the requested post entry.
     * @return      The information of the requested post entry.
     * @throws com.app.blog.service.exception.PostNotFoundException if no post entry is found.
     */
    PostDTO findById(String id);

    /**
     * Updates the information of a post entry.
     * @param post  The information of the updated post entry.
     * @return      The information of the updated post entry.
     * @throws com.app.blog.service.exception.PostNotFoundException if no post entry is found.
     */
    PostDTO update(PostDTO post);
}
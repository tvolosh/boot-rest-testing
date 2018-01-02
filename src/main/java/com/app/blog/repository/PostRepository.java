package com.app.blog.repository;

import com.app.blog.domain.Post;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link com.app.blog.domain.Post}
 * objects.
 */
public interface  PostRepository extends Repository<Post, String> {
    /**
     * Deletes a post entry from the database.
     * @param deleted   The deleted post entry.
     */
    void delete(Post deleted);

    /**
     * Finds all post entries from the database.
     * @return  The information of all post entries that are found from the database.
     */
    List<Post> findAll();

    /**
     * Finds the information of a single post entry.
     * @param id    The id of the requested post entry.
     * @return      The information of the found post entry. If no post entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Post> findOne(String id);

    /**
     * Saves a new post entry to the database.
     * @param saved The information of the saved post entry.
     * @return      The information of the saved post entry.
     */
    Post save(Post saved);
}

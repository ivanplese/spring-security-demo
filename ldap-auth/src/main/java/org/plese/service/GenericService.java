package org.plese.service;

import org.plese.model.IMessage;

import java.util.List;

/**
 * Created by iples on 7/10/14.
 * Generic interface for message services
 */
public interface GenericService {

    /**
     *  Retrieves a single post.
     */
    public IMessage getSingle(Long id);

    /**
     *  Retrieves all posts.
     */
    public List<IMessage> getAll();

    /**
     * Adds a new post.
     */
    public Boolean add(IMessage post);

    /**
     * Edits a post.
     */
    public Boolean edit(IMessage post);

    /**
     * Deletes a post.
     */
    public Boolean delete(IMessage post);
}

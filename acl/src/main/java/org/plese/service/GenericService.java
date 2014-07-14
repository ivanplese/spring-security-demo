package org.plese.service;

import org.plese.model.IMessage;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by iples on 7/10/14.
 * Generic interface for message services
 */
public interface GenericService {

    /**
     *  Retrieves a single post.
     */
    @PostAuthorize("hasPermission(returnObject, 'WRITE')")
    public IMessage getSingle(Long id);

    /**
     *  Retrieves all posts.
     */
    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<IMessage> getAll();

    /**
     * Adds a new post.
     */
    public Boolean add(IMessage post);

    /**
     * Edits a post.
     */
    @PreAuthorize("hasPermission(#post, 'WRITE')")
    public Boolean edit(IMessage post);

    /**
     * Deletes a post.
     */
    @PreAuthorize("hasPermission(#post, 'WRITE')")
    public Boolean delete(IMessage post);
}

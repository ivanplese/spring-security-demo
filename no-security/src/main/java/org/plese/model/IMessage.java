package org.plese.model;

import java.util.Date;

/**
 * Created by iples on 7/10/14.
 * Interface for message model
 */
public interface IMessage {

    public Long getId();

    public void setId(Long id);

    public Date getDate();

    public void setDate(Date date);

    public String getText();

    public void setText(String tex);
}

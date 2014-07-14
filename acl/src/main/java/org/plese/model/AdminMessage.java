package org.plese.model;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by iplese
 */
public class AdminMessage implements IMessage {
    private Long id;
    private Date date;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

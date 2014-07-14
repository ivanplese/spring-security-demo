package org.plese.dao;

import org.hibernate.SessionFactory;
import org.plese.model.IMessage;
import org.plese.model.PublicMessage;

import java.util.List;

/**
 * Created by ivan on 10/07/14.
 */
public interface IMessageDao {
    PublicMessage get(Long id);

    List<IMessage> getAll();

    Boolean add(IMessage message);

    Boolean edit(IMessage message);

    Boolean delete(IMessage message);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);
}

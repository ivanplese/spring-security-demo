package org.plese.DAO;

import org.hibernate.*;
import org.plese.model.IMessage;
import org.plese.model.PublicMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.String.format;

/**
 * Created by iplese
 * Data base CRUD operations for message model.
 */
@Repository
public class MessageDao{

    @Autowired
    private SessionFactory sessionFactory;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PublicMessage.class);



    public PublicMessage get(Long id) {

        Session session = sessionFactory.getCurrentSession();

        PublicMessage record;
        try {
            record = (PublicMessage) session.get(PublicMessage.class, id);
            Hibernate.initialize(record);

        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error("unable to get the record: " + ex);
            throw ex;
        }

        return record;
    }

    public List<IMessage> getAll() {

        Session session = sessionFactory.getCurrentSession();

        List records;
        try {

             records = session.createCriteria(PublicMessage.class).list();

        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error("unable to get the records: " + ex);
            throw ex;
        }

        return records;
    }

    public Boolean add(IMessage message){
        if (message == null) {
            logger.error("parameter is null");
            throw new NullPointerException("parameter is null");
        }

        logger.debug("creating... ");
        Session session = sessionFactory.getCurrentSession();
        try {
            logger.debug("trying to save... ");
            session.save(message);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error(format("unable to create" + ex));
            throw ex;
        }
        logger.debug("returning the passed object... ");
        return true;
    }

    public Boolean edit(IMessage message){
        if (message == null) {
            logger.error("parameter is null");
            throw new NullPointerException("user is null");
        }

        logger.debug("updating record");
        Session session = sessionFactory.getCurrentSession();
        try {

            session.update(message);

        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error(format("unable to delete the record") + ex);
            throw ex;
        }

        return true;
    }

    public Boolean delete(IMessage message){
        if (message == null) {
            logger.error("cant delete, id is null");
            throw new NullPointerException("parameter id is null");
        }

        logger.debug("deleting record with text : " + message.getText());
        Session session = sessionFactory.getCurrentSession();
        try {

            session.delete(message);

        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error(format("unable to delete the record with id: %s", message.getId()) + ex);
            throw ex;
        }
        return true;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

package org.plese.DAO;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.plese.model.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.String.format;

/**
 * Created by iplese
 */
@Repository
public class GenericMessageDao {
    Logger logger = Logger.getLogger(GenericMessageDao.class);

    @Autowired
    protected SessionFactory sessionFactory;

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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

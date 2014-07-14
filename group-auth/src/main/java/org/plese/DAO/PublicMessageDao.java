package org.plese.DAO;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.plese.model.IMessage;
import org.plese.model.PublicMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by iplese
 * Data base CRUD operations for message model.
 */
@Repository
public class PublicMessageDao extends GenericMessageDao{

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
}

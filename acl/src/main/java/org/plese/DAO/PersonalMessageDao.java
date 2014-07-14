package org.plese.DAO;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.plese.model.IMessage;
import org.plese.model.PersonalMessage;

import java.util.List;

/**
 * Created by iplese.
 */
public class PersonalMessageDao extends GenericMessageDao{

    public PersonalMessage get(Long id) {

        Session session = sessionFactory.getCurrentSession();

        PersonalMessage record;
        try {
            record = (PersonalMessage) session.get(PersonalMessage.class, id);
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

            records = session.createCriteria(PersonalMessage.class).list();

        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error("unable to get the records: " + ex);
            throw ex;
        }

        return records;
    }
}

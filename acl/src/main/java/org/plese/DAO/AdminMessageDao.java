package org.plese.DAO;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.plese.model.AdminMessage;
import org.plese.model.IMessage;
import java.util.List;

/**
 * Created by iplese.
 */
public class AdminMessageDao extends GenericMessageDao{

    public AdminMessage get(Long id) {

        Session session = sessionFactory.getCurrentSession();

        AdminMessage record;
        try {
            record = (AdminMessage) session.get(AdminMessage.class, id);
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

            records = session.createCriteria(AdminMessage.class).list();

        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error("unable to get the records: " + ex);
            throw ex;
        }

        return records;
    }
}

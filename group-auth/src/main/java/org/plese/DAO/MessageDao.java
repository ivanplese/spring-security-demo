package org.plese.DAO;

import com.sun.jndi.url.dns.dnsURLContext;
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
public class MessageDao extends GenericMessageDao{

    public MessageDao() {
        super();
    }

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
}

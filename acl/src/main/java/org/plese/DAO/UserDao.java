package org.plese.DAO;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.plese.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDao implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserDao.class);

    @SuppressWarnings("all")
    public User findByUserName(String username) {

        List<User> users = new ArrayList<User>();
        try{
        users = sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            logger.error("unable to get the record: " + ex);
            throw ex;
        }
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
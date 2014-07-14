package org.plese.service;

import org.apache.log4j.Logger;
import org.plese.DAO.PublicMessageDao;
import org.plese.model.IMessage;
import org.plese.model.PublicMessage;
import java.util.List;


/**
 * Created by iplese
 * Controls all transaction related to public messages.
 */
public class PublicService implements GenericService{
    Logger logger = Logger.getLogger(PublicService.class);

    private PublicMessageDao publicMessageDao;

    public PublicMessage getSingle(Long id) {
        logger.debug("Retrieving single personal message");
        // Run query then return result
        return publicMessageDao.get(id);
    }

    public List<IMessage> getAll() {
        logger.debug("Retrieving all personal messages");
        // Run query then return result
        List<IMessage> test = publicMessageDao.getAll();
        return test;
    }

    public Boolean add(IMessage message)  {
        logger.debug("Adding new message");
        return publicMessageDao.add(message);

    }

    public Boolean edit(IMessage message)  {
        logger.debug("Adding new message");
        return publicMessageDao.edit(message);
    }

    public Boolean delete(IMessage message)  {
        logger.debug("Deleting existing message");
        return publicMessageDao.delete(message);
    }

    public PublicMessageDao getPublicMessageDao() {
        return publicMessageDao;
    }

    public void setPublicMessageDao(PublicMessageDao publicMessageDao) {
        this.publicMessageDao = publicMessageDao;
    }
}

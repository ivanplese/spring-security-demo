package org.plese.service;

import org.apache.log4j.Logger;
import org.plese.dao.MessageDao;
import org.plese.model.IMessage;
import org.plese.model.PublicMessage;
import java.util.List;


/**
 * Created by iplese
 * Controls all transaction related to public messages.
 */

public class PublicService implements GenericService{
    Logger logger = Logger.getLogger(PublicMessage.class);


    private MessageDao messageDao;

    public PublicMessage getSingle(Long id) {
        try {
            logger.debug("Retrieving single personal post");
            // Run query then return result
            return messageDao.get(id);

        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public List<IMessage> getAll() {
        logger.debug("Retrieving all personal messages");

        // Run query then return result
        return messageDao.getAll();
    }

    public Boolean add(IMessage message)  {
        try {
            logger.debug("Adding new message");

            return messageDao.add(message);

        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Boolean edit(IMessage message)  {
        try {
            logger.debug("Adding new post");

            return messageDao.edit(message);

        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Boolean delete(IMessage message)  {
        try {
            logger.debug("Deleting existing post");

           return messageDao.delete(message);

        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}

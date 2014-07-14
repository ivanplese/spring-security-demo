package org.plese.service;

import org.apache.log4j.Logger;
import org.plese.DAO.AdminMessageDao;
import org.plese.model.AdminMessage;
import org.plese.model.IMessage;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by iplese
 * Controls all transaction related to public messages.
 */
public class AdminService implements GenericService{
    Logger logger = Logger.getLogger(PublicService.class);

    @Resource(name="adminMessageDao")
    private AdminMessageDao adminMessageDao;

    public AdminMessage getSingle(Long id) {
        logger.debug("Retrieving single admin message");
        // Run query then return result
        return adminMessageDao.get(id);
    }

    public List<IMessage> getAll() {
        logger.debug("Retrieving all admin messages");
        // Run query then return result
        return adminMessageDao.getAll();
    }


    public Boolean add(IMessage message)  {
        logger.debug("Adding new message");
        return adminMessageDao.add(message);

    }

    public Boolean edit(IMessage message)  {
        logger.debug("Adding new message");
        return adminMessageDao.edit(message);
    }

    public Boolean delete(IMessage message)  {
        logger.debug("Deleting existing message");
        return adminMessageDao.delete(message);
    }

    public AdminMessageDao getAdminMessageDao() {
        return adminMessageDao;
    }

    public void setAdminMessageDao(AdminMessageDao adminMessageDao) {
        this.adminMessageDao = adminMessageDao;
    }
}

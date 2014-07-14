package org.plese.service;

import org.apache.log4j.Logger;
import org.plese.DAO.PersonalMessageDao;
import org.plese.model.IMessage;
import org.plese.model.PersonalMessage;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by iplese
 * Controls all transaction related to public messages.
 */
public class PersonalService implements GenericService{
    Logger logger = Logger.getLogger(PublicService.class);

    @Resource(name="personalMessageDao")
    private PersonalMessageDao personalMessageDao;

    @PreAuthorize("hasRole('READ_USER_MESSAGE')")
    public PersonalMessage getSingle(Long id) {
        logger.debug("Retrieving single personal message");
        // Run query then return result
        return personalMessageDao.get(id);
    }

    @PreAuthorize("hasRole('READ_USER_MESSAGE')")
    public List<IMessage> getAll() {
        logger.debug("Retrieving all personal messages");
        // Run query then return result
        return personalMessageDao.getAll();
    }

    @PreAuthorize("hasRole('WRITE_USER_MESSAGE')")
    public Boolean add(IMessage message)  {
        logger.debug("Adding new message");
        return personalMessageDao.add(message);

    }

    @PreAuthorize("hasRole('EDIT_USER_MESSAGE')")
    public Boolean edit(IMessage message)  {
        logger.debug("Adding new message");
        return personalMessageDao.edit(message);
    }

    @PreAuthorize("hasRole('DELETE_USER_MESSAGE')")
    public Boolean delete(IMessage message)  {
        logger.debug("Deleting existing message");
        return personalMessageDao.delete(message);
    }

    public PersonalMessageDao getPersonalMessageDao() {
        return personalMessageDao;
    }

    public void setPersonalMessageDao(PersonalMessageDao personalMessageDao) {
        this.personalMessageDao = personalMessageDao;
    }
}

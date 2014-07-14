package org.plese.DAO;

import org.plese.model.User;

/**
 * Created by ivan on 28/06/14.
 */
public interface IUserDao{

        User findByUserName(String username);

}

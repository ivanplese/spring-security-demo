package org.plese.service;

import org.plese.DAO.UserDao;
import org.plese.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CustomDbAuthoritiesService {

    private UserDao userDao;

    /**
     * Creates user roles retrieved from the DB storage.
     * @param username
     * @return
     */
    public List<GrantedAuthority> getUsersGrantedAuthority(String username){

        org.plese.model.User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return authorities;

    }

    /**
     * Build user's authorities.
     * @param userRoles
     * @return
     */
    final private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        for(UserRole userRole : userRoles){
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return grantedAuthorities;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}

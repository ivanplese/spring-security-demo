package org.plese.service;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import java.util.ArrayList;
import java.util.Collection;


public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    CustomDbAuthoritiesService dbUserDetailService;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations, String s) {

        ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();


        Collection<GrantedAuthority> dbGrantedAuthorities = dbUserDetailService.getUsersGrantedAuthority(s);

        for(GrantedAuthority auth : dbGrantedAuthorities){
            list.add(auth);
        }

        return list;
    }

    public CustomDbAuthoritiesService getDbUserDetailService() {
        return dbUserDetailService;
    }

    public void setDbUserDetailService(CustomDbAuthoritiesService dbUserDetailService) {
        this.dbUserDetailService = dbUserDetailService;
    }
}

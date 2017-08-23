package ru.host.ViewModels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import ru.host.model.DetailUser;
import ru.host.model.Role;
import ru.host.repository.DetailUserRepository;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class IndexViewModel {
    @WireVariable
    private DetailUserRepository detailUserRepository;
    private DetailUser currentUser;
    private final String ROLE_ADMIN = "ROLE_ADMIN";
    @Init
    public void init(){
        try {
            currentUser = (DetailUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException ex){
            currentUser = null;
        }
    }

    public DetailUser getCurrentUser(){
        return currentUser;
    }

    public boolean getAdminFlag(){
        return (currentUser != null && currentUserHasRole(ROLE_ADMIN));
    }

    private boolean currentUserHasRole(String role){
        for (Role r: currentUser.getRoleList()) {
            if (role.equals(r.getRole())) {
                return true;
            }
        }
        return false;
    }
}

package ru.host.ViewModels;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;
import ru.host.model.DetailUser;
import ru.host.repository.DetailUserRepository;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginViewModel {
    @WireVariable
    private DetailUserRepository detailUserRepository;

    private List<DetailUser> userList;

    @Init
    public void init(){
        userList = detailUserRepository.findAll();
    }

    public ListModel<DetailUser> getUsers(){
        return new ListModelList<DetailUser>(userList);
    }

    @Command
    public void closeWindow(@BindingParam("win") Window win){
        win.detach();
    }
}

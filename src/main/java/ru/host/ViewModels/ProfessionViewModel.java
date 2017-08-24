package ru.host.ViewModels;

import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import ru.host.model.Profession;
import ru.host.repository.ProfessionRepository;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProfessionViewModel {
    @WireVariable
    private ProfessionRepository professionRepository;
    private List<Profession> professionList;
    private Profession currentProfession;

    public List<Profession> getProfessionList(){
        if(professionList == null){
            professionList = professionRepository.findAllByOrderByNameAsc();
        }
        return professionList;
    }

    public Profession getCurrentProfession() {
        if(currentProfession == null){
            currentProfession = new Profession();
        }
        return currentProfession;
    }

    @Command
    public void closeWindow(@BindingParam("win") Window win){
        win.detach();
    }

    @Command
    @NotifyChange({"currentProfession", "professionList"})
    public void saveProfession(){
        if(isValidProfession()){
            professionRepository.saveAndFlush(currentProfession);
            currentProfession = new Profession();
            professionList = professionRepository.findAllByOrderByNameAsc();
        } else {
            Messagebox.show("Обязательное поле пустое. Профессия не добавлена!", "warning", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    @Command
    @NotifyChange({"currentProfession"})
    public void editProfession(@BindingParam("profession") Profession profession){
        currentProfession = profession;
    }

    @Command
    @NotifyChange({"professionList"})
    public void deleteProfession(@BindingParam("profession") Profession profession){
        professionRepository.delete(profession);
        professionRepository.flush();
        professionList = professionRepository.findAllByOrderByNameAsc();
    }

    private boolean isValidProfession(){
        return currentProfession != null && !StringUtils.isEmpty(currentProfession.getName());
    }
}

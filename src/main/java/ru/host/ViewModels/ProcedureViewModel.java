package ru.host.ViewModels;

import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import ru.host.model.Procedure;
import ru.host.repository.ProcedureRepository;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProcedureViewModel {
    @WireVariable
    private ProcedureRepository procedureRepository;
    private Procedure currentProcedure;
    private List<Procedure> procedureList;

    public List<Procedure> getProcedureList(){
        if(procedureList == null){
            procedureList = procedureRepository.findAllByOrderByNameAsc();
        }
        return procedureList;
    }

    public Procedure getCurrentProcedure(){
        if(currentProcedure == null){
            currentProcedure = new Procedure();
        }
        return currentProcedure;
    }

    @Command
    @NotifyChange({"currentProcedure", "procedureList"})
    public void saveProcedure(){
        if(validateProcedure()) {
            procedureRepository.save(currentProcedure);
            procedureRepository.flush();
            currentProcedure = null;
            procedureList = procedureRepository.findAllByOrderByNameAsc();
        } else {
            Messagebox.show("Одно из обязательных полей пустое. Процедура не сохранена!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    @Command
    @NotifyChange({"currentProcedure"})
    public void editProcedure(@BindingParam("procedure") Procedure procedure){
        currentProcedure = procedure;
    }

    @Command
    @NotifyChange({"procedureList"})
    public void deleteProcedure(@BindingParam("procedure") Procedure procedure){
        procedureRepository.delete(procedure);
        procedureRepository.flush();
        procedureList = procedureRepository.findAllByOrderByNameAsc();
    }

    @Command
    public void closeWindow(@BindingParam("win") Window win){
        win.detach();
    }

    private boolean validateProcedure(){
        return !StringUtils.isEmpty(currentProcedure.getName()) && !StringUtils.isEmpty(currentProcedure.getPrice());
    }
}

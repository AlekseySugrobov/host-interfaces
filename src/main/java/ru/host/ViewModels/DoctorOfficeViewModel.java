package ru.host.ViewModels;

import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import ru.host.model.DoctorOffice;
import ru.host.model.Profession;
import ru.host.repository.DoctorOfficeRepository;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DoctorOfficeViewModel {
    @WireVariable
    private DoctorOfficeRepository doctorOfficeRepository;
    private List<DoctorOffice> doctorOfficeList;
    private DoctorOffice currentDoctorOffice;

    public DoctorOffice getCurrentDoctorOffice(){
        if(currentDoctorOffice == null){
            currentDoctorOffice = new DoctorOffice();
        }
        return currentDoctorOffice;
    }

    public List<DoctorOffice> getDoctorOfficeList(){
        if(doctorOfficeList == null){
            doctorOfficeList = doctorOfficeRepository.findAllByOrderByNumberAsc();
        }
        return doctorOfficeList;
    }

    @Command
    public void closeWindow(@BindingParam("win") Window win){
        win.detach();
    }

    @Command
    @NotifyChange({"currentDoctorOffice", "doctorOfficeList"})
    public void saveDoctorOffice(){
        if(isValidDoctorOffice()){
            doctorOfficeRepository.saveAndFlush(currentDoctorOffice);
            currentDoctorOffice = new DoctorOffice();
            doctorOfficeList = doctorOfficeRepository.findAllByOrderByNumberAsc();
        } else {
            Messagebox.show("Обязательное поле пустое. Кабинет не добавлен!", "warning", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    @Command
    @NotifyChange({"currentDoctorOffice"})
    public void editDoctorOffice(@BindingParam("doctorOffice") DoctorOffice doctorOffice){
        currentDoctorOffice = doctorOffice;
    }

    @Command
    @NotifyChange({"doctorOfficeList"})
    public void deleteDoctorOffice(@BindingParam("doctorOffice") DoctorOffice doctorOffice){
        doctorOfficeRepository.delete(doctorOffice);
        doctorOfficeRepository.flush();
        doctorOfficeList = doctorOfficeRepository.findAllByOrderByNumberAsc();
    }

    private boolean isValidDoctorOffice(){
        return currentDoctorOffice != null && !StringUtils.isEmpty(currentDoctorOffice.getNumber());
    }
}

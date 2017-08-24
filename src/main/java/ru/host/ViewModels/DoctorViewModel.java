package ru.host.ViewModels;

import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import ru.host.model.Doctor;
import ru.host.model.DoctorOffice;
import ru.host.model.Profession;
import ru.host.repository.DoctorOfficeRepository;
import ru.host.repository.DoctorRepository;
import ru.host.repository.ProfessionRepository;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DoctorViewModel {
    @WireVariable
    private DoctorRepository doctorRepository;
    @WireVariable
    private DoctorOfficeRepository doctorOfficeRepository;
    @WireVariable
    private ProfessionRepository professionRepository;
    private Doctor currentDoctor;
    private List<Doctor> doctorList;
    private List<DoctorOffice> doctorOfficeList;
    private List<Profession> professionList;

    public List<Profession> getProfessionList(){
        if(professionList == null){
            professionList = professionRepository.findAllByOrderByNameAsc();
        }
        return professionList;
    }

    public Doctor getCurrentDoctor(){
        if(currentDoctor == null){
            currentDoctor = new Doctor();
        }
        return currentDoctor;
    }

    public List<Doctor> getDoctorList(){
        if(doctorList == null){
            doctorList = doctorRepository.findAllByOrderByLastNameAsc();
        }
        return doctorList;
    }

    public List<DoctorOffice> getDoctorOfficeList(){
        if(doctorOfficeList == null){
            doctorOfficeList = doctorOfficeRepository.findAllByOrderByNumberAsc();
        }
        return doctorOfficeList;
    }

    @Command
    @NotifyChange({"currentDoctor", "doctorList"})
    public void saveDoctor(){
        if(isValidDoctor()) {
            doctorRepository.saveAndFlush(currentDoctor);
            currentDoctor = new Doctor();
            doctorList = doctorRepository.findAllByOrderByLastNameAsc();
        } else {
            Messagebox.show("Одно из обязательных полей не заполнено. Доктор не сохранен!", "warning", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    @Command
    @NotifyChange({"currentDoctor"})
    public void editDoctor(@BindingParam("doctor") Doctor doctor){
        currentDoctor = doctor;
    }

    @Command
    @NotifyChange({"doctorList"})
    public void deleteDoctor(@BindingParam("doctor") Doctor doctor){
        doctorRepository.delete(doctor);
        doctorList = doctorRepository.findAllByOrderByLastNameAsc();
    }

    @Command
    public void closeWindow(@BindingParam("win") Window win){
        win.detach();
    }

    private boolean isValidDoctor(){
        return currentDoctor != null &&
                currentDoctor.getDoctorOffice() != null &&
                currentDoctor.getProfession() != null &&
                !StringUtils.isEmpty(currentDoctor.getFirstName()) &&
                !StringUtils.isEmpty(currentDoctor.getMiddleName()) &&
                !StringUtils.isEmpty(currentDoctor.getLastName());
    }
}

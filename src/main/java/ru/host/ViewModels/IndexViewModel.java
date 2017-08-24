package ru.host.ViewModels;

import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import ru.host.model.*;
import ru.host.repository.DetailUserRepository;
import ru.host.repository.DoctorRepository;
import ru.host.repository.DoctorVisitRepository;
import ru.host.repository.ProcedureRepository;

import java.time.DayOfWeek;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class IndexViewModel {
    @WireVariable
    private DoctorVisitRepository doctorVisitRepository;
    @WireVariable
    private DoctorRepository doctorRepository;
    @WireVariable
    private ProcedureRepository procedureRepository;

    private List<Doctor> doctorList;

    private List<Procedure> procedureList;

    private List<DoctorVisit> doctorVisitList;

    private DoctorVisit currentDoctorVisit;

    private DetailUser currentUser;

    private final String ROLE_ADMIN = "ROLE_ADMIN";

    private int colSpan;

    public List<Doctor> getDoctorList() {
        if(doctorList == null){
            doctorList = doctorRepository.findAllByOrderByLastNameAsc();
        }
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Procedure> getProcedureList() {
        if(procedureList == null){
            procedureList = procedureRepository.findAllByOrderByNameAsc();
        }
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    public List<DoctorVisit> getDoctorVisitList() {
        if(doctorVisitList == null){
            doctorVisitList = doctorVisitRepository.findAllByOrderByDayOfWeekAscDoctor_LastNameAscDoctor_FirstNameAscProcedure_NameAsc();
        }
        return doctorVisitList;
    }

    public void setDoctorVisitList(List<DoctorVisit> doctorVisitList) {
        this.doctorVisitList = doctorVisitList;
    }

    public int getColSpan(){
        return colSpan;
    }

    public DoctorVisit getCurrentDoctorVisit() {
        if(currentDoctorVisit == null){
            currentDoctorVisit = new DoctorVisit();
        }
        return currentDoctorVisit;
    }

    public void setCurrentDoctorVisit(DoctorVisit currentDoctorVisit) {
        this.currentDoctorVisit = currentDoctorVisit;
    }

    @Init
    public void init(){
        try {
            currentUser = (DetailUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException ex){
            currentUser = null;
        }
        if(getAdminFlag()){
            colSpan = 7;
        } else {
            colSpan = 6;
        }
    }

    public ListModel getDaysOfWeek(){
        return new ListModelList(DayOfWeek.values());
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

    @Command
    @NotifyChange({"currentDoctorVisit", "doctorVisitList"})
    public void saveDoctorVisit(){
        if(isDoctorVisitValid()) {
            doctorVisitRepository.save(currentDoctorVisit);
            currentDoctorVisit = new DoctorVisit();
            doctorVisitList = doctorVisitRepository.findAllByOrderByDayOfWeekAscDoctor_LastNameAscDoctor_FirstNameAscProcedure_NameAsc();
        } else {
            Messagebox.show("Не все обязательные поля заполнены. Прием не сохранен!", "warning", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    private boolean isDoctorVisitValid(){
        return currentDoctorVisit != null &&
                currentDoctorVisit.getDayOfWeek() != null &&
                currentDoctorVisit.getTime() != null &&
                currentDoctorVisit.getDoctor() != null &&
                currentDoctorVisit.getProcedure() != null;
    }

    @Command
    @NotifyChange({"currentDoctorVisit"})
    public void editDoctorVisit(@BindingParam("doctorVisit") DoctorVisit doctorVisit){
        currentDoctorVisit = doctorVisit;
    }

    @Command
    @NotifyChange({"doctorVisitList"})
    public void deleteDoctorVisit(@BindingParam("doctorVisit") DoctorVisit doctorVisit){
        doctorVisitRepository.delete(doctorVisit);
        doctorVisitList = doctorVisitRepository.findAllByOrderByDayOfWeekAscDoctor_LastNameAscDoctor_FirstNameAscProcedure_NameAsc();
    }
}

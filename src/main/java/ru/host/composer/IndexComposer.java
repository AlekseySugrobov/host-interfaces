package ru.host.composer;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

public class IndexComposer extends SelectorComposer {
    @Listen("onClick = #authorizeMenuItem")
    public void showLoginModal(Event event){
        showModal("/views/login.zul");
    }

    @Listen("onClick = #procedureMenuItem")
    public void showProcedureModel(Event event){
        showModal("/views/procedure.zul");
    }

    private void showModal(String viewPath){
        Window window = (Window) Executions.createComponents(viewPath, null, null);
        window.doModal();
    }
}

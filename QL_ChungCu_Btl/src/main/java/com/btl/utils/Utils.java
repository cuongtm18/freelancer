package com.btl.utils;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "danhmucUtils")
@ViewScoped
public class Utils implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Utils.class);

    public static void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public static void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công!", summary);
        FacesContext.getCurrentInstance().addMessage("Thành công!", message);
    }

    public static void errMessage(String errMsg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Thất bại!", errMsg);
        FacesContext.getCurrentInstance().addMessage("Lỗi!", message);
    }

}

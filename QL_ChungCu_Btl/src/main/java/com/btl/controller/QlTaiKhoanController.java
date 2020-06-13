package com.btl.controller;

import com.btl.dao.QlTaiKhoanDAO;
import com.btl.entities.TaiKhoanEntity;
import com.btl.utils.BaseController;
import com.btl.utils.Constants;
import com.btl.utils.ExecuteObjectController;
import com.btl.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "qlTaiKhoanController")
@ViewScoped
@Getter
@Setter
public class QlTaiKhoanController extends BaseController
        implements Serializable, ExecuteObjectController<TaiKhoanEntity> {
    private static final long serialVersionUID = 1L;
    private TaiKhoanEntity taiKhoanEntity;
    private QlTaiKhoanDAO service;
    private List<TaiKhoanEntity> list;
    private int flag;
    private String password;
    private FacesContext facesContext = FacesContext.getCurrentInstance();
    private HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @PostConstruct
    public void init() {
        taiKhoanEntity = new TaiKhoanEntity();
        service = new QlTaiKhoanDAO();
        list = new ArrayList<>();
        checkUserIsLogin();
        onShowData();
    }

    @Override
    public void onShowData() {
        list = service.findAll();
    }

    @Override
    public void onSearch() {
        list = service.onSearchData(taiKhoanEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        taiKhoanEntity = new TaiKhoanEntity();
    }

    @Override
    public void onInsert() {
        taiKhoanEntity.setPassWord(password);
        if (service.insertData(taiKhoanEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void findById(int entity) {
        taiKhoanEntity = service.findById(entity);
        flag = 2;
    }

    @Override
    public void onComfirmUpdate() {
        if (checkCurentUser(taiKhoanEntity.getUserName())) {
            taiKhoanEntity.setPassWord(password);
        }
        if (service.updateData(taiKhoanEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            taiKhoanEntity = new TaiKhoanEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(TaiKhoanEntity entity) {
        if (service.deleteData(entity)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        taiKhoanEntity = new TaiKhoanEntity();
    }

    public boolean checkCurentUser(String userName) {
        String curentUser = (String) session.getAttribute("username");
        return curentUser.equalsIgnoreCase(userName);
    }
}

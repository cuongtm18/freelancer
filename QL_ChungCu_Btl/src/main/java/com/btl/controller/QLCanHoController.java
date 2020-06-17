package com.btl.controller;


import com.btl.dao.QLCanHoDAO;
import com.btl.entities.CanHoEntity;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "qlCanHoController")
@ViewScoped
@Getter
@Setter
public class QLCanHoController extends BaseController implements Serializable, ExecuteObjectController<CanHoEntity> {
    private CanHoEntity canHoEntity;
    private QLCanHoDAO service;
    private List<CanHoEntity> list;
    private int flag;

    @PostConstruct
    public void init() {
        canHoEntity = new CanHoEntity();
        service = new QLCanHoDAO();
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
        list = service.onSearchData(canHoEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        canHoEntity = new CanHoEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(canHoEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(canHoEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            canHoEntity = new CanHoEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(CanHoEntity canHoOnDel) {
        if (service.deleteData(canHoOnDel)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        canHoEntity = new CanHoEntity();
    }

    @Override
    public void findById(int entity) {
        canHoEntity = service.findById(entity);
        flag = 2;
    }
}

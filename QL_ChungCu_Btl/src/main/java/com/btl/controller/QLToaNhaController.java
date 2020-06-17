package com.btl.controller;

import com.btl.dao.QLToaNhaDAO;
import com.btl.entities.ToaNhaEntity;
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

@ManagedBean(name = "qlToaNhaController")
@ViewScoped
@Getter
@Setter
public class QLToaNhaController extends BaseController implements Serializable, ExecuteObjectController<ToaNhaEntity> {
    private ToaNhaEntity toaNhaEntity;
    private QLToaNhaDAO service;
    private List<ToaNhaEntity> list;
    private int flag;

    @PostConstruct
    public void init() {
        toaNhaEntity = new ToaNhaEntity();
        service = new QLToaNhaDAO();
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
        list = service.onSearchData(toaNhaEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        toaNhaEntity = new ToaNhaEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(toaNhaEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(toaNhaEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            toaNhaEntity = new ToaNhaEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(ToaNhaEntity toaNhaEntity) {
        if (service.deleteData(toaNhaEntity)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        toaNhaEntity = new ToaNhaEntity();
    }

    @Override
    public void findById(int entity) {
        toaNhaEntity = service.findById(entity);
        flag = 2;
    }
}

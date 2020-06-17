package com.btl.controller;

import com.btl.dao.QLTinhTrangSuDungDAO;
import com.btl.entities.TinhTrangSuDungCanHoEntity;
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

@ManagedBean(name = "qLTinhTrangToaNhaController")
@ViewScoped
@Getter
@Setter
public class QLTinhTrangToaNhaController extends BaseController implements Serializable, ExecuteObjectController<TinhTrangSuDungCanHoEntity> {
    private TinhTrangSuDungCanHoEntity tinhTrangSuDungCanHoEntity;
    private QLTinhTrangSuDungDAO service;
    private List<TinhTrangSuDungCanHoEntity> list;
    private int flag;

    @PostConstruct
    public void init() {
        tinhTrangSuDungCanHoEntity = new TinhTrangSuDungCanHoEntity();
        service = new QLTinhTrangSuDungDAO();
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
        list = service.onSearchData(tinhTrangSuDungCanHoEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        tinhTrangSuDungCanHoEntity = new TinhTrangSuDungCanHoEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(tinhTrangSuDungCanHoEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(tinhTrangSuDungCanHoEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            tinhTrangSuDungCanHoEntity = new TinhTrangSuDungCanHoEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(TinhTrangSuDungCanHoEntity tinhTrangSuDungCanHoEntity) {
        if (service.deleteData(tinhTrangSuDungCanHoEntity)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        tinhTrangSuDungCanHoEntity = new TinhTrangSuDungCanHoEntity();
    }

    @Override
    public void findById(int entity) {
        tinhTrangSuDungCanHoEntity = service.findById(entity);
        flag = 2;
    }
}

package com.btl.controller;


import com.btl.dao.QLHoaDonDAO;
import com.btl.entities.HoaDonEntity;
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

@ManagedBean(name = "qlHoaDonController")
@ViewScoped
@Getter
@Setter
public class QLHoaDonController extends BaseController implements Serializable, ExecuteObjectController<HoaDonEntity> {
    private HoaDonEntity hoaDonEntity;
    private QLHoaDonDAO service;
    private List<HoaDonEntity> list;
    private int flag;

    @PostConstruct
    public void init() {
        hoaDonEntity = new HoaDonEntity();
        service = new QLHoaDonDAO();
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
        list = service.onSearchData(hoaDonEntity);

    }

    @Override
    public void prepareInsert() {
        flag = 1;
        hoaDonEntity = new HoaDonEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(hoaDonEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(hoaDonEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            hoaDonEntity = new HoaDonEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(HoaDonEntity hoaDonOnDel) {
        if (service.deleteData(hoaDonOnDel)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        hoaDonEntity = new HoaDonEntity();
    }

    @Override
    public void findById(int entity) {
        hoaDonEntity = service.findById(entity);
        flag = 2;
    }
}

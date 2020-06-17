package com.btl.controller;

import com.btl.dao.QLKhachHangDAO;
import com.btl.entities.KhachHangEntity;
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

@ManagedBean(name = "qlKhachHangController")
@ViewScoped
@Getter
@Setter
public class QLKhachHangController extends BaseController implements Serializable, ExecuteObjectController<KhachHangEntity> {
    private KhachHangEntity khachHangEntity;
    private QLKhachHangDAO service;
    private List<KhachHangEntity> list;
    private int flag;

    @PostConstruct
    public void init() {
        khachHangEntity = new KhachHangEntity();
        service = new QLKhachHangDAO();
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
        list = service.onSearchData(khachHangEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        khachHangEntity = new KhachHangEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(khachHangEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(khachHangEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            khachHangEntity = new KhachHangEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(KhachHangEntity khachHangEntity) {
        if (service.deleteData(khachHangEntity)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        khachHangEntity = new KhachHangEntity();
    }

    @Override
    public void findById(int entity) {
        khachHangEntity = service.findById(entity);
        flag = 2;
    }
}

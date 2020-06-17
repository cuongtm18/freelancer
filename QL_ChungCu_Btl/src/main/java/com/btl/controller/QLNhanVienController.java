package com.btl.controller;

import com.btl.dao.QLNhanVienDAO;
import com.btl.entities.BanQuanLyNhanVienEntity;
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

@ManagedBean(name = "qlNhanVienController")
@ViewScoped
@Getter
@Setter
public class QLNhanVienController extends BaseController implements Serializable, ExecuteObjectController<BanQuanLyNhanVienEntity> {
    private BanQuanLyNhanVienEntity banQuanLyNhanVienEntity;
    private QLNhanVienDAO service;
    private List<BanQuanLyNhanVienEntity> list;
    private int flag;


    @PostConstruct
    public void init() {
        banQuanLyNhanVienEntity = new BanQuanLyNhanVienEntity();
        service = new QLNhanVienDAO();
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
        list = service.onSearchData(banQuanLyNhanVienEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        banQuanLyNhanVienEntity = new BanQuanLyNhanVienEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(banQuanLyNhanVienEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(banQuanLyNhanVienEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            banQuanLyNhanVienEntity = new BanQuanLyNhanVienEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(BanQuanLyNhanVienEntity banQuanLyNhanVienOnDel) {
        if (service.deleteData(banQuanLyNhanVienOnDel)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        banQuanLyNhanVienEntity = new BanQuanLyNhanVienEntity();
    }

    @Override
    public void findById(int entity) {
        banQuanLyNhanVienEntity = service.findById(entity);
        flag = 2;
    }
}

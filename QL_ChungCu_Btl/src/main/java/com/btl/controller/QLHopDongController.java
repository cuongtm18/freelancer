package com.btl.controller;

import com.btl.dao.QLHopDongDAO;
import com.btl.entities.HopDongEntity;
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

@ManagedBean(name = "qlHopDongController")
@ViewScoped
@Getter
@Setter
public class QLHopDongController extends BaseController implements Serializable, ExecuteObjectController<HopDongEntity> {
    private HopDongEntity hopDongEntity;
    private QLHopDongDAO service;
    private List<HopDongEntity> list;
    private int flag;

    @PostConstruct
    public void init() {
        hopDongEntity = new HopDongEntity();
        service = new QLHopDongDAO();
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
        list = service.onSearchData(hopDongEntity);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        hopDongEntity = new HopDongEntity();
    }

    @Override
    public void onInsert() {
        if (service.insertData(hopDongEntity)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(hopDongEntity)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            hopDongEntity = new HopDongEntity();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(HopDongEntity hopDongOnDel) {
        if (service.deleteData(hopDongOnDel)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        hopDongEntity = new HopDongEntity();
    }

    @Override
    public void findById(int entity) {
        hopDongEntity = service.findById(entity);
        flag = 2;
    }
}

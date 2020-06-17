package com.btl.controller;

import com.btl.dao.QLTheoDoiCongNoKhDAO;
import com.btl.entities.TheoDoiCongNoKh;
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

@ManagedBean(name = "qlTheoDoiCongNoKhController")
@ViewScoped
@Getter
@Setter
public class QLTheoDoiCongNoKhController extends BaseController implements Serializable, ExecuteObjectController<TheoDoiCongNoKh> {
    private TheoDoiCongNoKh theoDoiCongNoKh;
    private QLTheoDoiCongNoKhDAO service;
    private List<TheoDoiCongNoKh> list;
    private int flag;

    @PostConstruct
    public void init() {
        theoDoiCongNoKh = new TheoDoiCongNoKh();
        service = new QLTheoDoiCongNoKhDAO();
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
        list = service.onSearchData(theoDoiCongNoKh);
    }

    @Override
    public void prepareInsert() {
        flag = 1;
        theoDoiCongNoKh = new TheoDoiCongNoKh();
    }

    @Override
    public void onInsert() {
        if (service.insertData(theoDoiCongNoKh)) {
            Utils.addMessage(Constants.INS_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
        } else {
            Utils.errMessage(Constants.INS_FAIL);
        }
    }

    @Override
    public void onComfirmUpdate() {
        if (service.updateData(theoDoiCongNoKh)) {
            Utils.addMessage(Constants.UPD_SUCCESS);
            onShowData();
            PrimeFaces.current().executeScript("PF('dlgAddEdit').hide()");
            theoDoiCongNoKh = new TheoDoiCongNoKh();
        } else {
            Utils.errMessage(Constants.UPD_FAIL);
        }
    }

    @Override
    public void onDelete(TheoDoiCongNoKh theodoiOnDel) {
        if (service.deleteData(theodoiOnDel)) {
            Utils.addMessage(Constants.DEL_SUCCESS);
            onShowData();
        } else {
            Utils.errMessage(Constants.DEL_FAIL);
        }
    }

    @Override
    public void resetDialogForm() {
        onShowData();
        theoDoiCongNoKh = new TheoDoiCongNoKh();
    }

    @Override
    public void findById(int entity) {
        theoDoiCongNoKh = service.findById(entity);
        flag = 2;
    }
}

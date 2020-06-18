package com.btl.layout;

import com.btl.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@ViewScoped
@ManagedBean(name = "masterController")
@Getter
@Setter
public class MasterLayoutController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MasterLayoutController.class);
    private static final String rootPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    private MenuModel mainMenuModel;
    private DefaultMenuItem currentMenu;
    private static Map<Long, Object> hsdnSearchMap = new HashedMap<>();
    private int activeTabIndex;
    private String curMenu = "";
    private String priMenu = "";
    private boolean isShow;
    public HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
            .getSession(true);

    @PostConstruct
    public void init() {
    }

    public MasterLayoutController() throws IOException {
        if (mainMenuModel == null) {
            mainMenuModel = genMainMenu();
        }

        getCurrentMenu();
    }

    private MenuModel genMainMenu() throws IOException {
        DynamicMenuModel mainMenuModel = new DynamicMenuModel();
        // Phân quyền menu cho admin và nhân viên
        Integer role = (Integer) httpSession.getAttribute("loai");
        addMenu(mainMenuModel, Constants.HOME, "/adminIndex.xhtml", "fa fa-fw fa-home");
        if (role != null && role == 1) {
            addMenu(mainMenuModel, Constants.QL_TK, "/admin/qlTaiKhoan/ql-tai-khoan.xhtml", "fa fa-address-book-o");
            addMenu(mainMenuModel, Constants.BAN_QL_NV, "/admin/qlBanQlNhanVien/ql-ban-ql-nv.xhtml", "fa fa-id-badge");
            addMenu(mainMenuModel, Constants.QL_TOA_NHA, "/admin/qlToaNha/ql-toa-nha.xhtml", "fa fa-university");
            addMenu(mainMenuModel, Constants.QL_CAN_HO, "/admin/qlCanHo/ql-can-ho.xhtml", "fa fa-archive");
            addMenu(mainMenuModel, Constants.TINHTRANG_SDCH, "/admin/qlTinhTrangSdch/ql-tinh-trang-sdch.xhtml", "fa fa-building-o");
            addMenu(mainMenuModel, Constants.QL_HOP_DONG, "/admin/qlHopDong/ql-hop-dong.xhtml", "fa fa-file-archive-o");
            addMenu(mainMenuModel, Constants.QL_HOA_DON, "/admin/qlHoaDon/ql-hoa-don.xhtml", "fa fa-money");
            addMenu(mainMenuModel, Constants.QL_KHACH_HANG, "/admin/qlKhachHang/ql-khach-hang.xhtml", "fa fa-user-circle-o");
            addMenu(mainMenuModel, Constants.THEODOI_CONGNO_KHACH_HANG, "/admin/qlTheoDoiCnkh/ql-theo-doi-cong-no.xhtml", "fa fa-newspaper-o");
        } else if (role != null && role == 2) {
            addMenu(mainMenuModel, Constants.QL_TOA_NHA, "/admin/qlToaNha/ql-toa-nha.xhtml", "fa fa-university");
            addMenu(mainMenuModel, Constants.QL_CAN_HO, "/admin/qlCanHo/ql-can-ho.xhtml", "fa fa-archive");
            addMenu(mainMenuModel, Constants.QL_HOP_DONG, "/admin/qlHopDong/ql-hop-dong.xhtml", "fa fa-file-archive-o");
        } else if (role != null && role == 3) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(rootPath + "/admin/qlCuDan/ql-cu-dan.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(rootPath + "/login.xhtml");
        }
        return mainMenuModel;
    }

    public MenuModel getMainMenuModel() {
        return mainMenuModel;
    }

    public void setMainMenuModel(MenuModel mainMenuModel) {
        this.mainMenuModel = mainMenuModel;
    }

    public DefaultMenuItem getCurrentMenu() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();

        if (!priMenu.equals(path)) {
            activeTabIndex = 0;
            priMenu = path;
        }

        List<MenuElement> menuItems = mainMenuModel.getElements();
        for (MenuElement me : menuItems) {
            DefaultMenuItem dmi = (DefaultMenuItem) me;
            if (path.equals(dmi.getUrl().split("\\?")[0])) {
                dmi.setContainerStyleClass("current-menu");
                dmi.setStyleClass("current-menu");
                currentMenu = dmi;
            } else {
                dmi.setStyleClass("uncurrent-menu");
            }
        }
        return currentMenu;
    }

    private void addMenu(DynamicMenuModel mainMenuModel, String menuName, String url, String icon) {
        ResourceBundle resources = ResourceBundle.getBundle(Constants.RESOURCE_FILENAME,
                new Locale(Constants.LANGUAGE_VI, Constants.COUNTRY_VI));
        mainMenuModel.addElement(new DefaultMenuItem(resources.getString(menuName), StringUtils.isEmpty(icon) ? "" : icon,
                rootPath + url));
    }
}

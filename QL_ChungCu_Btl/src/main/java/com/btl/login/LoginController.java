package com.btl.login;

import com.btl.entities.TaiKhoanEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.Constants;
import com.btl.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "loginController")
@SessionScoped
@Getter
@Setter
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private String username;
    private String password;
    private String imgLoading = "";
    private TaiKhoanEntity taiKhoanEntity;
    private List<TaiKhoanEntity> listUser;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    private int activeTabIndex;

    @PostConstruct
    public void init() {

    }

    public TaiKhoanEntity checkExisted() {
        try {
            Session session = sessionFactory.openSession();
            String SQL = String.format("SELECT * FROM taikhoan WHERE TenTaiKhoan = '%s' AND MatKhau = '%s'", username, password);
            Query query = session.createNativeQuery(SQL, TaiKhoanEntity.class);
            return (TaiKhoanEntity) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public LoginController() {
        String rootPath = "/";
        imgLoading = rootPath + "resources/img/default.svg";
    }

    public void doLogin() {
        try {
            taiKhoanEntity = checkExisted();
            if (taiKhoanEntity != null) {
                if (taiKhoanEntity.getStatus() == 1) {
                    session.setAttribute("fullName", taiKhoanEntity.getFullName());
                    session.setAttribute("loai", taiKhoanEntity.getRole());
                    session.setAttribute("username", taiKhoanEntity.getUserName());
                    facesContext.getExternalContext().redirect("adminIndex.xhtml");
                } else {
                    Utils.errMessage(Constants.NOT_ACTIVE);
                }
            } else {
                Utils.errMessage(Constants.AUTH_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("Lỗi : ", e);
        }
    }

    public void onTabChange() {
        Map<String, String> paramMap = facesContext.getExternalContext().getRequestParameterMap();
        String paramIndex = paramMap.get("activeIndex");
        setActiveTabIndex(Integer.valueOf(paramIndex));
    }

    public void doLogout() {
        try {
            facesContext.getExternalContext().invalidateSession();
            String rootPath = facesContext.getExternalContext().getRequestContextPath() + "/";
            facesContext.getExternalContext().redirect(rootPath + "login.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
    }

    public String getUserName() {
        String username = "";
        username = (String) session.getAttribute("username");
        return username;
    }

    public String getFullName() {
        String fullName = "";
        fullName = (String) session.getAttribute("fullName");
        return fullName;
    }

    public String getRoleType() {
        int role;
        role = (int) session.getAttribute("loai");
        if (role == 1) {
            return "SuperAdmin";
        } else if (role == 2) {
            return "Admin";
        } else {
            return "User";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

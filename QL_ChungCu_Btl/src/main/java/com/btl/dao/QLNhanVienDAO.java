package com.btl.dao;

import com.btl.entities.BanQuanLyNhanVienEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.List;

public class QLNhanVienDAO extends HibernateUtil implements ExecuteObjectDAO<BanQuanLyNhanVienEntity> {

    @Override
    public List<BanQuanLyNhanVienEntity> findAll() {
        String sql = "SELECT tk FROM BanQuanLyNhanVienEntity tk ORDER BY tk.id DESC";
        return getSession().createQuery(sql, BanQuanLyNhanVienEntity.class).getResultList();

    }

    public BanQuanLyNhanVienEntity findById(int input) {
        return getSession().get(BanQuanLyNhanVienEntity.class, input);
    }

    @Override
    public List<BanQuanLyNhanVienEntity> onSearchData(BanQuanLyNhanVienEntity entityNhanVien) {
        String hql = "SELECT * FROM banquanlynhanvien WHERE 1=1";
        if (StringUtils.isNotEmpty(entityNhanVien.getTenNhanVien().trim())) {
            hql += " AND TenNv LIKE N'%" + entityNhanVien.getTenNhanVien() + "%'";
        }
        if (StringUtils.isNotEmpty(entityNhanVien.getPhone().trim())) {
            hql += " AND Sodienthoai LIKE '%" + entityNhanVien.getPhone() + "%'";
        }

        return getSession().createNativeQuery(hql, BanQuanLyNhanVienEntity.class).getResultList();

    }

    @Override
    public boolean insertData(BanQuanLyNhanVienEntity nhanVienIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(nhanVienIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(BanQuanLyNhanVienEntity nhanVienUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(nhanVienUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(BanQuanLyNhanVienEntity nhanVienDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(nhanVienDel);
        session.getTransaction().commit();
        return true;
    }
}

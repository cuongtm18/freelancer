package com.btl.dao;

import com.btl.entities.TaiKhoanEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.hibernate.Session;

import java.util.List;

public class QlTaiKhoanDAO extends HibernateUtil implements ExecuteObjectDAO<TaiKhoanEntity> {

    @Override
    public List<TaiKhoanEntity> findAll() {
        String sql = "SELECT tk FROM TaiKhoanEntity tk ORDER BY tk.id DESC";
        return getSession().createQuery(sql, TaiKhoanEntity.class).getResultList();
    }

    public TaiKhoanEntity findById(int input) {
        return getSession().get(TaiKhoanEntity.class, input);
    }

    @Override
    public List<TaiKhoanEntity> onSearchData(TaiKhoanEntity userIn) {
        String hql = "SELECT * FROM taikhoan WHERE 1=1";
        if (!userIn.getFullName().trim().equals("")) {
            hql += " AND HoVaTen LIKE N'%" + userIn.getFullName() + "%'";
        }
        if (!userIn.getUserName().trim().equals("")) {
            hql += " AND TenTaiKhoan LIKE N'%" + userIn.getUserName() + "%'";
        }
        if (userIn.getRole() != null) {
            hql += " AND VaiTro = " + userIn.getRole();
        }
        if (!userIn.getEmail().trim().equals("")) {
            hql += " AND Email LIKE N'%" + userIn.getEmail() + "%'";
        }
        if (!userIn.getPhone().trim().equals("")) {
            hql += " AND SDT LIKE '%" + userIn.getPhone() + "%'";
        }
        if (userIn.getStatus() != null) {
            hql += " AND TrangThai = " + userIn.getStatus();
        }
        return getSession().createNativeQuery(hql, TaiKhoanEntity.class).getResultList();
    }

    @Override
    public boolean insertData(TaiKhoanEntity userIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(userIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(TaiKhoanEntity userDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(userDel);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(TaiKhoanEntity userUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(userUpdate);
        session.getTransaction().commit();
        return true;
    }

}

package com.btl.dao;

import com.btl.entities.KhachHangEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.List;

public class QLKhachHangDAO extends HibernateUtil implements ExecuteObjectDAO<KhachHangEntity> {

    @Override
    public List<KhachHangEntity> findAll() {
        String sql = "SELECT tk FROM KhachHangEntity tk ORDER BY tk.id DESC";
        return getSession().createQuery(sql, KhachHangEntity.class).getResultList();
    }

    public KhachHangEntity findById(int input) {
        return getSession().get(KhachHangEntity.class, input);
    }

    @Override
    public List<KhachHangEntity> onSearchData(KhachHangEntity khachHangEntity) {
        String hql = "SELECT * FROM khachhang WHERE 1=1";
        if (StringUtils.isNotEmpty(khachHangEntity.getTenKhachHang())) {
            hql += " AND TenKh LIKE N'%" + khachHangEntity.getTenKhachHang() + "%'";
        }
        if (StringUtils.isNotEmpty(khachHangEntity.getDiaChi())) {
            hql += " AND Diachi LIKE N'%" + khachHangEntity.getDiaChi() + "%'";
        }
        if (StringUtils.isNotEmpty(khachHangEntity.getPhone())) {
            hql += " AND Sodienthoai LIKE N'%" + khachHangEntity.getPhone() + "%'";
        }
        if (StringUtils.isNotEmpty(khachHangEntity.getSoCMTND())) {
            hql += " AND SoCmtnd LIKE N'%" + khachHangEntity.getSoCMTND() + "%'";
        }


        return getSession().createNativeQuery(hql, KhachHangEntity.class).getResultList();

    }

    @Override
    public boolean insertData(KhachHangEntity khachHangIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(khachHangIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(KhachHangEntity khachHangUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(khachHangUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(KhachHangEntity khachHangDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(khachHangDel);
        session.getTransaction().commit();
        return true;
    }
}

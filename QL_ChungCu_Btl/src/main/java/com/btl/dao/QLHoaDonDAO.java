package com.btl.dao;

import com.btl.entities.HoaDonEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.hibernate.Session;

import java.util.List;

public class QLHoaDonDAO extends HibernateUtil implements ExecuteObjectDAO<HoaDonEntity> {

    @Override
    public List<HoaDonEntity> findAll() {
        String sql = "SELECT tk FROM HoaDonEntity tk ORDER BY tk.id DESC";
        return getSession().createQuery(sql, HoaDonEntity.class).getResultList();
    }

    public HoaDonEntity findById(int input) {
        return getSession().get(HoaDonEntity.class, input);
    }

    @Override
    public List<HoaDonEntity> onSearchData(HoaDonEntity hoaDonEntity) {
        String hql = "SELECT * FROM hoadon WHERE 1=1";
        if (hoaDonEntity.getMaGd() != null) {
            hql += " AND MaGd =" + hoaDonEntity.getMaGd();
        }
        return getSession().createNativeQuery(hql, HoaDonEntity.class).getResultList();
    }

    @Override
    public boolean insertData(HoaDonEntity hoaDonIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(hoaDonIns);
        session.getTransaction().commit();
        return true;

    }

    @Override
    public boolean updateData(HoaDonEntity hoaDonUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(hoaDonUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(HoaDonEntity hoaDonDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(hoaDonDel);
        session.getTransaction().commit();
        return true;
    }
}

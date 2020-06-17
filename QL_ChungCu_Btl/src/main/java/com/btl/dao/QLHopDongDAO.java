package com.btl.dao;

import com.btl.entities.HopDongEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.hibernate.Session;

import java.util.List;

public class QLHopDongDAO extends HibernateUtil implements ExecuteObjectDAO<HopDongEntity> {


    @Override
    public List<HopDongEntity> findAll() {
        String sql = "SELECT tk FROM HopDongEntity tk ORDER BY tk.id DESC";
        return getSession().createQuery(sql, HopDongEntity.class).getResultList();
    }

    public HopDongEntity findById(int input) {
        return getSession().get(HopDongEntity.class, input);
    }

    @Override
    public List<HopDongEntity> onSearchData(HopDongEntity hopDongEntity) {
        String hql = "SELECT * FROM hopdong WHERE 1=1";
        if (hopDongEntity.getMaHd() != null) {
            hql += " AND MaHd =" + hopDongEntity.getMaHd();
        }
        return getSession().createNativeQuery(hql, HopDongEntity.class).getResultList();
    }

    @Override
    public boolean insertData(HopDongEntity hopDonIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(hopDonIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(HopDongEntity hopDongUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(hopDongUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(HopDongEntity hopDongDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(hopDongDel);
        session.getTransaction().commit();
        return true;
    }
}

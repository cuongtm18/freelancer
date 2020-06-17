package com.btl.dao;


import com.btl.entities.CanHoEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.hibernate.Session;

import java.util.List;

public class QLCanHoDAO extends HibernateUtil implements ExecuteObjectDAO<CanHoEntity> {


    @Override
    public List<CanHoEntity> findAll() {
        String sql = "SELECT tk FROM CanHoEntity tk ORDER BY tk.maCh DESC";
        return getSession().createQuery(sql, CanHoEntity.class).getResultList();

    }

    public CanHoEntity findById(int input) {
        return getSession().get(CanHoEntity.class, input);
    }

    @Override
    public List<CanHoEntity> onSearchData(CanHoEntity canHoEntity) {
        String hql = "SELECT * FROM canho WHERE 1=1";
        if (!canHoEntity.getTenCanHo().trim().equals("")) {
            hql += " AND TenCh LIKE N'%" + canHoEntity.getTenCanHo() + "%'";
        }
        if (canHoEntity.getSoTang() != null) {
            hql += " AND Sotang =" + canHoEntity.getSoTang();
        }
        if (canHoEntity.getSoPhong() != null) {
            hql += " AND Sophong =" + canHoEntity.getSoPhong();
        }
        return getSession().createNativeQuery(hql, CanHoEntity.class).getResultList();
    }

    @Override
    public boolean insertData(CanHoEntity canHoIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(canHoIns);
        session.getTransaction().commit();
        return true;

    }

    @Override
    public boolean updateData(CanHoEntity canHoUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(canHoUpdate);
        session.getTransaction().commit();
        return true;

    }

    @Override
    public boolean deleteData(CanHoEntity canhoDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(canhoDel);
        session.getTransaction().commit();
        return true;
    }
}

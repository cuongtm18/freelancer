package com.btl.dao;

import com.btl.entities.TinhTrangSuDungCanHoEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.hibernate.Session;

import java.util.List;

public class QLTinhTrangSuDungDAO extends HibernateUtil implements ExecuteObjectDAO<TinhTrangSuDungCanHoEntity> {

    @Override
    public List<TinhTrangSuDungCanHoEntity> findAll() {
        String sql = "SELECT tk FROM TinhTrangSuDungCanHoEntity tk ORDER BY tk.maCh DESC";
        return getSession().createQuery(sql, TinhTrangSuDungCanHoEntity.class).getResultList();
    }

    public TinhTrangSuDungCanHoEntity findById(int input) {
        return getSession().get(TinhTrangSuDungCanHoEntity.class, input);
    }

    @Override
    public List<TinhTrangSuDungCanHoEntity> onSearchData(TinhTrangSuDungCanHoEntity tinhTrangSuDungCanHoEntity) {
        String hql = "SELECT * FROM tinhtrangsdcanho WHERE 1=1";
        if (tinhTrangSuDungCanHoEntity.getTinhTrang() != null) {
            hql += " AND Tinhtrang =" + tinhTrangSuDungCanHoEntity.getTinhTrang();
        }


        return getSession().createNativeQuery(hql, TinhTrangSuDungCanHoEntity.class).getResultList();
    }

    @Override
    public boolean insertData(TinhTrangSuDungCanHoEntity tinhTrangSuDungCanHoEntityIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(tinhTrangSuDungCanHoEntityIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(TinhTrangSuDungCanHoEntity tinhTrangSuDungCanHoEntityUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(tinhTrangSuDungCanHoEntityUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(TinhTrangSuDungCanHoEntity tinhTrangSuDungCanHoEntityDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(tinhTrangSuDungCanHoEntityDel);
        session.getTransaction().commit();
        return true;
    }
}

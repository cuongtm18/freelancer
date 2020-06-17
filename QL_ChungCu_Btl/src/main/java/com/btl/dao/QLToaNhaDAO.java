package com.btl.dao;

import com.btl.entities.ToaNhaEntity;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.List;

public class QLToaNhaDAO extends HibernateUtil implements ExecuteObjectDAO<ToaNhaEntity> {

    @Override
    public List<ToaNhaEntity> findAll() {
        String sql = "SELECT tk FROM ToaNhaEntity tk ORDER BY tk.maTn DESC";
        return getSession().createQuery(sql, ToaNhaEntity.class).getResultList();
    }

    public ToaNhaEntity findById(int input) {
        return getSession().get(ToaNhaEntity.class, input);
    }

    @Override
    public List<ToaNhaEntity> onSearchData(ToaNhaEntity toaNhaEntity) {
        String hql = "SELECT * FROM toanha WHERE 1=1";
        if (toaNhaEntity.getMaTn() != null) {
            hql += " AND MaTn LIKE N'%" + toaNhaEntity.getMaTn() + "%'";
        }
        if (StringUtils.isNotEmpty(toaNhaEntity.getTenTn().trim())) {
            hql += " AND TenTn LIKE N'%" + toaNhaEntity.getTenTn() + "%'";
        }
        return getSession().createNativeQuery(hql, ToaNhaEntity.class).getResultList();
    }

    @Override
    public boolean insertData(ToaNhaEntity toaNhaIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(toaNhaIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(ToaNhaEntity toaNhaUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(toaNhaUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(ToaNhaEntity toaNhaDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(toaNhaDel);
        session.getTransaction().commit();
        return true;
    }
}

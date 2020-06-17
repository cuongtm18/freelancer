package com.btl.dao;

import com.btl.entities.TheoDoiCongNoKh;
import com.btl.models.HibernateUtil;
import com.btl.utils.ExecuteObjectDAO;
import org.hibernate.Session;

import java.util.List;

public class QLTheoDoiCongNoKhDAO extends HibernateUtil implements ExecuteObjectDAO<TheoDoiCongNoKh> {

    @Override
    public List<TheoDoiCongNoKh> findAll() {
        String sql = "SELECT tdcn FROM TheoDoiCongNoKh tdcn ORDER BY tdcn.maKh DESC";
        return getSession().createQuery(sql, TheoDoiCongNoKh.class).getResultList();
    }

    public TheoDoiCongNoKh findById(int input) {
        return getSession().get(TheoDoiCongNoKh.class, input);
    }

    @Override
    public List<TheoDoiCongNoKh> onSearchData(TheoDoiCongNoKh theoDoiCongNoKh) {
        String hql = "SELECT * FROM theodoicongnokh WHERE 1=1";
        if (theoDoiCongNoKh.getMaKh() != null) {
            hql += " AND MaKh =" + theoDoiCongNoKh.getMaKh();
        }
        return getSession().createNativeQuery(hql, TheoDoiCongNoKh.class).getResultList();

    }

    @Override
    public boolean insertData(TheoDoiCongNoKh theoDoiCongNoKhIns) {
        Session session = getSession();
        session.beginTransaction();
        session.save(theoDoiCongNoKhIns);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateData(TheoDoiCongNoKh theoDoiCongNoKhUpdate) {
        Session session = getSession();
        session.beginTransaction();
        session.update(theoDoiCongNoKhUpdate);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteData(TheoDoiCongNoKh theoDoiCongNoKhDel) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(theoDoiCongNoKhDel);
        session.getTransaction().commit();
        return true;
    }
}

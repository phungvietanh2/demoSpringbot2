package com.example.booking.Dao.Iml;

import com.example.booking.Dao.RoomsImgDao;
import com.example.booking.entity.RoomsImg;
import com.example.booking.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomsImgDaoImpl implements RoomsImgDao {
    @Override
    public boolean saveorUpdate(RoomsImg roomsImg) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(roomsImg);

            transaction.commit();
        }
        return true;
    }

    @Override
    public List<RoomsImg> findall(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from RoomsImg r join r.rooms rm where rm.rooms_id = :id";
            Query<RoomsImg> query = session.createQuery(hql, RoomsImg.class);
            query.setParameter("id", id);
            return query.list();
        }
    }
}

package com.example.booking.Dao.Iml;

import com.example.booking.Dao.RoomsUserDao;
import com.example.booking.entity.RoomsUser;
import com.example.booking.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomsUserDaoImpl implements RoomsUserDao {
    @Override
    public boolean saveOrUpdate(RoomsUser roomsUser) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(roomsUser);

            transaction.commit();
        }
        return true;
    }

    @Override
    public List<RoomsUser> findByID(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from RoomsUser r join r.users u where u.id= :id";
            Query<RoomsUser> query = session.createQuery(hql, RoomsUser.class);
            query.setParameter("id", id);
            return query.list();
        }
    }

    @Override
    public List<RoomsUser> findByUserID(Long roomid) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from RoomsUser r join r.rooms u where r.users.id= :id order by r.rooms_date_created desc ";
            Query<RoomsUser> query = session.createQuery(hql, RoomsUser.class);
            query.setParameter("id", roomid);
            return query.list();
        }
    }


}

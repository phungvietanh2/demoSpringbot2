package com.example.booking.Dao.Iml;

import com.example.booking.Dao.UserDao;
import com.example.booking.entity.HistoryUser;
import com.example.booking.entity.User;
import com.example.booking.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean saveOrUpdate(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        }
        return  true;
    }

    @Override
    public List<User> findByCode(String code) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from User where users_code = :code";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("code", code);
            return query.list();
        }
    }

    @Override
    public List<HistoryUser> findById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from HistoryUser h join h.roomsuser r where r.id = :id order by h.rooms_date_created desc ";
            Query<HistoryUser> query = session.createQuery(hql, HistoryUser.class);
            query.setParameter("id", id);
            return query.list();
        }
    }
}

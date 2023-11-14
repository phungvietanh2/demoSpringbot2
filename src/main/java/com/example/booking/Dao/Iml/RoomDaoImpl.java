package com.example.booking.Dao.Iml;

import com.example.booking.Dao.RoomDao;
import com.example.booking.entity.Rooms;
import com.example.booking.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public boolean saveorUpdate(Rooms rooms) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(rooms);

            transaction.commit();
        }
        return true;
    }

    @Override
    public List<Rooms> findAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Rooms";
            Query query = session.createQuery(hql,Rooms.class);
            return  query.list();
        }

    }

    @Override
    public List<Rooms> findById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Rooms r join r.roomsimg rm where r.rooms_id = :id";
            Query<Rooms> query = session.createQuery(hql, Rooms.class);
            query.setParameter("id", id);
            return query.list();
        }
    }

    @Override
    public List<Rooms> findByUserId(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Rooms r join r.users rm where rm.id = :id";
            Query<Rooms> query = session.createQuery(hql, Rooms.class);
            query.setParameter("id", id);
            return query.list();
        }
    }

    @Override
    public List<Rooms> findByRoomUser(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Rooms r join r.roomsUsers rm join rm.users u where u.id = :id";
            Query<Rooms> query = session.createQuery(hql, Rooms.class);
            query.setParameter("id", id);
            return query.list();
        }
    }

}

package com.example.booking.Dao.Iml;

import com.example.booking.Dao.RoleDao;
import com.example.booking.entity.Role;
import com.example.booking.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDaoImpl implements RoleDao {
    @Override
    public boolean saveOrUpdate(Role role) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(role);
            transaction.commit();
        }
        return  true;
    }
}

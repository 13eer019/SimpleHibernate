package com.example.gokul.test;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("userDao")
public class UserDaoImp implements UserDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<User> getUser() {
        Session session = sessionFactory.getCurrentSession();
                List<User>  list = session.createCriteria(User.class).list();
        return list;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User findById(int id) {
        Session session = sessionFactory.getCurrentSession();
                User user = session.get(User.class,id);
        /*Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("id", id));
        User user = (User) cr.uniqueResult();
        System.out.println("1");*/
        return user;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User update(User user, int id) {
        Session session = sessionFactory.getCurrentSession();
                User userdata = session.get(User.class,id);
        userdata.setCountry(user.getCountry());
        userdata.setName(user.getName());
        session.update(userdata);
        return userdata;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User updateCountry(User val, int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.load(User.class, id);
        user.setCountry(val.getCountry());
        return user;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class,id);
        session.delete(user);
    }
}

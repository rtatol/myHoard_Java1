package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private SecurityService securityService;

    @Override
    public UserDS getByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDS.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.setMaxResults(1);

        return (UserDS) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UserDS> getList() throws MyHoardException {
        return sessionFactory.getCurrentSession().createCriteria(UserDS.class).list();
    }

    @Override
    public UserDS get(int id) {
        return (UserDS) sessionFactory.getCurrentSession().get(UserDS.class, id);
    }

    @Override
    public void create(UserDS object) {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(UserDS object) {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public void remove(int id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<CollectionDS> getListOfUserCollections(int id) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CollectionDS.class);
		if(Integer.parseInt(securityService.getCurrentUser().getId()) == id) {
			criteria.add(Restrictions.eq("owner.id", id));
		}
		else {
			criteria.add(Restrictions.eq("owner.id", id))
					.add(Restrictions.eq("isPublic", true));
		}
		return criteria.list();
	}

}

package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.ItemDS;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    public List<ItemDS> getListByUser(int id) {
        return sessionFactory.getCurrentSession().createCriteria(ItemDS.class)
                .add(Restrictions.eq("owner.id", id)).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<ItemDS> getList() {
        return sessionFactory.getCurrentSession().createCriteria(ItemDS.class).list();
    }

    @Override
    public ItemDS get(int id) {
        return (ItemDS) sessionFactory.getCurrentSession().get(ItemDS.class, id);
    }

    @Override
    public void create(ItemDS object) {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(ItemDS object) {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public void remove(int id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDS> getList(String name, int collection, String owner) {
		
		
		return sessionFactory.getCurrentSession().createCriteria(ItemDS.class)
				.add(Restrictions.disjunction()
						.add(Restrictions.ilike("name", name))
						.add(Restrictions.ilike("description", "%" + name + "%"))
					)
				.add(Restrictions.eq("collection.id", collection))
				.list();
	}

}
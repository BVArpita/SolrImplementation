package com.dao;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.bean.Entity;
import com.bean.UserEntity;

public class BackEndDao implements IGenericBackEndDao<Entity> {
	
	
	//private Transaction transaction=null;
	private SessionFactory sessionFactory = null;
	private Session session = null;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserEntity login(UserEntity user, Class<UserEntity> clazz)
			throws Exception {

		UserEntity aUser = null;
		try {

			session = sessionFactory.getCurrentSession();
			// transaction = session.getTransaction();
			// session.beginTransaction();
			aUser = (UserEntity) session
					.createCriteria(clazz)
					.add(Restrictions.and(
							Restrictions.eq("email", user.getEmail()),
							Restrictions.eq("password", user.getPassword())))
					.uniqueResult();
		} finally {
			
			if (aUser != null)
				return aUser;
		}
		return null;
	}
	@Override
	public UserEntity getEntityByID(UserEntity entity, Class<UserEntity> clazz)
			throws Exception {

		UserEntity aUser = null;
		try {

			session = sessionFactory.getCurrentSession();
			// transaction = session.getTransaction();
			// session.beginTransaction();
			aUser = (UserEntity) session
					.createCriteria(clazz)
					.add(Restrictions.eq("id", entity.getId())).uniqueResult();
		} finally {
			
			if (aUser != null)
				return aUser;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> getEntityList(Class<? extends Entity> clazz)
			throws HibernateException, Exception {
	
		List<Entity> entities = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(clazz);
			entities = criteria.list();
		} catch (Exception e) {

			throw e;
		}
		return entities;
	}

	@Override
	public boolean addEntity(Entity entity) throws Exception {
		try {
			session = sessionFactory.getCurrentSession();
			//transaction = session.getTransaction();
			session.beginTransaction();
			session.save(entity);
			//transaction.commit();

			return true;
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {

		}
	}

	@Override
	public boolean deleteEntity(long id, Class<? extends Entity> clazz)
			throws Exception {
		try {

			session = sessionFactory.getCurrentSession();

			Object o = session.load(clazz, new Long(id));
			session.delete(o);
			return true;
		} catch (Exception e) {

			throw e;
		} finally {

		}
	}

	@Override
	public Entity getEntity(long id, Class<? extends Entity> clazz)
			throws Exception {
		try {

			session = sessionFactory.getCurrentSession();

			Object o = session.get(clazz, new Long(id));

			return (Entity) o;
		} catch (Exception e) {
			// TODO: handle exception

			throw e;
			// return null;
		} finally {

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> getEntityList(long id, String restrictionStr,
			Class<? extends Entity> clazz) throws Exception {
		List<Entity> entities = null;
		try {

			session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(clazz);
			criteria.add(Restrictions.eq(restrictionStr, id));
			entities = criteria.list();

		} catch (Exception e) {

			throw e;
		} finally {

		}
		return entities;
	}

	@Override
	public boolean updateEntity(Class<? extends Entity> clazz, Entity entity,
			long id) throws Exception {
		try {
			session = sessionFactory.getCurrentSession();
			Entity entity1 = (Entity) session.load(clazz, id);
			BeanUtils.copyProperties(entity1, entity);
			session.update(entity1);
			return true;
		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

}

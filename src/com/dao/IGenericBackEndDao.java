package com.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.bean.Entity;
import com.bean.UserEntity;
/**
 * @author Vinay
 *
 * @param <T>
 */
public interface IGenericBackEndDao<T extends Entity> extends
		IGenericUserBackEndDao<UserEntity> {
	/**
	 * @param entity
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addEntity(T entity) throws Exception;
	
	/**
	 * @param entity
	 * @return boolean
	 * @throws Exception
	 */
	
	public boolean updateEntity(Class<? extends Entity> clazz,Entity entity,long id) throws Exception;

	/**
	 * @param id
	 * @param clazz
	 * @return T
	 * @throws Exception
	 */
	public T getEntity(long id, Class<? extends Entity> clazz) throws Exception;
	/**
	 * @param id
	 * @param clazz
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteEntity(long id, Class<? extends Entity > clazz) throws Exception;

	/**
	 * @param clazz
	 * @return List<T>
	 * @throws HibernateException
	 * @throws Exception
	 */
	
	
	public List<T> getEntityList(Class<? extends Entity> clazz) throws HibernateException,
			Exception;
	/**
	 * @param id
	 * @param restrictionStr
	 * @param clazz
	 * @return List<T>
	 * @throws Exception
	 */
	public List<T> getEntityList(long id,String restrictionStr,Class<? extends Entity > clazz) throws Exception;
	
	
	/**
	 * @param user
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public UserEntity getEntityByID(UserEntity user, Class<UserEntity> clazz) throws Exception;

}

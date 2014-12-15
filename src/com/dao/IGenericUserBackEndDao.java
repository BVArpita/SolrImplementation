package com.dao;

import com.bean.Entity;

public interface IGenericUserBackEndDao<T extends Entity> {
	/**
	 * @param user
	 * @param clazz
	 * @return T
	 * @throws Exception
	 */
	public T login(T user,Class<T> clazz) throws Exception;
	

}

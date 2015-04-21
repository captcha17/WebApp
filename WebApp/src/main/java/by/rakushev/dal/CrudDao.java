package by.rakushev.dal;

import java.io.Serializable;
import java.util.List;

import by.rakushev.exception.DalException;

/**
 * CRUD interface
 * 
 * @author mihm
 */
public interface CrudDao {

	/**
	 * Creates/updates a new object for the given type.
	 * 
	 * @param <T>
	 *            Entity class
	 * @param t
	 *            entity
	 * @return persisted Object
	 */
	<T> T merge(T t) throws DalException;
	<T> void saveOrUpdate(T t) throws DalException;

	/**
	 * Delete object by id
	 * 
	 * @param <T>
	 * @param <PK>
	 * @param type
	 *            , entity class type
	 * @param id
	 */
	<T, PK extends Serializable> void delete(Class<T> type,String str)
			throws DalException;

	/**
	 * Find object by id
	 * 
	 * @param <T>
	 * @param <PK>
	 * @param type
	 * @param id
	 * @return the object
	 */
	<T, PK extends Serializable> T find(Class<T> type, PK id)
			throws DalException;

	/**
	 * List of objects
	 * 
	 * @param type
	 * @param <T>
	 * @return
	 */
	<T> List<T> list(Class<T> type) throws DalException;
//	<T> List<T> list(Class<T> type, String str) throws DalException;
//	<T> List<T> getGroup(Class<T> type, String prop) throws DalException;
	<T> List<T> getGroup(Class<T> type, Long prop) throws DalException;
//	<T> T getProfile(Class<T> type, String prop) throws DalException;
	<T> T check(Class<T> type, Long id) throws DalException;
//	<T> List<T> checkList(Class<T> type, Long id) throws DalException;
}

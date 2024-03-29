package com.sci.bpm.dao.mi;

import java.util.List;

import com.sci.bpm.db.model.SciPurchaseMast;

/**
 * Interface for SciPurchaseMastDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISciPurchaseMastDAO {
	/**
	 * Perform an initial save of a previously unsaved SciPurchaseMast entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISciPurchaseMastDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SciPurchaseMast entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SciPurchaseMast entity);

	/**
	 * Delete a persistent SciPurchaseMast entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISciPurchaseMastDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SciPurchaseMast entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SciPurchaseMast entity);

	/**
	 * Persist a previously saved SciPurchaseMast entity and return it or a copy
	 * of it to the sender. A copy of the SciPurchaseMast entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISciPurchaseMastDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SciPurchaseMast entity to update
	 * @returns SciPurchaseMast the persisted SciPurchaseMast entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SciPurchaseMast update(SciPurchaseMast entity);

	public SciPurchaseMast findById(Long id);

	/**
	 * Find all SciPurchaseMast entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SciPurchaseMast property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SciPurchaseMast> found by query
	 */
	public List<SciPurchaseMast> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all SciPurchaseMast entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SciPurchaseMast> all SciPurchaseMast entities
	 */
	public List<SciPurchaseMast> findAll(int... rowStartIdxAndCount);
}
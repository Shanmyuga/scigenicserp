package com.sci.bpm.dao.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sci.bpm.db.model.SciUserStateMasterEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.sci.bpm.db.model.ScigenicsRoleMaster;
import com.sci.bpm.db.model.ScigenicsUserMaster;

@Repository
public class UserMasterDAO implements IScigenicsUserMasterDAO {

	
	@PersistenceContext
	private EntityManager em;
	
	public void delete(ScigenicsUserMaster entity) {
		em.remove(entity);

	}

	public List<ScigenicsUserMaster> findAll(int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public ScigenicsUserMaster findById(Long id) {
		// TODO Auto-generated method stub
		return em.find(ScigenicsUserMaster.class,id);
	}

	public List<ScigenicsUserMaster> findByPassword(Object password,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ScigenicsUserMaster> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ScigenicsUserMaster> findByUserFirstname(Object userFirstname,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ScigenicsUserMaster> findByUserId(Object userId,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserStates(List<SciUserStateMasterEntity> userStateMasterEntityList) {

		for(SciUserStateMasterEntity userStateMasterEntity1: userStateMasterEntityList) {
			em.persist(userStateMasterEntity1);
		}
		return true;
	}

	public List<ScigenicsUserMaster> findByUserLastname(Object userLastname,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(ScigenicsUserMaster entity) {
		
		Set<ScigenicsRoleMaster> rolemaster = entity.getScigenicsRoleMasters();
		Set<ScigenicsRoleMaster> refreshedSet = new HashSet<ScigenicsRoleMaster>();
		for(ScigenicsRoleMaster rm : rolemaster) {
		
			refreshedSet.add(em.find(ScigenicsRoleMaster.class, rm.getSeqRoleId()));
		}
		entity.setScigenicsRoleMasters(refreshedSet);
		em.persist(entity);

	}
	public void update(ScigenicsUserMaster entity) {

		 em.merge(entity);
	}

	public List selectUserList() {
		// TODO Auto-generated method stub
		List<ScigenicsUserMaster> users = em.createQuery("from ScigenicsUserMaster  um where um.userStatus = 'Y' ").getResultList();
		for(ScigenicsUserMaster sm:users) {
			sm.getScigenicsRoleMasters();
		}
		return users;
	}

	@Override
	public List<ScigenicsUserMaster> selectUserList(String role) {
		// TODO Auto-generated method stub
		System.out.println(role);
		List<ScigenicsUserMaster> users = em.createQuery("from ScigenicsUserMaster  um where um.userStatus = 'Y' ").getResultList();
		List<ScigenicsUserMaster> filteredUsers = new ArrayList<ScigenicsUserMaster>();
		for(ScigenicsUserMaster sm:users) {
			System.out.println(sm.getUserId());
			for(ScigenicsRoleMaster rm : sm.getScigenicsRoleMasters()) {
				if(rm.getRoleName() !=null && rm.getRoleName().equals(role)) {
					filteredUsers.add(sm);
					break;
				}
			}
			/*boolean isExist = sm.getScigenicsRoleMasters().stream().anyMatch(element -> element.getRoleName().contains(role));
			if(isExist) {
				filteredUsers.add(sm);
			}*/

		}
		return filteredUsers;
	}


	@Override
	public void deleteUserStates(Long seqUserId) {
		Query qry = em.createNativeQuery("Delete from SCI_USER_STATE_MASTER where SEQ_USER_ID=:seqUserId");
		qry.setParameter("seqUserId",seqUserId);
		qry.executeUpdate();
	}

	@Override
	public ScigenicsRoleMaster selectRole(Long roleId) {
		return em.find(ScigenicsRoleMaster.class,roleId);
	}

	@Override
	public List<SciUserStateMasterEntity> getUserStates(Long seqUserId) {
		Query qry = em.createQuery("from SciUserStateMasterEntity where seqUserId=:seqUserId");
		qry.setParameter("seqUserId",seqUserId);
		return qry.getResultList();
	}

	public ScigenicsUserMaster findUser(String userID) {
		// TODO Auto-generated method stub
		Query qp = em.createQuery("from ScigenicsUserMaster  um where um.userId =:userID");
		qp.setParameter("userID", userID);
		List mylist = qp.getResultList();
		if(mylist.size() > 0) {
			return (ScigenicsUserMaster)mylist.get(0);
		}
		return null;
	}

}

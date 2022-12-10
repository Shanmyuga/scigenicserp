package com.sci.bpm.service.user;

import java.util.List;

import com.sci.bpm.db.model.SciUserStateMasterEntity;
import com.sci.bpm.db.model.ScigenicsRoleMaster;
import com.sci.bpm.db.model.ScigenicsUserMaster;

public interface UserService {

	
	public boolean addUser(ScigenicsUserMaster user);
	
	public boolean updateUser(ScigenicsUserMaster user);

	public boolean updateUserStates(List<SciUserStateMasterEntity> userStateMasterEntity,Long seqUserId);

	public ScigenicsUserMaster findById(Long id);

	public ScigenicsRoleMaster generateRole(Long roleID);
	
	public ScigenicsUserMaster findUser(String userID);

	public List<SciUserStateMasterEntity> getUserStates(Long seqUserId);
	
	public boolean updateRoleForUser(int roleid[],String seqUserID);
	
	public List selectUserList();
	public List<ScigenicsUserMaster> selectUserList(String role);
	
	
	
	
}

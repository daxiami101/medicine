package cn.com.taiji.sample.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.User;

public interface UserRepo extends AbstractJpaRepo<User, String>
{
	@Query("select count(id) from User where role.id=?1")
	public long count(String roleId);

	@Query("from User where unit.id=?1")
	public List<User> listByUnit(String unitId);
	
	public User findByLoginName(String loginName);
	
	public User findByMobile(String mobile);
}

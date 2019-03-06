package cn.com.taiji.sample.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.Role;

public interface RoleRepo extends AbstractJpaRepo<Role, String>
{
	@Query("from Role where unit.id=?1")
	public List<Role> listByUnit(String unitId);
	
	public Role findFirstByOrderByNameAsc();
}

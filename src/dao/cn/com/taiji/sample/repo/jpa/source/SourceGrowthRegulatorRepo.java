package cn.com.taiji.sample.repo.jpa.source;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.source.land.SourceGrowthRegulator;

public interface SourceGrowthRegulatorRepo extends AbstractJpaRepo<SourceGrowthRegulator, String>
{
	@Query(" from SourceGrowthRegulator where 1=1 and taskId=?1")
	public List<SourceGrowthRegulator> listByTaskId(String taskId);
}

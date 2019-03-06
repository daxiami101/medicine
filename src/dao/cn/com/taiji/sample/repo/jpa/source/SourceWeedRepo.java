package cn.com.taiji.sample.repo.jpa.source;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.source.land.SourceWeed;

public interface SourceWeedRepo extends AbstractJpaRepo<SourceWeed, String>
{
	@Query(" from SourceWeed where 1=1 and taskId=?1")
	public List<SourceWeed> listByTaskId(String taskId);
}

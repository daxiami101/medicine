package cn.com.taiji.sample.repo.jpa.source;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.source.land.SourceDiseaseCtrl;

public interface SourceDiseaseCtrlRepo extends AbstractJpaRepo<SourceDiseaseCtrl, String>
{
	@Query(" from SourceDiseaseCtrl where 1=1 and taskId=?1")
	public List<SourceDiseaseCtrl> listByTaskId(String taskId);
}

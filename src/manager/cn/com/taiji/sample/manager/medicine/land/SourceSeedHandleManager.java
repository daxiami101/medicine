package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.repo.request.source.SourceSeedHandlePageRequest;

/**
 * 种子种苗处理
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceSeedHandleManager
{
	public Pagination queryPage(SourceSeedHandlePageRequest req);

	public void createTask(SourceSeedHandle req, HttpServletRequest request);

	public SourceSeedHandle findById(String id);

	public void update(SourcePlantTask user);

	public void delete(String id);
	
	public List<SourceSeedHandle> listByTaskId(String taskid);


}

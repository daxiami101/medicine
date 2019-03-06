package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.entity.source.land.SourceWeed;
import cn.com.taiji.sample.repo.request.source.SourceWeedPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceWeedManager
{
	public Pagination queryPage(SourceWeedPageRequest req);

	public void delete(String id);

	public void createTask(SourceWeed req, HttpServletRequest request);

	public SourceWeed findById(String id);

	public List<SourceWeed> listByTaskId(String plantTaskId);


}

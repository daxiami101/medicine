package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourceOther;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.repo.request.source.SourceOtherPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceOtherManager
{
	public Pagination queryPage(SourceOtherPageRequest req);

	public void delete(String id);

	public SourceOther findById(String id);

	public void createTask(SourceOther req, HttpServletRequest request);

	public List<SourceOther> listByTaskId(String plantTaskId);


}

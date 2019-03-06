package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceProduction;
import cn.com.taiji.sample.repo.request.source.SourceProductionPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceProductionManager
{
	public Pagination queryPage(SourceProductionPageRequest req);

	public void createTask(SourceProduction req, HttpServletRequest request);

	public SourceProduction findById(String id);


}

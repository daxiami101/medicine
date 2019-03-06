package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceSoldOut;
import cn.com.taiji.sample.repo.request.source.SourceSoldOutPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceSoldOutManager
{
	public Pagination queryPage(SourceSoldOutPageRequest req);

	public void delete(String id);

	public SourceSoldOut findById(String id);

	public void createTask(SourceSoldOut req, HttpServletRequest request);


}

package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceCheck;
import cn.com.taiji.sample.repo.request.source.SourceCheckPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceCheckManager
{
	public Pagination queryPage(SourceCheckPageRequest req);

	public void delete(String id);

	public Object findById(String id);

	public void createTask(SourceCheck req, HttpServletRequest request);


}

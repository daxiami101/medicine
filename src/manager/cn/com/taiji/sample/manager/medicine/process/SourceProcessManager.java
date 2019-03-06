package cn.com.taiji.sample.manager.medicine.process;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceProcess;
import cn.com.taiji.sample.repo.request.source.SourceProcessPageRequest;

/**
 * 加工管理
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceProcessManager
{
	public Pagination queryPage(SourceProcessPageRequest req);

	public void createTask(SourceProcess req, HttpServletRequest request);

	public SourceProcess findById(String id);
}

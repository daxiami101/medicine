package cn.com.taiji.sample.manager.medicine.process;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceProcessPackage;
import cn.com.taiji.sample.repo.request.source.SourceProcessPackagePageRequest;

/**
 * 加工管理--包装
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceProcessPackageManager
{
	public Pagination queryPage(SourceProcessPackagePageRequest req);

	public void createTask(SourceProcessPackage req, HttpServletRequest request);

	public SourceProcessPackage findById(String id);


}

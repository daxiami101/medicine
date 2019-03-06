package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceWaitPackage;
import cn.com.taiji.sample.repo.request.source.SourceWaitPackagePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceWaitPackageManager
{
	public Pagination queryPage(SourceWaitPackagePageRequest req);

	public void createTask(SourceWaitPackage req, HttpServletRequest request);

	public SourceWaitPackage findById(String id);


}

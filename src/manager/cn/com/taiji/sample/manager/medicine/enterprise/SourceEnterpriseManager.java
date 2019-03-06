package cn.com.taiji.sample.manager.medicine.enterprise;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceEnterprise;
import cn.com.taiji.sample.repo.request.source.SourceEnterprisePageRequest;

/**
 * 经营者信息
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceEnterpriseManager
{
	public Pagination queryPage(SourceEnterprisePageRequest req);

	public void delete(String id);

	public SourceEnterprise findById(String id);

	public void createTask(SourceEnterprise req, HttpServletRequest request);


}

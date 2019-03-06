package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceSold;
import cn.com.taiji.sample.repo.request.source.SourceSoldPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceSoldManager
{
	public String add(User user, User loginUser) throws JsonManagerException;

	public Pagination queryPage(SourceSoldPageRequest req);

	public void delete(String id);

	public SourceSold findById(String id);

	public void createTask(SourceSold req, HttpServletRequest request);


}

package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceHarvest;
import cn.com.taiji.sample.repo.request.source.SourceHarvestPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceHarvestManager
{
	public String add(User user, User loginUser) throws JsonManagerException;

	public Pagination queryPage(SourceHarvestPageRequest req);

	public void delete(String id);

	public SourceHarvest findById(String id);

	public void createTask(SourceHarvest req, HttpServletRequest request);


}

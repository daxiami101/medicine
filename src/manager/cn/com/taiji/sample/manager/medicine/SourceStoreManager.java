package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceStore;
import cn.com.taiji.sample.repo.request.source.SourceStorePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceStoreManager
{
	public String add(User user, User loginUser) throws JsonManagerException;

	public Pagination queryPage(SourceStorePageRequest req);

	public void delete(String id);

	public SourceStore findById(String id);

	public void createTask(SourceStore req, HttpServletRequest request);


}

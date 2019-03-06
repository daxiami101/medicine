package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.repo.request.source.SourceTransportPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceTransportManager
{
	public String add(User user, User loginUser) throws JsonManagerException;

	public Pagination queryPage(SourceTransportPageRequest req);

	public void delete(String id);

	public SourceTransport findById(String id);

	public void createTask(SourceTransport req, HttpServletRequest request);


}

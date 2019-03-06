package cn.com.taiji.sample.manager.medicine;

import javax.validation.Valid;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceEnvironment;
import cn.com.taiji.sample.repo.request.source.SourceEnvironmentPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceEnviromentManager
{
	public String add(User user, User loginUser) throws JsonManagerException;

	public Pagination queryPage(SourceEnvironmentPageRequest req);

	public void save(@Valid SourceEnvironment pageModel);

	public void delete(String id);

	public SourceEnvironment findById(String id);

	public void update(@Valid SourceEnvironment user);


}

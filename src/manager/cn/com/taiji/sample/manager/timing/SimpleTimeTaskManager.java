package cn.com.taiji.sample.manager.timing;

import java.util.Map;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.SimpleTimeTask;
import cn.com.taiji.sample.repo.request.timing.SimpleTimeTaskPageRequest;

public interface SimpleTimeTaskManager {

	public Pagination queryPage(SimpleTimeTaskPageRequest req);
	
	public SimpleTimeTask add(SimpleTimeTask model) throws JsonManagerException;
	
	public SimpleTimeTask updateTime(SimpleTimeTask model) throws JsonManagerException;
	
	public void delete(String id)throws JsonManagerException;
	
	public SimpleTimeTask findById(String id);

	Map<String, SimpleTimeTaskRunner> getRunners();


}

package cn.com.taiji.sample.manager.medicine;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceEnvironment;
import cn.com.taiji.sample.repo.jpa.source.SourceEnvironmentRepo;
import cn.com.taiji.sample.repo.request.source.SourceEnvironmentPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceEnvironmentManager")
public class SourceEnvironmentManagerImpl extends AbstractManager implements SourceEnviromentManager{
	@Autowired
	private SourceEnvironmentRepo repo;

	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryPage(SourceEnvironmentPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}

	@Override
	public void save(@Valid SourceEnvironment model) {
		repo.save(model);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public SourceEnvironment findById(String id) {
		SourceEnvironment sourceEnvironment = repo.findById(id).get();
		System.out.println("find======="+sourceEnvironment);
		return sourceEnvironment;
	}

	@Override
	public void update(@Valid SourceEnvironment source) {
		String id = source.getId();
		System.out.println("id:"+id);
		SourceEnvironment model = repo.findById(id).get();
		BeanUtils.copyProperties(source, model);
		repo.save(model);
	}
}

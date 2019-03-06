package cn.com.taiji.sample.manager.medicine;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceSeed;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceSeedPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceSeedManager")
public class SourceSeedManagerImpl extends AbstractManager implements SourceSeedManager{
	@Autowired
	private SourceSeedRepo repo;

	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryPage(SourceSeedPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}

	@Override
	public void save(@Valid SourceSeed model) {
		repo.save(model);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public SourceSeed findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public void update(@Valid SourceSeed source) {
		String id = source.getId();
		SourceSeed model = repo.findById(id).get();
		BeanUtils.copyProperties(source, model);
		System.out.println("model:"+model);
		repo.save(model);
	}

	
}

package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceProduction;
import cn.com.taiji.sample.repo.jpa.source.SourceProductionRepo;
import cn.com.taiji.sample.repo.request.source.SourceProductionPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceProduction")
public class SourceProductionManagerImpl extends AbstractManager implements SourceProductionManager{
	@Autowired
	private SourceProductionRepo repo;


	@Override
	public Pagination queryPage(SourceProductionPageRequest req) {
		return repo.page(req);
	}


	@Override
	public void createTask(SourceProduction req, HttpServletRequest request) {
		repo.save(req);
	}


	@Override
	public SourceProduction findById(String id) {
		return repo.findById(id).get();
	}

	
}

package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceSoldOut;
import cn.com.taiji.sample.repo.jpa.source.SourceSoldOutRepo;
import cn.com.taiji.sample.repo.request.source.SourceSoldOutPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 */
@Service("sourceSoldOut")
public class SourceSoldOutManagerImpl extends AbstractManager implements SourceSoldOutManager{
	@Autowired
	private SourceSoldOutRepo repo;


	@Override
	public Pagination queryPage(SourceSoldOutPageRequest req) {
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public SourceSoldOut findById(String id) {
		return repo.findById(id).get();
	}


	@Override
	public void createTask(SourceSoldOut req, HttpServletRequest request) {
		repo.save(req);
	}

	
}

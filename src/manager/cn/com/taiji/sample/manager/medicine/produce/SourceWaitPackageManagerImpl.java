package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceWaitPackage;
import cn.com.taiji.sample.repo.jpa.source.SourceWaitPackageRepo;
import cn.com.taiji.sample.repo.request.source.SourceWaitPackagePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceWaitPackage")
public class SourceWaitPackageManagerImpl extends AbstractManager implements SourceWaitPackageManager{
	@Autowired
	private SourceWaitPackageRepo repo;


	@Override
	public Pagination queryPage(SourceWaitPackagePageRequest req) {
		return repo.page(req);
	}


	@Override
	public void createTask(SourceWaitPackage req, HttpServletRequest request) {
		repo.save(req);
	}


	@Override
	public SourceWaitPackage findById(String id) {
		return repo.findById(id).get();
	}

	
}

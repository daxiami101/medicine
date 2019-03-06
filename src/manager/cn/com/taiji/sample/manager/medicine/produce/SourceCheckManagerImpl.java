package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceCheck;
import cn.com.taiji.sample.repo.jpa.source.SourceCheckRepo;
import cn.com.taiji.sample.repo.request.source.SourceCheckPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceCheck")
public class SourceCheckManagerImpl extends AbstractManager implements SourceCheckManager{
	@Autowired
	private SourceCheckRepo repo;


	@Override
	public Pagination queryPage(SourceCheckPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}


	@Override
	public Object findById(String id) {
		// TODO Auto-generated method stub
		return repo.getOne(id);
	}


	@Override
	public void createTask(SourceCheck req, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	
}

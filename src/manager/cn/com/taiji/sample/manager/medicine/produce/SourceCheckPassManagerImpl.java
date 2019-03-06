package cn.com.taiji.sample.manager.medicine.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.repo.jpa.source.SourceCheckPassRepo;
import cn.com.taiji.sample.repo.request.source.SourceCheckPassPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceCheckPass")
public class SourceCheckPassManagerImpl extends AbstractManager implements SourceCheckPassManager{
	@Autowired
	private SourceCheckPassRepo repo;


	@Override
	public Pagination queryPage(SourceCheckPassPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public Object findById(String id) {
		// TODO Auto-generated method stub
		return repo.getOne(id);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	
}

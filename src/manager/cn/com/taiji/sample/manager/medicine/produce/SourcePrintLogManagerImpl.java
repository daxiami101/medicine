package cn.com.taiji.sample.manager.medicine.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.repo.jpa.source.SourcePrintLogRepo;
import cn.com.taiji.sample.repo.request.source.SourcePrintLogPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourcePrintLog")
public class SourcePrintLogManagerImpl extends AbstractManager implements SourcePrintLogManager{
	@Autowired
	private SourcePrintLogRepo repo;


	@Override
	public Pagination queryPage(SourcePrintLogPageRequest req) {
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

	
}

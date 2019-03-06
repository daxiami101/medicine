package cn.com.taiji.sample.manager.medicine.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessRepo;
import cn.com.taiji.sample.repo.request.source.SourceProcessPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceProcessCheck")
public class SourceProcessCheckManagerImpl extends AbstractManager implements SourceProcessCheckManager{
	@Autowired
	private SourceProcessRepo repo;


	@Override
	public Pagination queryPage(SourceProcessPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}

	
}

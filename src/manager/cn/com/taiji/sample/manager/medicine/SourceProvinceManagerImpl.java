package cn.com.taiji.sample.manager.medicine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceProvince;
import cn.com.taiji.sample.repo.jpa.source.SourceProvinceRepo;
import cn.com.taiji.sample.repo.request.source.SourceProvincePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceProvinceManager")
public class SourceProvinceManagerImpl extends AbstractManager implements SourceProvinceManager{
	@Autowired
	private SourceProvinceRepo repo;


	@Override
	public Pagination queryPage(SourceProvincePageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}


	@Override
	public List<SourceProvince> listAll() {
		
		return repo.findAll();
	}
}

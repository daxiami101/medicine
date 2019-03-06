package cn.com.taiji.sample.manager.medicine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceCounty;
import cn.com.taiji.sample.repo.jpa.source.SourceCountyRepo;
import cn.com.taiji.sample.repo.request.source.SourceCountyPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceCountyManager")
public class SourceCountyManagerImpl extends AbstractManager implements SourceCountyManager{
	@Autowired
	private SourceCountyRepo repo;


	@Override
	public Pagination queryPage(SourceCountyPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}


	@Override
	public List<SourceCounty> listByProvinceId(String key) {
		return repo.listByCityId(key);
	}
}

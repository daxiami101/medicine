package cn.com.taiji.sample.manager.medicine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceCity;
import cn.com.taiji.sample.repo.jpa.source.SourceCityRepo;
import cn.com.taiji.sample.repo.request.source.SourceCityPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceCityManager")
public class SourceCityManagerImpl extends AbstractManager implements SourceCityManager{
	@Autowired
	private SourceCityRepo repo;


	@Override
	public Pagination queryPage(SourceCityPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}


	@Override
	public List<SourceCity> listByProvinceId(String provinceId) {
		return repo.listByProvinceId(provinceId);
	}
}

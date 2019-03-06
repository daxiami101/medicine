package cn.com.taiji.sample.manager.medicine;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceFarmer;
import cn.com.taiji.sample.repo.jpa.source.SourceCityRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceCountyRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceProvinceRepo;
import cn.com.taiji.sample.repo.request.source.SourceFarmerPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceFarmerManager")
public class SourceFarmerManagerImpl extends AbstractManager implements SourceFarmerManager
{
	@Autowired
	private SourceFarmerRepo repo;
	@Autowired
	private SourceProvinceRepo provinceRepo;
	@Autowired
	private SourceCityRepo cityRepo;
	@Autowired
	private SourceCountyRepo countyRepo;
//	@Autowired
//	private CommParamConfig paramConfig;

	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryPage(SourceFarmerPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}

	@Transactional
	@Override
	public void save(@Valid SourceFarmer pageModel) {
		repo.save(pageModel);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public SourceFarmer findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Transactional
	@Override
	public void update(@Valid SourceFarmer sourceFarmer) {
		String id = sourceFarmer.getId();
		SourceFarmer model = repo.findById(id).get();
		System.out.println("sourceFarmer:"+sourceFarmer);
		System.out.println("sourceFarmer:"+sourceFarmer.getFarmerType());
		BeanUtils.copyProperties(sourceFarmer, model);
		System.out.println("sourceFarmer:"+sourceFarmer);
		repo.save(model);
	}

	
}

package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceSold;
import cn.com.taiji.sample.entity.source.SourceStore;
import cn.com.taiji.sample.repo.jpa.source.SourceSoldRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceStoreRepo;
import cn.com.taiji.sample.repo.request.source.SourceSoldPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceSold")
public class SourceSoldManagerImpl extends AbstractManager implements SourceSoldManager{
	@Autowired
	private SourceSoldRepo repo;
	@Autowired
	private SourceStoreRepo storeRepo;

	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryPage(SourceSoldPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public SourceSold findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public void createTask(SourceSold req, HttpServletRequest request) {
		String storeId = req.getStoreId();
		SourceSold model=new SourceSold();
		BeanUtils.copyProperties(req, model);
		SourceStore sourceStore = storeRepo.findById(storeId).get();
		model.setPlantTaskId(sourceStore.getPlantTaskId());
		model.setHarvestId(sourceStore.getHarvestId());
		model.setSeedId(sourceStore.getSeedId());
		model.setFarmerId(sourceStore.getFarmerId());
		model.setProcessId(sourceStore.getProcessId());
		model.setProcessPackageId(sourceStore.getProcessPackageId());
		repo.save(model);
	}
}

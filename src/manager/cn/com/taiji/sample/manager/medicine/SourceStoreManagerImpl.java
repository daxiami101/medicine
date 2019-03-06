package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceProcessPackage;
import cn.com.taiji.sample.entity.source.SourceStore;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessPackageRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceStoreRepo;
import cn.com.taiji.sample.repo.request.source.SourceStorePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceStoreManager")
public class SourceStoreManagerImpl extends AbstractManager implements SourceStoreManager{
	@Autowired
	private SourceStoreRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;
	@Autowired
	private SourceProcessPackageRepo processPackageRepo;
	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryPage(SourceStorePageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}
	@Override
	public SourceStore findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public void createTask(SourceStore req, HttpServletRequest request) {
		String processPackageId = req.getProcessPackageId();
		SourceProcessPackage sourceProcessPackage = processPackageRepo.findById(processPackageId).get();
		SourceStore model=new SourceStore();
		BeanUtils.copyProperties(req, model);
		model.setProcessId(sourceProcessPackage.getProcessId());
		model.setPlantTaskId(sourceProcessPackage.getPlantTaskId());
		model.setSeedId(sourceProcessPackage.getSeedId());
		model.setFarmerId(sourceProcessPackage.getFarmerId());
		model.setHarvestId(sourceProcessPackage.getHarvestId());
		repo.save(model);
	}
}

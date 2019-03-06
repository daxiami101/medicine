package cn.com.taiji.sample.manager.medicine.process;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceProcess;
import cn.com.taiji.sample.entity.source.SourceProcessPackage;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceHarvestRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessPackageRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceProcessPackagePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceProcessPackage")
public class SourceProcessPackageManagerImpl extends AbstractManager implements SourceProcessPackageManager{
	@Autowired
	private SourceProcessPackageRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;
	@Autowired
	private SourceHarvestRepo harvestRepo;
	@Autowired
	private SourceProcessRepo processRepo;
	

	@Override
	public Pagination queryPage(SourceProcessPackagePageRequest req) {
		return repo.page(req);
	}


	@Override
	public void createTask(SourceProcessPackage req, HttpServletRequest request) {
		String processId = req.getProcessId();
		SourceProcess sourceProcess = processRepo.findById(processId).get();
		req.setPlantTaskId(sourceProcess.getPlantTaskId());
		req.setFarmerId(sourceProcess.getFarmerId());
		req.setSeedId(sourceProcess.getSeedId());
		req.setHarvestId(sourceProcess.getHarvestId());
		repo.save(req);
	}

	@Override
	public SourceProcessPackage findById(String id) {
		return repo.findById(id).get();
	}

	
}

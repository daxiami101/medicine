package cn.com.taiji.sample.manager.medicine.process;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceHarvest;
import cn.com.taiji.sample.entity.source.SourceProcess;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceHarvestRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceProcessPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceProcess")
public class SourceProcessManagerImpl extends AbstractManager implements SourceProcessManager{
	@Autowired
	private SourceProcessRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;
	@Autowired
	private SourceHarvestRepo harvestRepo;

	@Override
	public Pagination queryPage(SourceProcessPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public void createTask(SourceProcess req, HttpServletRequest request) {
		String harvestId = req.getHarvestId();
		SourceHarvest sourceHarvest = harvestRepo.findById(harvestId).get();
		SourceProcess model=new SourceProcess();
		BeanUtils.copyProperties(req, model);
		model.setPlantTaskId(sourceHarvest.getPlantTaskId());
		model.setFarmerId(sourceHarvest.getFarmerId());
		model.setSeedId(sourceHarvest.getSeedId());
		model.setMedicineName(sourceHarvest.getMedicineName());
		repo.save(model);
	}


	@Override
	public SourceProcess findById(String id) {
		return repo.findById(id).get();
	}

	
}

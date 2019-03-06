package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceGrowthRegulator;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceGrowthRegulatorRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceGrowthRegulatorPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceGrowthRegulator")
public class SourceGrowthRegulatorManagerImpl extends AbstractManager implements SourceGrowthRegulatorManager{
	@Autowired
	private SourceGrowthRegulatorRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;

	@Override
	public Pagination queryPage(SourceGrowthRegulatorPageRequest req) {
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public SourceGrowthRegulator findById(String id) {
		return repo.findById(id).get();
	}


	@Override
	public void createTask(SourceGrowthRegulator req, HttpServletRequest request) {
		String taskId = req.getTaskId();
		SourcePlantTask sourcePlantTask = plantTaskRepo.findById(taskId).get();
		SourceGrowthRegulator model=new SourceGrowthRegulator();
		BeanUtils.copyProperties(req, model);
		model.setTaskId(taskId);
		model.setSeedId(sourcePlantTask.getSeedId());
		model.setFarmerId(sourcePlantTask.getFarmerId());
		repo.save(model);
	}


	@Override
	public List<SourceGrowthRegulator> listByTaskId(String plantTaskId) {
		return repo.listByTaskId(plantTaskId);
	}

	
}

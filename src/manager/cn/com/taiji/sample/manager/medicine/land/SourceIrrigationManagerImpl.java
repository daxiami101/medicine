package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceIrrigation;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceIrrigationRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceIrrigationPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceIrrigation")
public class SourceIrrigationManagerImpl extends AbstractManager implements SourceIrrigationManager{
	@Autowired
	private SourceIrrigationRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;

	@Override
	public Pagination queryPage(SourceIrrigationPageRequest req) {
		return repo.page(req);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public SourceIrrigation findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public void createTask(SourceIrrigationPageRequest req, HttpServletRequest request) {
		String taskId = req.getTaskId();
		SourcePlantTask sourcePlantTask = plantTaskRepo.findById(taskId).get();
		SourceIrrigation model=new SourceIrrigation();
		BeanUtils.copyProperties(req, model);
		model.setTaskId(taskId);
		model.setFarmerId(sourcePlantTask.getFarmerId());
		model.setSeedId(sourcePlantTask.getSeedId());
		repo.save(model);
	}

	@Override
	public List<SourceIrrigation> listByTaskId(String plantTaskId) {
		// TODO Auto-generated method stub
		return repo.listByTaskId(plantTaskId);
	}


	

	
}

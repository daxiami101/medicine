package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceFertilization;
import cn.com.taiji.sample.repo.jpa.source.SourceFertilizationRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.request.source.SourceFertilizationPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceFertilization")
public class SourceFertilizationManagerImpl extends AbstractManager implements SourceFertilizationManager{
	@Autowired
	private SourceFertilizationRepo repo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;


	@Override
	public Pagination queryPage(SourceFertilizationPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}


	@Override
	public SourceFertilization findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}


	@Override
	public void createTask(SourceFertilization req, HttpServletRequest request) {
		
		String taskId = req.getTaskId();
		SourcePlantTask sourcePlantTask = plantTaskRepo.findById(taskId).get();
		req.setTaskId(taskId);
		req.setSeedId(sourcePlantTask.getSeedId());
		req.setFarmerId(sourcePlantTask.getFarmerId());
		repo.save(req);
	}


	@Override
	public List<SourceFertilization> listByTaskId(String plantTaskId) {
		// TODO Auto-generated method stub
		return repo.listByTaskId(plantTaskId);
	}

	
}

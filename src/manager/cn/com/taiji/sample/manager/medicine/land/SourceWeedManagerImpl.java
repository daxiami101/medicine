package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceWeed;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceWeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceWeedPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceWeed")
public class SourceWeedManagerImpl extends AbstractManager implements SourceWeedManager{
	@Autowired
	private SourceWeedRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;

	@Override
	public Pagination queryPage(SourceWeedPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
	}


	@Override
	public void createTask(SourceWeed req, HttpServletRequest request) {
		String taskId = req.getTaskId();
		SourcePlantTask sourcePlantTask = plantTaskRepo.findById(taskId).get();
		SourceWeed model=new SourceWeed();
		BeanUtils.copyProperties(req, model);
		model.setTaskId(taskId);
		System.out.println(sourcePlantTask.getFarmerId());
		model.setFarmerId(sourcePlantTask.getFarmerId());
		model.setSeedId(sourcePlantTask.getSeedId());
		repo.save(model);
	}


	@Override
	public SourceWeed findById(String id) {
		return repo.findById(id).get();
		
	}


	@Override
	public List<SourceWeed> listByTaskId(String plantTaskId) {
		return repo.listByTaskId(plantTaskId);
	}

	
}

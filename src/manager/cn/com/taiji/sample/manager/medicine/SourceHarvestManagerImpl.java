package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceHarvest;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceHarvestRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceHarvestPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceHarvestManager")
public class SourceHarvestManagerImpl extends AbstractManager implements SourceHarvestManager{
	@Autowired
	private SourceHarvestRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;
	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryPage(SourceHarvestPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public SourceHarvest findById(String id) {
		// TODO Auto-generated method stub
		System.out.println("-----");
		return repo.findById(id).get();
	}

	@Override
	public void createTask(SourceHarvest req, HttpServletRequest request) {
		String taskId = req.getPlantTaskId();
		SourcePlantTask sourcePlantTask = plantTaskRepo.findById(taskId).get();
		SourceHarvest model=new SourceHarvest();
		BeanUtils.copyProperties(req, model);
		model.setPlantTaskId(taskId);
		model.setSeedId(sourcePlantTask.getSeedId());
		model.setFarmerId(sourcePlantTask.getFarmerId());
		repo.save(model);
	}
}

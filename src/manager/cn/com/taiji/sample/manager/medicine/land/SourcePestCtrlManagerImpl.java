package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourcePestCtrl;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePestCtrlRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourcePestCtrlPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourcePestCtrl")
public class SourcePestCtrlManagerImpl extends AbstractManager implements SourcePestCtrlManager{
	@Autowired
	private SourcePestCtrlRepo repo;
	@Autowired
	private SourceFarmerRepo farmerRepo;
	@Autowired
	private SourcePlantTaskRepo plantTaskRepo;
	@Autowired
	private SourceSeedRepo seedRepo;

	@Override
	public Pagination queryPage(SourcePestCtrlPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}


	@Override
	public SourcePestCtrl findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}


	@Override
	public void createTask(SourcePestCtrl req, HttpServletRequest request) {
		String taskId = req.getTaskId();
		SourcePlantTask sourcePlantTask = plantTaskRepo.findById(taskId).get();
		SourcePestCtrl model=new SourcePestCtrl();
		BeanUtils.copyProperties(req, model);
		model.setTaskId(taskId);
		model.setSeedId(sourcePlantTask.getSeedId());
		model.setFarmerId(sourcePlantTask.getFarmerId());
		repo.save(model);
	}


	@Override
	public List<SourcePestCtrl> listByTaskId(String plantTaskId) {
		// TODO Auto-generated method stub
		return repo.listByTaskId(plantTaskId);
	}

	
}

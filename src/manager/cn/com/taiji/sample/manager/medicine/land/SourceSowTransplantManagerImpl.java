package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceSowTransplant;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSowTransplantRepo;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;
import cn.com.taiji.sample.repo.request.source.SourceSowTransplantPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceSowTransplant")
public class SourceSowTransplantManagerImpl extends AbstractManager implements SourceSowTransplantManager{
	@Autowired
	private SourceSowTransplantRepo repo;
	@Autowired
	private SourcePlantTaskRepo taskRepo;

	@Override
	public Pagination queryPage(SourceSowTransplantPageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}

	@Override
	public SourceSowTransplant findById(String id) {
		// TODO Auto-generated method stub
//		System.out.println("one");
//		System.out.println(repo.getOne(id));
		SourceSowTransplant sourceSowTransplant = repo.findById(id).get();
		return sourceSowTransplant;
	}

	@Override
	public void update(SourceSowTransplant user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(@Valid SourceSowTransplant pageModel) {
		// TODO Auto-generated method stub
		repo.save(pageModel);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public void chooseTask(SourcePlantTaskPageRequest pageModel, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String taskId = pageModel.getTaskId();
		
	}

	@Override
	public void createTask(SourceSowTransplantPageRequest req, HttpServletRequest request) {
		// TODO Auto-generated method stub
		//新建任务
		SourcePlantTask sourcePlantTask = taskRepo.findById(req.getTaskId()).get();
		String taskId = req.getTaskId();
		SourceSowTransplant model=new SourceSowTransplant();
		BeanUtils.copyProperties(req, model);
		model.setTaskId(taskId);
		model.setSeedId(sourcePlantTask.getSeedId());
		model.setFarmerId(sourcePlantTask.getFarmerId());
		repo.save(model);
	}

	@Override
	public List<SourceSowTransplant> listByTaskId(String plantTaskId) {
		return repo.listByTaskId(plantTaskId);
	}


	
}

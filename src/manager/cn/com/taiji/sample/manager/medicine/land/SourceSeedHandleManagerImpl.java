package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.dao.ResultConverter;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.model.SourceSeedHandleModel;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedHandleRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourceSeedHandlePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceSeedHandle")
public class SourceSeedHandleManagerImpl extends AbstractManager implements SourceSeedHandleManager,
ResultConverter<SourceSeedHandle, SourceSeedHandleModel>
{
	@Autowired
	private SourceSeedHandleRepo repo;
	@Autowired
	private SourceSeedRepo seedRepo;
	@Autowired
	private SourcePlantTaskRepo taskRepo;


	@Override
	public Pagination queryPage(SourceSeedHandlePageRequest req) {
		return repo.page(req);
	}


	@Override
	public void createTask(SourceSeedHandle req, HttpServletRequest request) {
		String taskId = req.getTaskId();
		System.out.println("taskId:"+taskId);
		SourcePlantTask plantTask = taskRepo.findById(taskId).get();
		SourceSeedHandle model=new SourceSeedHandle();
		BeanUtils.copyProperties(req, model);
		model.setTaskId(taskId);
		model.setSeedId(plantTask.getSeedId());
		model.setFarmerId(plantTask.getFarmerId());
		repo.save(model);
	}
	@Override
	public SourceSeedHandle findById(String id) {
		return repo.findById(id).get();
	}


	@Override
	public void update(SourcePlantTask user) {
		
	}


	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public SourceSeedHandleModel convert(SourceSeedHandle from) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SourceSeedHandle> listByTaskId(String id) {
		return repo.listByTaskId(id);
	}

	
}

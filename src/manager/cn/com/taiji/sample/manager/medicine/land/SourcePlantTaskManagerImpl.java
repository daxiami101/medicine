package cn.com.taiji.sample.manager.medicine.land;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.SourceSeed;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceSeedRepo;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourcePlantTask")
public class SourcePlantTaskManagerImpl extends AbstractManager implements SourcePlantTaskManager{
	@Autowired
	private SourcePlantTaskRepo repo;
	@Autowired
	private SourceSeedRepo seedRepo;


	@Override
	public Pagination queryPage(SourcePlantTaskPageRequest req) {
		return repo.page(req);
	}


	@Transactional
	@Override
	public void save(@Valid SourcePlantTask pageModel) {
		repo.save(pageModel);
	}


	@Override
	public SourcePlantTask findById(String id) {
		return repo.findById(id).get();
	}

	@Transactional
	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Transactional
	@Override
	public void update( SourcePlantTask source) {
		String id = source.getId();
		System.out.println("id:"+id);
		SourcePlantTask model = repo.findById(id).get();
		BeanUtils.copyProperties(source, model);
		System.out.println("model:"+model);
		repo.save(model);
	}


	@Transactional
	@Override
	public void createPlanTask(SourcePlantTaskPageRequest req) {
		System.out.println("start===123");
		String seedId = req.getSeedId();
		System.out.println("seedId:"+seedId);
		Optional<SourceSeed> optional = seedRepo.findById(seedId);
		System.out.println(optional.isPresent());
		
		if(optional.isPresent()){
//			SourceSeed sourceSeed = optional.get();
			SourcePlantTask task=new SourcePlantTask();
			task.setSeedId(seedId);//为该种子批次，创建种植任务
			BeanUtils.copyProperties(req, task);
			System.out.println("task:"+task);
			repo.save(task);
		}else{
			System.out.println("no seed");
		}
	}

	@Transactional
	@Override
	public void chooseFarmer(SourcePlantTaskPageRequest pageModel) {
		// TODO Auto-generated method stub
		System.out.println("taskId:"+pageModel.getTaskId());
		SourcePlantTask task=repo.getOne(pageModel.getTaskId());
		System.out.println("task:"+task);
		String farmerId = pageModel.getSelectFarmerId();
		System.out.println("farmerId:"+farmerId);
//		BeanUtils.copyProperties(pageModel, task);
		task.setFarmerId(farmerId);
		repo.save(task);
	}
}

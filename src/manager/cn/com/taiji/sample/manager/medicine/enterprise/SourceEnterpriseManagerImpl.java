package cn.com.taiji.sample.manager.medicine.enterprise;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceEnterprise;
import cn.com.taiji.sample.repo.jpa.source.SourceEnterpriseRepo;
import cn.com.taiji.sample.repo.request.source.SourceEnterprisePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceEnterpriseManager")
public class SourceEnterpriseManagerImpl extends AbstractManager implements SourceEnterpriseManager{
	@Autowired
	private SourceEnterpriseRepo repo;


	@Override
	public Pagination queryPage(SourceEnterprisePageRequest req) {
		// TODO Auto-generated method stub
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}


	@Override
	public SourceEnterprise findById(String id) {
		// TODO Auto-generated method stub
		return repo.getOne(id);
	}


	@Override
	public void createTask(SourceEnterprise req, HttpServletRequest request) {
//		String taskId = req.getTaskId();
//		SourceDiseaseCtrl model=new SourceDiseaseCtrl();
//		BeanUtils.copyProperties(req, model);
//		model.setTaskId(taskId);
//		repo.save(model);
	}

	
}

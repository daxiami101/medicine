package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceMedicineProduce;
import cn.com.taiji.sample.repo.jpa.source.SourceMedicineProduceRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePurchaseRepo;
import cn.com.taiji.sample.repo.request.source.SourceMedicineProducePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * 饮片生产
 */
@Service("sourceMedicineProduce")
public class SourceMedicineProduceManagerImpl extends AbstractManager implements SourceMedicineProduceManager{
	@Autowired
	private SourceMedicineProduceRepo repo;
	@Autowired
	private SourcePurchaseRepo purchaseRepo;


	@Override
	public Pagination queryPage(SourceMedicineProducePageRequest req) {
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public SourceMedicineProduce findById(String id) {
		return repo.findById(id).get();
	}


	@Override
	public void createTask(SourceMedicineProduce req, HttpServletRequest request) {
		repo.save(req);
	}

	
}

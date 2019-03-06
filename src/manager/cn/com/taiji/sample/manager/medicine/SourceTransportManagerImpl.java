package cn.com.taiji.sample.manager.medicine;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.source.SourceSold;
import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.repo.jpa.source.SourceSoldRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceTransportRepo;
import cn.com.taiji.sample.repo.request.source.SourceTransportPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 */
@Service("sourceTransport")
public class SourceTransportManagerImpl extends AbstractManager implements SourceTransportManager{
	@Autowired
	private SourceTransportRepo repo;
	@Autowired
	private SourceSoldRepo soldRepo;
	@Override
	public String add(User user, User loginUser) throws JsonManagerException {
		return null;
	}

	@Override
	public Pagination queryPage(SourceTransportPageRequest req) {
		Pagination page = repo.page(req);
		return page;
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public SourceTransport findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public void createTask(SourceTransport req, HttpServletRequest request) {
		 String soldId = req.getSoldId();
		SourceTransport model=new SourceTransport();
		BeanUtils.copyProperties(req, model);
		SourceSold sourceSold = soldRepo.findById(soldId).get();
		
		model.setPlantTaskId(sourceSold.getPlantTaskId());
		model.setFarmerId(sourceSold.getFarmerId());
		model.setSeedId(sourceSold.getSeedId());
		model.setProcessId(sourceSold.getProcessId());
		model.setHarvestId(sourceSold.getHarvestId());
		model.setProcessPackageId(sourceSold.getProcessPackageId());
		model.setStoreId(sourceSold.getStoreId());
		repo.save(model);
	}
}

package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceMedicineStore;
import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.repo.jpa.source.SourceMedicineStoreRepo;
import cn.com.taiji.sample.repo.jpa.source.SourcePurchaseRepo;
import cn.com.taiji.sample.repo.request.source.SourceMedicineStorePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceMedicineStore")
public class SourceMedicineStoreManagerImpl extends AbstractManager implements SourceMedicineStoreManager{
	@Autowired
	private SourceMedicineStoreRepo repo;
	@Autowired
	private SourcePurchaseRepo purchaseRepo;

	@Override
	public Pagination queryPage(SourceMedicineStorePageRequest req) {
		return repo.page(req);
	}


	@Override
	public SourceMedicineStore findById(String id) {
		return repo.findById(id).get();
	}


	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public void createTask(SourceMedicineStore req, HttpServletRequest request) {
		String purchaseId = req.getPurchaseId();
		SourcePurchase sourcePurchase = purchaseRepo.findById(purchaseId).get();
		
		req.setPlantTaskId(sourcePurchase.getPlantTaskId());
		req.setFarmerId(sourcePurchase.getFarmerId());
		req.setSeedId(sourcePurchase.getSeedId());
		req.setProcessId(sourcePurchase.getProcessId());
		req.setHarvestId(sourcePurchase.getHarvestId());
		req.setProcessPackageId(sourcePurchase.getProcessPackageId());
		req.setStoreId(sourcePurchase.getStoreId());
		req.setSoldId(sourcePurchase.getSoldId());
		req.setProcessId(sourcePurchase.getProcessId());
		req.setTransportId(sourcePurchase.getTransportId());
		repo.save(req);
	}

	
}

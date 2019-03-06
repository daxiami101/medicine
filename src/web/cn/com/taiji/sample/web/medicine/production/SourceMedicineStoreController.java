package cn.com.taiji.sample.web.medicine.production;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.sample.entity.dict.source.MaterialStatus;
import cn.com.taiji.sample.entity.source.SourceMedicineStore;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.SourceSoldManager;
import cn.com.taiji.sample.manager.medicine.SourceStoreManager;
import cn.com.taiji.sample.manager.medicine.SourceTransportManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessPackageManager;
import cn.com.taiji.sample.manager.medicine.produce.SourceMedicineStoreManager;
import cn.com.taiji.sample.manager.medicine.produce.SourcePurchaseManager;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 * 药材入库
 */
@Controller
@RequestMapping("/tablet/medicine")
public class SourceMedicineStoreController extends BaseLogController
{
	@Autowired
	private SourceMedicineStoreManager manager;
	@Autowired
	private SourcePurchaseManager purchaseManager;
	@Autowired
	private SourceHarvestManager harvestManager;
	@Autowired
	private SourceSeedManager seedManager;
	@Autowired
	private SourceFarmerManager farmerManager;
	@Autowired
	private SourceProcessManager processManager;
	@Autowired
	private SourceProcessPackageManager processPackageManager;
	@Autowired
	private SourcePlantTaskManager plantTaskManager;
	@Autowired
	private SourceStoreManager storeManager;
	@Autowired
	private SourceSoldManager soldManager;
	@Autowired
	private SourceTransportManager transportManager;
	private final String prefix = "tablet/medicine/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourcePurchasePageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("materialStatuses", MaterialStatus.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourcePurchasePageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", purchaseManager.queryPage(req));
//		model.addAttribute("canChangeStatus", (!paramConfig.isEnableSso() || paramConfig.isEnableLocalLogin()));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		System.out.println("detele---:"+id);
		manager.delete(id);
		addSuccess(response, "删除成功");
//		super.addSysLog(request, "删除角色({})成功", roleName);
		return prefix + "result";
	}
	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") String id, Model model)
	{
		
		
		SourceMedicineStore medicineStore = manager.findById(id);
		model.addAttribute("medicineStoreModel",medicineStore);
		model.addAttribute("farmerModel", farmerManager.findById(medicineStore.getFarmerId()));
		model.addAttribute("seedModel", seedManager.findById(medicineStore.getSeedId()));
		model.addAttribute("processModel", processManager.findById(medicineStore.getProcessId()));
		model.addAttribute("processPackageModel", processPackageManager.findById(medicineStore.getProcessPackageId()));
		model.addAttribute("plantTaskModel", plantTaskManager.findById(medicineStore.getPlantTaskId()));
		model.addAttribute("harvestModel", harvestManager.findById(medicineStore.getHarvestId()));
		model.addAttribute("storeModel", storeManager.findById(medicineStore.getStoreId()));
		model.addAttribute("soldModel", soldManager.findById(medicineStore.getSoldId()));
		model.addAttribute("transportModel", transportManager.findById(medicineStore.getTransportId()));
		model.addAttribute("purchaseModel", purchaseManager.findById(medicineStore.getPurchaseId()));
		
		return prefix + "view";
	}

}

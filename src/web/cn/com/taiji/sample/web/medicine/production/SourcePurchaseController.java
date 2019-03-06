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
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.SourceSoldManager;
import cn.com.taiji.sample.manager.medicine.SourceStoreManager;
import cn.com.taiji.sample.manager.medicine.SourceTransportManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessPackageManager;
import cn.com.taiji.sample.manager.medicine.produce.SourcePurchaseManager;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 * 采购管理
 */
@Controller
@RequestMapping("/tablet/purchase")
public class SourcePurchaseController extends BaseLogController
{
	@Autowired
	private SourcePurchaseManager manager;
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
	private final String prefix = "tablet/purchase/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourcePurchasePageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourcePurchasePageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", manager.queryPage(req));
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
		SourcePurchase sourcePurchase = manager.findById(id);

		model.addAttribute("purchaseModel", sourcePurchase);
		model.addAttribute("farmerModel", farmerManager.findById(sourcePurchase.getFarmerId()));
		model.addAttribute("seedModel", seedManager.findById(sourcePurchase.getSeedId()));
		model.addAttribute("processModel", processManager.findById(sourcePurchase.getProcessId()));
		model.addAttribute("processPackageModel", processPackageManager.findById(sourcePurchase.getProcessPackageId()));
		model.addAttribute("plantTaskModel", plantTaskManager.findById(sourcePurchase.getPlantTaskId()));
		model.addAttribute("harvestModel", harvestManager.findById(sourcePurchase.getHarvestId()));
		model.addAttribute("storeModel", storeManager.findById(sourcePurchase.getStoreId()));
		model.addAttribute("soldModel", soldManager.findById(sourcePurchase.getSoldId()));
		model.addAttribute("transportModel", transportManager.findById(sourcePurchase.getTransportId()));
		
		return prefix + "view";
	}
	/*添加用户--接口，授予权限*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") SourcePurchase queryModel, Model model)
	{
//		model.addAttribute("apiInfos", infoManager.findAll());//传入接口信息
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("pageModel") SourcePurchase pageModel,
			HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		manager.save(pageModel);
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
	@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	public String editGet(@PathVariable("id") String id,@ModelAttribute("pageModel") SourcePurchase queryModel, Model model){
		System.out.println("id:"+id);
		SourcePurchase source = manager.findById(id);
		model.addAttribute("model", source);
		return prefix + "edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editPost(@ModelAttribute("pageModel") SourcePurchase entity, Model model,HttpServletResponse response){
//		userManager.checkIp(entity);//校驗ip不空
		manager.update(entity);
		addSuccess(response, "更新成功");
		return prefix+"result";
	}
}

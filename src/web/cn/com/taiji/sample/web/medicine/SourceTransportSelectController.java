package cn.com.taiji.sample.web.medicine;

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
import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.SourceSoldManager;
import cn.com.taiji.sample.manager.medicine.SourceStoreManager;
import cn.com.taiji.sample.manager.medicine.SourceTransportManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessPackageManager;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;
import cn.com.taiji.sample.repo.request.source.SourceSoldPageRequest;
import cn.com.taiji.sample.web.BaseLogController;

@Controller
@RequestMapping("/medicine/transport/taskSelect")
public class SourceTransportSelectController extends BaseLogController
{
	@Autowired
	private SourcePlantTaskManager planTaskManager;
	@Autowired
	private SourceTransportManager manager;
	@Autowired
	private SourceSoldManager soldManager;
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
	private final String prefix = "medicine/transport/taskSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SourceSoldPageRequest req)
	{
		System.out.println("123");
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceSoldPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", soldManager.queryPage(req));
		System.out.println("select ---");
		return prefix + "queryResult";
	}
	@RequestMapping(value="/randomConfig", method=RequestMethod.POST)
	public String randomConfig(@ModelAttribute("queryModel")SourcePlantTaskPageRequest pageModel, HttpServletRequest request, HttpServletResponse response) throws ManagerException{
		System.out.println("config===123"+pageModel.getTaskId());
//		driManager.randomConfig(driModel, request);
//		manager.chooseTask(pageModel,request);
		addSuccess(response, "设置成功");
		return "noteInfo";
	}
	@RequestMapping(value = "/createTask/{id}", method = RequestMethod.GET)
	public String createTask(@PathVariable("id") String id,@ModelAttribute("pageModel") SourceTransport req, Model model)
	{
		System.out.println("view:"+id);
//		planTaskManager.createPlanTask(seedId);
		model.addAttribute("task", soldManager.findById(id)); 
		model.addAttribute("plantMethods", PlantMethod.values()); 
		return prefix + "createTask";
	}
	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	public String createTaskPost(@ModelAttribute("pageModel") SourceTransport req, HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		manager.createTask(req,request);	
//		model.addAttribute("model", manager.findById(id));
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") String id, Model model) {
		SourceTransport transport = manager.findById(id);

		model.addAttribute("model", transport);
		model.addAttribute("farmerModel", farmerManager.findById(transport.getFarmerId()));
		model.addAttribute("seedModel", seedManager.findById(transport.getSeedId()));
		model.addAttribute("processModel", processManager.findById(transport.getProcessId()));
		model.addAttribute("processPackageModel", processPackageManager.findById(transport.getProcessPackageId()));
		model.addAttribute("plantTaskModel", plantTaskManager.findById(transport.getPlantTaskId()));
		model.addAttribute("harvestModel", harvestManager.findById(transport.getHarvestId()));
		model.addAttribute("storeModel", storeManager.findById(transport.getStoreId()));
		model.addAttribute("soldModel", soldManager.findById(transport.getSoldId()));
		return prefix + "view";
	}
}

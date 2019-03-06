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
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.source.SourceHarvest;
import cn.com.taiji.sample.entity.source.land.SourceDiseaseCtrl;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.repo.request.source.SourceHarvestPageRequest;
import cn.com.taiji.sample.web.BaseLogController;

/**
 * 
 * @author admin02
 * 2018年10月28日 上午11:30:15
 * TODO
 * 采收管理
 */
@Controller
@RequestMapping("/medicine/harvest")
public class SourceHarvestController extends BaseLogController
{
	@Autowired
	private SourceHarvestManager manager;
	@Autowired
	private SourceSeedManager seedManager;
	@Autowired
	private SourceFarmerManager farmerManager;
	private final String prefix = "medicine/harvest/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceHarvestPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceHarvestPageRequest req,HttpServletRequest request, Model model)
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
		
		SourceHarvest task = manager.findById(id);
			model.addAttribute("model",task );
			model.addAttribute("seedModel", seedManager.findById(task.getSeedId()));
			model.addAttribute("farmerModel", farmerManager.findById(task.getFarmerId()));
		return prefix + "view";
	}
}

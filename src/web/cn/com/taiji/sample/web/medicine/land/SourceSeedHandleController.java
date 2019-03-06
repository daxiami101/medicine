package cn.com.taiji.sample.web.medicine.land;

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

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.dict.source.ReproduceMaterial;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.land.SourceSeedHandleManager;
import cn.com.taiji.sample.repo.request.source.SourceSeedHandlePageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * 种子种苗处理
 */
@Controller
@RequestMapping("/land/seedHandle")
public class SourceSeedHandleController extends BaseLogController
{
	@Autowired
	private SourceSeedHandleManager manager;
	@Autowired
	private SourceSeedManager seedManager;
	@Autowired
	private SourceFarmerManager farmerManager;
	private final String prefix = "land/seedHandle/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceSeedHandlePageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceSeedHandlePageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", manager.queryPage(req));
//		model.addAttribute("canChangeStatus", (!paramConfig.isEnableSso() || paramConfig.isEnableLocalLogin()));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id,@ModelAttribute("pageModel") SourcePlantTask user, HttpServletRequest request,Model model)
	{
		System.out.println("edit123:"+id);
		SourceSeedHandle task = manager.findById(id);
		model.addAttribute("model", task);
		model.addAttribute("reproduceMaterials", ReproduceMaterial.values());
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("pageModel") SourcePlantTask user, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
//		User loginUser=LoginHelper.getLoginUser(request);
		manager.update(user);
		addSuccess(response, "修改成功");
//		model.addAttribute("vo", po);
//		super.addSysLog(request, "修改用户({})成功", user.getName());
		return prefix + "result";
	}
	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") String id, Model model)
	{
		System.out.println("view:"+id);
		SourceSeedHandle task = manager.findById(id);
		model.addAttribute("model", task);
		model.addAttribute("seedModel", seedManager.findById(task.getSeedId()));
		model.addAttribute("farmerModel", farmerManager.findById(task.getFarmerId()));
		return prefix + "view";
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
}

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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.entity.source.land.SourceSowTransplant;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.land.SourceSowTransplantManager;
import cn.com.taiji.sample.repo.request.source.SourceSowTransplantPageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 * 播种移栽
 */
@Controller
@RequestMapping("/land/sowTransplant")
public class SourceSowTransplantController extends BaseLogController
{
	@Autowired
	private SourceSeedManager seedManager;
	@Autowired
	private SourceFarmerManager farmerManager;
	@Autowired
	private SourceSowTransplantManager manager;
	private final String prefix = "land/sowTransplant/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceSowTransplantPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("plantMethods", PlantMethod.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceSowTransplantPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", manager.queryPage(req));
//		model.addAttribute("canChangeStatus", (!paramConfig.isEnableSso() || paramConfig.isEnableLocalLogin()));
		return prefix + "queryResult";
	}


	@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	public String editGet(@PathVariable("id") String id, Model model){
//		InfoServiceUser infoServiceUser = manager.findById(id);
//		model.addAttribute("infoUserModel", infoServiceUser);
		return prefix + "edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editPost(@ModelAttribute("pageModel") SourceSowTransplant entity, Model model,HttpServletResponse response){
//		userManager.checkIp(entity);//校驗ip不空
//		userManager.update(entity);
		addSuccess(response, "更新成功");
		return prefix+"result";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") SourceSowTransplant queryModel, Model model)
	{
//		model.addAttribute("infoUsers", userManager.findAll());//传入用户信息
//		model.addAttribute("apiInfos", infoManager.findAll());//传入接口信息
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("pageModel") SourceSowTransplant pageModel,
			HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		manager.save(pageModel);
		addSuccess(response, "添加成功");
		return prefix + "result";
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
		SourceSowTransplant task = manager.findById(id);
		model.addAttribute("model", task);
		model.addAttribute("seedModel", seedManager.findById(task.getSeedId()));
		model.addAttribute("farmerModel", farmerManager.findById(task.getFarmerId()));
		return prefix + "view";
	}
}

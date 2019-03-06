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
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.repo.request.source.SourceFarmerPageRequest;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * @author admin02
 * 2018年10月21日 下午11:12:35
 * 种植任务--农户信息选择
 * TODO
 */
@Controller
@RequestMapping("/land/plantTask/farmerSelect")
public class SourcePlantTaskFarmerSelectController extends BaseLogController
{
	@Autowired
	private SourceFarmerManager farmerManager;
	@Autowired
	private SourcePlantTaskManager planTaskManager;
	private final String prefix = "land/plantTask/farmerSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SourceFarmerPageRequest req)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceFarmerPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", farmerManager.queryPage(req));
		System.out.println("select ---");
		return prefix + "queryResult";
	}
	@RequestMapping(value = "/chooseFarmer/{id}", method = RequestMethod.GET)
	public String createTask(@PathVariable("id") String plantTaskId,@ModelAttribute("pageModel") SourcePlantTaskPageRequest req, Model model)
	{
		System.out.println("chooseFarmer:"+plantTaskId);
//		planTaskManager.createPlanTask(seedId);
		model.addAttribute("seedModel", planTaskManager.findById(plantTaskId)); 
		model.addAttribute("farmerTypes", FarmerType.values()); 
		model.addAttribute("taskId", plantTaskId); 
		return prefix + "manage";
	}
	@RequestMapping(value = "/chooseFarmer", method = RequestMethod.POST)
	public String createTaskPost(@Valid @ModelAttribute("pageModel") SourcePlantTaskPageRequest req, HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		System.out.println("view:createTask");
		planTaskManager.createPlanTask(req);
//		model.addAttribute("model", manager.findById(id));
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
	@RequestMapping(value="/randomConfig", method=RequestMethod.POST)
	public String randomConfig(@ModelAttribute("pageModel")SourcePlantTaskPageRequest pageModel, HttpServletRequest request, HttpServletResponse response) throws ManagerException{
		System.out.println("config===123"+pageModel.getTaskNo());
//		driManager.randomConfig(driModel, request);
		planTaskManager.chooseFarmer(pageModel);
		addSuccess(response, "农户配置种植任务成功");
		return "noteInfo";
	}
}

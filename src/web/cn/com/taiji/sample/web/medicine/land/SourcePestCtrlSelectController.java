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
import cn.com.taiji.sample.entity.source.land.SourcePestCtrl;
import cn.com.taiji.sample.manager.medicine.land.SourcePestCtrlManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;
import cn.com.taiji.sample.web.BaseLogController;

@Controller
@RequestMapping("/land/pestCtrl/taskSelect")
public class SourcePestCtrlSelectController extends BaseLogController
{
	@Autowired
	private SourcePlantTaskManager planTaskManager;
	@Autowired
	private SourcePestCtrlManager manager;
	private final String prefix = "land/pestCtrl/taskSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SourcePlantTaskPageRequest req)
	{
		System.out.println("123");
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourcePlantTaskPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", planTaskManager.queryPage(req));
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
	public String createTask(@PathVariable("id") String id,@ModelAttribute("pageModel") SourcePestCtrl req, Model model)
	{
		System.out.println("view:"+id);
//		planTaskManager.createPlanTask(seedId);
		model.addAttribute("task", planTaskManager.findById(id)); 
		model.addAttribute("plantMethods", PlantMethod.values()); 
		return prefix + "createTask";
	}
	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	public String createTaskPost(@ModelAttribute("pageModel") SourcePestCtrl req, HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		manager.createTask(req,request);
//		model.addAttribute("model", manager.findById(id));
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
}

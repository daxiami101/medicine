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
import cn.com.taiji.sample.manager.LoginHelper;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;
import cn.com.taiji.sample.repo.request.source.SourceSeedPageRequest;
import cn.com.taiji.sample.web.BaseLogController;

@Controller
@RequestMapping("/land/plantTask/userSelect")
public class SourcePlantTaskSelectController extends BaseLogController
{
	@Autowired
	private SourceSeedManager manager;
	@Autowired
	private SourcePlantTaskManager planTaskManager;
	private final String prefix = "land/plantTask/userSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SourceSeedPageRequest req)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceSeedPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", manager.queryPage(req));
		System.out.println("select ---");
		return prefix + "queryResult";
	}
	@RequestMapping(value = "/createTask/{id}", method = RequestMethod.GET)
	public String createTask(@PathVariable("id") String id,@ModelAttribute("pageModel") SourcePlantTaskPageRequest req, Model model)
	{
//		planTaskManager.createPlanTask(seedId);
		model.addAttribute("seedModel", manager.findById(id)); 
		model.addAttribute("plantMethods", PlantMethod.values()); 
		return prefix + "createTask";
	}
	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	public String createTaskPost(@ModelAttribute("pageModel") SourcePlantTaskPageRequest req, HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		String dataSource = LoginHelper.getLoginUser(request).getLoginName();
		req.setDataSource(dataSource);
		planTaskManager.createPlanTask(req);
//		model.addAttribute("model", manager.findById(id));
		addSuccess(response, "添加成功");
		return "land/plantTask/" + "result";
	}
	
}

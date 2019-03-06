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
import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.manager.medicine.SourceTransportManager;
import cn.com.taiji.sample.manager.medicine.produce.SourceProductionManager;
import cn.com.taiji.sample.manager.medicine.produce.SourcePurchaseManager;
import cn.com.taiji.sample.manager.medicine.produce.SourceSoldOutManager;
import cn.com.taiji.sample.repo.request.source.SourceTransportPageRequest;
import cn.com.taiji.sample.web.BaseLogController;

@Controller
@RequestMapping("/tablet/purchase/taskSelect")
public class SourcePurchaseSelectController extends BaseLogController
{
	@Autowired
	private SourceSoldOutManager soldOutManager;
	@Autowired
	private SourceProductionManager productionManager;
	@Autowired
	private SourceTransportManager transportManager;
	@Autowired
	private SourcePurchaseManager purchaseManager;
	private final String prefix = "tablet/purchase/taskSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SourceTransportPageRequest req)
	{
		System.out.println("123");
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceTransportPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", transportManager.queryPage(req));
		return prefix + "queryResult";
	}
	@RequestMapping(value = "/createTask/{id}", method = RequestMethod.GET)
	public String createTask(@PathVariable("id") String id,@ModelAttribute("pageModel") SourcePurchase req, Model model)
	{
		System.out.println("view122313:"+id);
//		planTaskManager.createPlanTask(seedId);
//		System.out.println("taskNo:"+planTaskManager.findById(id).getTaskNo());
//		SourcePurchase findById = purchaseManager.findById(id);
//		System.out.println("findById:"+findById);
		model.addAttribute("task", transportManager.findById(id)); 
		return prefix + "createTask";
	}
	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	public String createTaskPost(@ModelAttribute("pageModel") SourcePurchase req, HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		purchaseManager.createTask(req,request);
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
	//请验通过
	@RequestMapping(value = "/pass/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		System.out.println("pass---:"+id);
		//manager.pass(id);
		addSuccess(response, "删除成功");
//		super.addSysLog(request, "删除角色({})成功", roleName);
		return prefix + "result";
	}
}

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
import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.source.SourceMedicineProduce;
import cn.com.taiji.sample.manager.medicine.produce.SourceMedicineProduceManager;
import cn.com.taiji.sample.manager.medicine.produce.SourcePurchaseManager;
import cn.com.taiji.sample.manager.medicine.produce.SourceWaitPackageManager;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;
import cn.com.taiji.sample.web.BaseLogController;

@Controller
@RequestMapping("/tablet/produce/taskSelect")
public class SourceMedicineProduceSelectController extends BaseLogController
{
	@Autowired
	private SourceMedicineProduceManager manager;
	@Autowired
	private SourcePurchaseManager purchaseManager;
	@Autowired
	private SourceWaitPackageManager waitPackageManager;
	private final String prefix = "tablet/produce/taskSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SourcePurchasePageRequest req)
	{
		System.out.println("123");
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourcePurchasePageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", purchaseManager.queryPage(req));
		System.out.println("select ---");
		return prefix + "queryResult";
	}
	@RequestMapping(value = "/createTask/{id}", method = RequestMethod.GET)
	public String createTask(@PathVariable("id") String id,@ModelAttribute("pageModel") SourceMedicineProduce req, Model model)
	{
		System.out.println("view123:"+id);
//		SourcePurchase findById = purchaseManager.findById(id);
//		System.out.println("findById:"+findById);
		model.addAttribute("task", waitPackageManager.findById(id)); 
		model.addAttribute("plantMethods", PlantMethod.values()); 
		return prefix + "createTask";
	}
	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	public String createTaskPost(@ModelAttribute("pageModel") SourceMedicineProduce req, HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		manager.createTask(req,request);
//		model.addAttribute("model", manager.findById(id));
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

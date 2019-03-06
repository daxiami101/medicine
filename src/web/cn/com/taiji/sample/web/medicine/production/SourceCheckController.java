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
import cn.com.taiji.sample.entity.dict.source.MaterialStatus;
import cn.com.taiji.sample.manager.medicine.produce.SourceCheckManager;
import cn.com.taiji.sample.manager.medicine.produce.SourcePurchaseManager;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 * 物料请验
 */
@Controller
@RequestMapping("/tablet/materialCheck")
public class SourceCheckController extends BaseLogController
{
	@Autowired
	private SourceCheckManager manager;
	@Autowired
	private SourcePurchaseManager purchaseManager;
	private final String prefix = "tablet/materialCheck/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourcePurchasePageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("materialStatuses", MaterialStatus.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourcePurchasePageRequest req,HttpServletRequest request, Model model)
	{
	
		model.addAttribute("pagn", purchaseManager.queryPage(req));
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
		model.addAttribute("model", manager.findById(id));
		return prefix + "view";
	}
}

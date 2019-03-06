package cn.com.taiji.sample.web.medicine.enterprise;

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
import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.manager.medicine.SourceStoreManager;
import cn.com.taiji.sample.repo.request.source.SourceStorePageRequest;
import cn.com.taiji.sample.web.BaseLogController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-30 上午10:08:19<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/enterprise/storage")
public class SourceEnterpriseStoreController extends BaseLogController
{
	@Autowired
	private SourceStoreManager manager;
	private final String prefix = "enterprise/storage/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceStorePageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceStorePageRequest req,HttpServletRequest request, Model model)
	{
		req.setBuySell(BuySell.PURCHASE);
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
		model.addAttribute("model", manager.findById(id));
		return prefix + "view";
	}
}

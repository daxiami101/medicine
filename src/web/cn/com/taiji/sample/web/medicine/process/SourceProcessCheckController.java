package cn.com.taiji.sample.web.medicine.process;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessManager;
import cn.com.taiji.sample.repo.request.source.SourceProcessPageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 */
@Controller
@RequestMapping("/process/exam")
public class SourceProcessCheckController extends BaseLogController
{
	@Autowired
	private SourceProcessManager manager;
	private final String prefix = "process/exam/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceProcessPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceProcessPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", manager.queryPage(req));
//		model.addAttribute("canChangeStatus", (!paramConfig.isEnableSso() || paramConfig.isEnableLocalLogin()));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
//		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
//		model.addAttribute("roles", manager.queryPage(req));
		return prefix + "add";
	}

	@RequestMapping(value = "/openAdd", method = RequestMethod.GET)
	public String setupOpenAdd(@ModelAttribute("pageModel") User user, HttpServletRequest request,Model model)
	{
//		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
		return prefix + "openAdd";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String setupAdd(@ModelAttribute("pageModel") User user,
			@RequestParam(value = "userName", required = false) String userName, HttpServletRequest request,Model model)
	{
		user.setName(userName);
//		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
		return prefix + "add";
	}

}

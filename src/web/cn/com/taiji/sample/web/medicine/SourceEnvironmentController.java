package cn.com.taiji.sample.web.medicine;

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
import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.SourceEnvironment;
import cn.com.taiji.sample.manager.LoginHelper;
import cn.com.taiji.sample.manager.medicine.SourceEnviromentManager;
import cn.com.taiji.sample.manager.medicine.SourceProvinceManager;
import cn.com.taiji.sample.repo.request.source.SourceEnvironmentPageRequest;
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
@RequestMapping("/medicine/environment")
public class SourceEnvironmentController extends BaseLogController
{
	@Autowired
	private SourceEnviromentManager manager;
	@Autowired
	private SourceProvinceManager provinceManager;
	private final String prefix = "medicine/environment/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceEnvironmentPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("provinces", provinceManager.listAll());//传入省
		model.addAttribute("soilTextures", SoilTexture.values());//传入省
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceEnvironmentPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", manager.queryPage(req));
//		model.addAttribute("canChangeStatus", (!paramConfig.isEnableSso() || paramConfig.isEnableLocalLogin()));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") SourceEnvironment queryModel, Model model)
	{
		model.addAttribute("provinces", provinceManager.listAll());//传入省
		model.addAttribute("soilTextures", SoilTexture.values());
		System.out.println("add--get");
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("pageModel") SourceEnvironment pageModel,
			HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		System.out.println("add==post");
		String dataSource = LoginHelper.getLoginUser(request).getLoginName();
		pageModel.setDataSource(dataSource);
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
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request,Model model)
	{
		model.addAttribute("pageModel", manager.findById(id));
		model.addAttribute("provinces", provinceManager.listAll());//传入省
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") SourceEnvironment user, HttpServletRequest request, Model model,
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
		model.addAttribute("model", manager.findById(id));
		return prefix + "view";
	}
}

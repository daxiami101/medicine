package cn.com.taiji.sample.web.medicine;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.NoteModel;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.User.UserStatus;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.source.SourceCity;
import cn.com.taiji.sample.entity.source.SourceCounty;
import cn.com.taiji.sample.entity.source.SourceFarmer;
import cn.com.taiji.sample.manager.LoginHelper;
import cn.com.taiji.sample.manager.acl.RoleManager;
import cn.com.taiji.sample.manager.acl.UserManager;
import cn.com.taiji.sample.manager.medicine.SourceCityManager;
import cn.com.taiji.sample.manager.medicine.SourceCountyManager;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceProvinceManager;
import cn.com.taiji.sample.repo.request.source.SourceFarmerPageRequest;
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
@RequestMapping("/medicine/farmer")
public class SourceFarmerController extends BaseLogController
{
	@Autowired
	private SourceProvinceManager provinceManager;
	@Autowired
	private SourceCityManager cityManager;
	@Autowired
	private SourceCountyManager countyManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private UserManager userManager;
//	@Autowired
//	private CommParamConfig paramConfig;
	@Autowired
	private SourceFarmerManager manager;
	private final String prefix = "medicine/farmer/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceFarmerPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		model.addAttribute("provinces", provinceManager.listAll());//传入省
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceFarmerPageRequest req,HttpServletRequest request, Model model)
	{
		Pagination queryPage = manager.queryPage(req);
		model.addAttribute("pagn", queryPage);
		return prefix + "queryResult";
	}
	@ResponseBody
	@RequestMapping(value = "/getCitys/{key}",produces="text/plain;charset=UTF-8")
	public String getCity(@PathVariable("key") String key,HttpServletResponse response, Model model,
			HttpServletRequest request) throws IOException 
	{
		List<SourceCity>all=cityManager.listByProvinceId(key);
		System.out.println(JsonTools.toJsonStr(all));
		return JsonTools.toJsonStr(all);
	}
	@ResponseBody
	@RequestMapping(value = "/getCounty/{key}",produces="text/plain;charset=UTF-8")
	public String getCounty(@PathVariable("key") String key,HttpServletResponse response, Model model,
			HttpServletRequest request) throws IOException 
	{
		List<SourceCounty>all=countyManager.listByProvinceId(key);
		System.out.println(JsonTools.toJsonStr(all));
		return JsonTools.toJsonStr(all);
	}

	/*添加用户--接口，授予权限*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") SourceFarmer queryModel, Model model)
	{
		model.addAttribute("provinces", provinceManager.listAll());//传入省
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("pageModel") SourceFarmer pageModel,
			HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		String dataSource = LoginHelper.getLoginUser(request).getLoginName().toUpperCase();
		pageModel.setDataSource(dataSource);
		manager.save(pageModel);
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		manager.delete(id);
		addSuccess(response, "删除成功");
//		super.addSysLog(request, "删除角色({})成功", roleName);
		return prefix + "result";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request,Model model)
	{
		model.addAttribute("pageModel", manager.findById(id));
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") SourceFarmer user, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
//		User loginUser=LoginHelper.getLoginUser(request);
		manager.update(user);
		addSuccess(response, "修改成功");
//		model.addAttribute("vo", po);
//		super.addSysLog(request, "修改用户({})成功", user.getName());
		return prefix + "result";
	}
	
	@RequestMapping(value = "/editGrid", method = RequestMethod.POST)
	public void processEditGrid(@RequestParam("pk")String id,@RequestParam("name")String name,@RequestParam("value")String value,
			HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		User oldUser=userManager.findById(id);
		switch (name) {
		case "name":
			oldUser.setName(value);
			break;
		case "male":
			oldUser.setMale(value.equals("1"));
			break;
		case "roleId":
			throw new ManagerException("修改角色时抛个异常！");
		}
		User loginUser=LoginHelper.getLoginUser(request);
		userManager.update(oldUser, loginUser);
		responseJson(new NoteModel(true, "修改成功").toJson(), response);
	}

	@RequestMapping(value = "/status/{id}/{status}", method = RequestMethod.POST)
	public String changeStatus(@PathVariable(value = "id") String id, @PathVariable("status") UserStatus status,
			Model model, HttpServletResponse response) throws ManagerException
	{
		model.addAttribute("vo", userManager.updateStatus(id, status));
		addSuccess(response, "修改用户状态成功");
		return prefix + "result";
	}

	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("model", manager.findById(id));
		return prefix + "view";
	}

	@RequestMapping(value = "/info/{id}")
	public String info(@PathVariable("id") String id, Model model) throws Exception
	{
		Thread.sleep(300);
		model.addAttribute("pageModel", userManager.findById(id));
		return prefix + "info";
	}

	@RequestMapping("/isLoginNameValid")
	public void isLoginNameValid(@RequestParam("loginName") String loginName, HttpServletResponse response)
			throws IOException
	{
		logger.debug("loginName={}", loginName);
		response.getWriter().print(userManager.findByLoginName(loginName) == null);
	}
}

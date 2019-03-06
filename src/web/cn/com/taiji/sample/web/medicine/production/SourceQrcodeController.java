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
import cn.com.taiji.sample.entity.source.SourceQrCode;
import cn.com.taiji.sample.manager.LoginHelper;
import cn.com.taiji.sample.manager.tablet.SourceQrCodeManager;
import cn.com.taiji.sample.repo.request.source.SourceQrCodePageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 * 二维码管理
 */
@Controller
@RequestMapping("/tablet/qrcode")
public class SourceQrcodeController extends BaseLogController
{
	@Autowired
	private SourceQrCodeManager manager;
	private final String prefix = "tablet/qrcode/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceQrCodePageRequest req, Model model, HttpServletRequest request)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceQrCodePageRequest req,HttpServletRequest request, Model model)
	{
	
		model.addAttribute("pagn", manager.queryPage(req));
//		model.addAttribute("canChangeStatus", (!paramConfig.isEnableSso() || paramConfig.isEnableLocalLogin()));
		return prefix + "queryResult";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") SourceQrCode queryModel, Model model)
	{
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("pageModel") SourceQrCode pageModel,
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

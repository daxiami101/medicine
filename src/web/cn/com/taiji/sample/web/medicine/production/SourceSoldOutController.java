package cn.com.taiji.sample.web.medicine.production;

import java.io.File;
import java.io.IOException;

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

import com.google.zxing.WriterException;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.manager.medicine.produce.SourceSoldOutManager;
import cn.com.taiji.sample.model.tools.Img2Base64Util;
import cn.com.taiji.sample.model.tools.PhotoTools;
import cn.com.taiji.sample.model.tools.QrCodeUtils;
import cn.com.taiji.sample.repo.request.source.SourceSoldOutPageRequest;
import cn.com.taiji.sample.web.BaseLogController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 * 销售出库
 */
@Controller
@RequestMapping("/tablet/saleOut")
public class SourceSoldOutController extends BaseLogController
{
	@Autowired
	private SourceSoldOutManager manager;
	private final String prefix = "tablet/saleOut/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceSoldOutPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceSoldOutPageRequest req,HttpServletRequest request, Model model)
	{
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
	public String view(@PathVariable("id") String id, Model model) throws WriterException, IOException
	{
		model.addAttribute("model", manager.findById(id));
		File imgFile = QrCodeUtils.generatePic(id);
		model.addAttribute("photoSrc",  Img2Base64Util.getFile2Base64(imgFile));
		return prefix + "view";
	}
	@RequestMapping(value = "/pic/{id}")
	public String pic(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("model", manager.findById(id));
		return prefix + "view";
	}
}

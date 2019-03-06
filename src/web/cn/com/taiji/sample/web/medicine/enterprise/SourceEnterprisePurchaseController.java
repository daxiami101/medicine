package cn.com.taiji.sample.web.medicine.enterprise;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.dict.IOStatus;
import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.manager.LoginHelper;
import cn.com.taiji.sample.manager.medicine.produce.SourcePurchaseManager;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;
import cn.com.taiji.sample.web.BaseDownloadController;
/**
 * 
 * @author admin02
 * 2018年10月17日 下午12:42:21
 * TODO
 */
@Controller
@RequestMapping("/enterprise/purchase")
public class SourceEnterprisePurchaseController extends  BaseDownloadController 
{
	@Autowired
	private SourcePurchaseManager manager;
	private static Map<String,IOStatus> iostate;
	static {
		iostate=Maps.newHashMap();
	}
	private final String prefix = "enterprise/purchase/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourcePurchasePageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourcePurchasePageRequest req,HttpServletRequest request, Model model)
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
	@RequestMapping(value="/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		File file = new File(FileHelper.getWebappPath()+"/template/examOrderPlan.xlsx");
		download(request, response, file, "考试预约计划模板.xlsx");
		addSuccess(response, "下载成功");
	}
	@RequestMapping(value="/importExcel", method = RequestMethod.POST)
	public String importExcel(@RequestParam("file")MultipartFile file, Model model, HttpServletRequest request, HttpServletResponse response)throws Exception{
		User user = (User) LoginHelper.getLoginUser(request);
		HttpSession session = request.getSession();
		session.removeAttribute(user.getLoginName());
		iostate.remove(user.getLoginName());
	
		if(file==null||file.isEmpty()||file.getSize()==0){
			
			iostate.put(user.getLoginName(), IOStatus.FILEEMPTY);
			return "noteInfo";
		}
		String fileName = file.getOriginalFilename();
		
		if(fileName.endsWith(".xlsx")){
			
			boolean result = manager.checkFileType(file.getInputStream(), fileName.endsWith(".xlsx"));
			if(!result){
				iostate.put(user.getLoginName(), IOStatus.FILEERROR);
				return "noteInfo";
			}else{
				manager.importExcel(file, request,iostate);
				return "noteInfo";
			}
		}else{
			iostate.put(user.getLoginName(), IOStatus.FILEERROR);
			return "noteInfo";
		}
	} 
	@RequestMapping(value = "/getResult",produces="text/plain;charset=UTF-8;")
	@ResponseBody
	public String getResult(HttpServletRequest request)throws Exception
	{	
		User user = (User) LoginHelper.getLoginUser(request);
		HttpSession session=request.getSession();
		String type=request.getParameter("type");
		String result="";
		
		Map<String,String> v=Maps.newHashMap();
		if("import".equals(type)){
			Integer count=(Integer)request.getSession().getAttribute(user.getLoginName());
			if(count!=null){
				result=count+"";
			}else{
				result=0+"";
			}
			v.put("count", result);
			IOStatus state=iostate.get(user.getLoginName());
			if(state==null)
			v.put("state", "DOING");
			else{
				v.put("state", state.toString());
				if(state.equals(IOStatus.SUCCESS)||state.equals(IOStatus.FAILED)){
					session.removeAttribute(user.getLoginName());
				}
			}
			String json=JsonTools.toJsonStr(v);
			return json;
		}else{
			IOStatus state=iostate.get(user.getLoginName());
			if(state==null)
				v.put("state2", "DOING");
			else{
				v.put("state2", state.toString());
				
				Integer count=(Integer)request.getSession().getAttribute(user.getLoginName());
				if(count!=null){
					result=count+"";
				}else{
					result=0+"";
				}
				v.put("count2", result);
				
				if(state.equals(IOStatus.COMPLETE)){
					session.removeAttribute(user.getLoginName());
				}
			}
			return JsonTools.toJsonStr(v);
		}
	}
	/*添加用户--接口，授予权限*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") SourcePurchase queryModel, Model model)
	{
//		model.addAttribute("apiInfos", infoManager.findAll());//传入接口信息
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("pageModel") SourcePurchase pageModel,
			HttpServletResponse response, Model model,
			HttpServletRequest request)
	{
		manager.save(pageModel);
		addSuccess(response, "添加成功");
		return prefix + "result";
	}
	@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	public String editGet(@PathVariable("id") String id,@ModelAttribute("pageModel") SourcePurchase queryModel, Model model){
		System.out.println("id:"+id);
		SourcePurchase source = manager.findById(id);
		model.addAttribute("model", source);
		return prefix + "edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editPost(@ModelAttribute("pageModel") SourcePurchase entity, Model model,HttpServletResponse response){
//		userManager.checkIp(entity);//校驗ip不空
		manager.update(entity);
		addSuccess(response, "更新成功");
		return prefix+"result";
	}
}

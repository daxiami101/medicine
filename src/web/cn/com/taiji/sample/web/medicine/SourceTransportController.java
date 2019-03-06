package cn.com.taiji.sample.web.medicine;

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
import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.SourceSoldManager;
import cn.com.taiji.sample.manager.medicine.SourceStoreManager;
import cn.com.taiji.sample.manager.medicine.SourceTransportManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessPackageManager;
import cn.com.taiji.sample.model.tools.Img2Base64Util;
import cn.com.taiji.sample.model.tools.QrCodeUtils;
import cn.com.taiji.sample.repo.request.source.SourceTransportPageRequest;
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
@RequestMapping("/medicine/transport")
public class SourceTransportController extends BaseLogController
{
	@Autowired
	private SourceTransportManager manager;
	@Autowired
	private SourceHarvestManager harvestManager;
	@Autowired
	private SourceSeedManager seedManager;
	@Autowired
	private SourceFarmerManager farmerManager;
	@Autowired
	private SourceProcessManager processManager;
	@Autowired
	private SourceProcessPackageManager processPackageManager;
	@Autowired
	private SourcePlantTaskManager plantTaskManager;
	@Autowired
	private SourceStoreManager storeManager;
	@Autowired
	private SourceSoldManager soldManager;
	private final String prefix = "medicine/transport/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel")SourceTransportPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", FarmerType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SourceTransportPageRequest req,HttpServletRequest request, Model model)
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
		
		SourceTransport transport = manager.findById(id);

		model.addAttribute("model", transport);
		model.addAttribute("farmerModel", farmerManager.findById(transport.getFarmerId()));
		model.addAttribute("seedModel", seedManager.findById(transport.getSeedId()));
		model.addAttribute("processModel", processManager.findById(transport.getProcessId()));
		model.addAttribute("processPackageModel", processPackageManager.findById(transport.getProcessPackageId()));
		model.addAttribute("plantTaskModel", plantTaskManager.findById(transport.getPlantTaskId()));
		model.addAttribute("harvestModel", harvestManager.findById(transport.getHarvestId()));
		model.addAttribute("storeModel", storeManager.findById(transport.getStoreId()));
		model.addAttribute("soldModel", soldManager.findById(transport.getSoldId()));
		
		File imgFile = QrCodeUtils.generatePic(id);
		model.addAttribute("photoSrc",  Img2Base64Util.getFile2Base64(imgFile));
		return prefix + "view";
	}
	@RequestMapping(value = "/pic/{id}")
	public String pic(@PathVariable("id") String id, Model model) throws WriterException, IOException
	{
		
		File imgFile = QrCodeUtils.generatePic(id);
		model.addAttribute("photoSrc",  Img2Base64Util.getFile2Base64(imgFile));
		return prefix + "pic";
	}
}

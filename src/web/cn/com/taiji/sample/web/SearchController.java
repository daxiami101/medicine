package cn.com.taiji.sample.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import cn.com.taiji.common.model.acl.UserModel;
import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.entity.source.land.SourceDiseaseCtrl;
import cn.com.taiji.sample.entity.source.land.SourceFertilization;
import cn.com.taiji.sample.entity.source.land.SourceGrowthRegulator;
import cn.com.taiji.sample.entity.source.land.SourceIrrigation;
import cn.com.taiji.sample.entity.source.land.SourceOther;
import cn.com.taiji.sample.entity.source.land.SourcePestCtrl;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.entity.source.land.SourceSowTransplant;
import cn.com.taiji.sample.entity.source.land.SourceWeed;
import cn.com.taiji.sample.manager.acl.RoleResourceManager;
import cn.com.taiji.sample.manager.acl.UserManager;
import cn.com.taiji.sample.manager.comm.CommParamConfig;
import cn.com.taiji.sample.manager.medicine.SourceFarmerManager;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.manager.medicine.SourceSeedManager;
import cn.com.taiji.sample.manager.medicine.SourceSoldManager;
import cn.com.taiji.sample.manager.medicine.SourceStoreManager;
import cn.com.taiji.sample.manager.medicine.SourceTransportManager;
import cn.com.taiji.sample.manager.medicine.land.SourceDiseaseCtrlManager;
import cn.com.taiji.sample.manager.medicine.land.SourceFertilizationManager;
import cn.com.taiji.sample.manager.medicine.land.SourceGrowthRegulatorManager;
import cn.com.taiji.sample.manager.medicine.land.SourceIrrigationManager;
import cn.com.taiji.sample.manager.medicine.land.SourceOtherManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePestCtrlManager;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.manager.medicine.land.SourceSeedHandleManager;
import cn.com.taiji.sample.manager.medicine.land.SourceSowTransplantManager;
import cn.com.taiji.sample.manager.medicine.land.SourceWeedManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessManager;
import cn.com.taiji.sample.manager.medicine.process.SourceProcessPackageManager;
import cn.com.taiji.sample.manager.oauth.OAuthLoginManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-6-5 下午05:39:04<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
public class SearchController extends BaseLogController
{
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleResourceManager rrManager;
	@Autowired
	private CommParamConfig paramConfig;
	@Autowired
	private OAuthLoginManager oauthLoginManager;
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
//田间管理
	@Autowired
	private SourceSeedHandleManager seedhandleManager;
	@Autowired
	private SourceSowTransplantManager sowTransplantManager;
	@Autowired
	private SourceIrrigationManager irrigationManager;
	@Autowired
	private SourceFertilizationManager fertilizationManager;
	@Autowired
	private SourceWeedManager weedManager;
	@Autowired
	private SourceGrowthRegulatorManager growthRegulatorManager;
	@Autowired
	private SourceDiseaseCtrlManager diseaseCtrlManager;
	@Autowired
	private SourcePestCtrlManager pestCtrlManager;
	@Autowired
	private SourceOtherManager otherManager;
//	@RequestMapping(value = "/common/login",method=RequestMethod.POST)
//	public void login(HttpServletRequest request,HttpServletResponse response,
//			UserModel userModel,Model model) throws Exception
//	{
//		User loginUser=LoginValidator.validate(userModel, request, userManager);
//		LoginHelper.setSession(request, loginUser, rrManager.getRoleMenu(loginUser.getRole().getId()));
//		super.addLoginLog(request, "用户({})登录成功.", userModel.getUsername());
//		logger.info("用户通过本地登录界面登录成功:{}", loginUser);
//		
//		String jumpUri = (String) WebTools.getSessionAttribute(request, SessionNames.LAST_URI);
//		if (!StringTools.hasText(jumpUri) || jumpUri.startsWith("/app/common/welcome")){
//			jumpUri=request.getContextPath() + "/app/index?myMenuId=index";
//		}
//		response.setHeader("taiji_jump", jumpUri);
//		WebTools.setSessionAttribute(request, SessionNames.LAST_URI, null);
//		response.getWriter().print(jumpUri);
//	}
//	
	
	@RequestMapping(value = "/common/info/{id}",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String setupForm(@PathVariable("id") String id, HttpServletRequest request, UserModel user, BindingResult result, SessionStatus status,
			Model model)
	{
		logger.info("-----{}---", user.getUsername());
		model.addAttribute("oauthUrl", oauthLoginManager.getAuthLoginUrl());
		SourceTransport transport = manager.findById(id);
		String plantTaskId = transport.getPlantTaskId();
		
		model.addAttribute("model", transport);
		//田间管理
		List<SourceSeedHandle> sourceSeedHandles = seedhandleManager.listByTaskId(plantTaskId);
		model.addAttribute("seedHandleModel", sourceSeedHandles.size()==0?null:sourceSeedHandles.get(0));
		
		List<SourceSowTransplant> SourceSowTransplants = sowTransplantManager.listByTaskId(plantTaskId);
		model.addAttribute("sowTransplantModel", SourceSowTransplants.size()==0?null:SourceSowTransplants.get(0));
		
		List<SourceIrrigation> sourceIrrigations = irrigationManager.listByTaskId(plantTaskId);
		model.addAttribute("irrigationModel", sourceIrrigations.size()==0?null:sourceIrrigations.get(0));
		
		List<SourceFertilization> sourceFertilizations = fertilizationManager.listByTaskId(plantTaskId);
		model.addAttribute("fertilizationModel", sourceFertilizations.size()==0?null:sourceFertilizations.get(0));
		
		List<SourceWeed> sourceWeeds = weedManager.listByTaskId(plantTaskId);
		model.addAttribute("weedModel", sourceWeeds.size()==0?null:sourceWeeds.get(0));
		
		List<SourceGrowthRegulator> sourceGrowthRegulators = growthRegulatorManager.listByTaskId(plantTaskId);
		model.addAttribute("growthRegulatorModel", sourceGrowthRegulators.size()==0?null:sourceGrowthRegulators.get(0));
		
		List<SourceDiseaseCtrl> sourceDiseaseCtrl = diseaseCtrlManager.listByTaskId(plantTaskId);
		model.addAttribute("diseaseCtrlModel", sourceDiseaseCtrl.size()==0?null:sourceDiseaseCtrl.get(0));
		
		List<SourcePestCtrl> sourcePestCtrl = pestCtrlManager.listByTaskId(plantTaskId);
		model.addAttribute("pestCtrlModel", sourcePestCtrl.size()==0?null:sourcePestCtrl.get(0));
		
		List<SourceOther> sourceOther = otherManager.listByTaskId(plantTaskId);
		model.addAttribute("otherModel", sourceOther.size()==0?null:sourceOther.get(0));
		
		String farmerId = transport.getFarmerId();
		
		if(null==farmerId || "".equals(farmerId)){
			model.addAttribute("farmerModel", null);
		}else{
			model.addAttribute("farmerModel", farmerManager.findById(transport.getFarmerId()));
		}
		
		model.addAttribute("seedModel", seedManager.findById(transport.getSeedId()));
		
		model.addAttribute("processModel", processManager.findById(transport.getProcessId()));
		
		model.addAttribute("processPackageModel", processPackageManager.findById(transport.getProcessPackageId()));
		
		model.addAttribute("plantTaskModel", plantTaskManager.findById(transport.getPlantTaskId()));
		
		model.addAttribute("harvestModel", harvestManager.findById(transport.getHarvestId()));
		
		model.addAttribute("storeModel", storeManager.findById(transport.getStoreId()));
		
		model.addAttribute("soldModel", soldManager.findById(transport.getSoldId()));
		// sso前置校验
		return "welcome2";
		
	}

	@RequestMapping(value = "/common/info/{produceNo}",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String searchByProduceNo(@PathVariable("produceNo") String produceNo, HttpServletRequest request, UserModel user, BindingResult result, SessionStatus status,
			Model model)
	{
		logger.info("-----{}---", user.getUsername());
		model.addAttribute("oauthUrl", oauthLoginManager.getAuthLoginUrl());
//		SourceTransport transport = manager.findById(id);
//		String plantTaskId = transport.getPlantTaskId();
//		
//		model.addAttribute("model", transport);
//		//田间管理
//		List<SourceSeedHandle> sourceSeedHandles = seedhandleManager.listByTaskId(plantTaskId);
//		model.addAttribute("seedHandleModel", sourceSeedHandles.size()==0?null:sourceSeedHandles.get(0));
//		
//		List<SourceSowTransplant> SourceSowTransplants = sowTransplantManager.listByTaskId(plantTaskId);
//		model.addAttribute("sowTransplantModel", SourceSowTransplants.size()==0?null:SourceSowTransplants.get(0));
//		
//		List<SourceIrrigation> sourceIrrigations = irrigationManager.listByTaskId(plantTaskId);
//		model.addAttribute("irrigationModel", sourceIrrigations.size()==0?null:sourceIrrigations.get(0));
//		
//		List<SourceFertilization> sourceFertilizations = fertilizationManager.listByTaskId(plantTaskId);
//		model.addAttribute("fertilizationModel", sourceFertilizations.size()==0?null:sourceFertilizations.get(0));
//		
//		List<SourceWeed> sourceWeeds = weedManager.listByTaskId(plantTaskId);
//		model.addAttribute("weedModel", sourceWeeds.size()==0?null:sourceWeeds.get(0));
//		
//		List<SourceGrowthRegulator> sourceGrowthRegulators = growthRegulatorManager.listByTaskId(plantTaskId);
//		model.addAttribute("growthRegulatorModel", sourceGrowthRegulators.size()==0?null:sourceGrowthRegulators.get(0));
//		
//		List<SourceDiseaseCtrl> sourceDiseaseCtrl = diseaseCtrlManager.listByTaskId(plantTaskId);
//		model.addAttribute("diseaseCtrlModel", sourceDiseaseCtrl.size()==0?null:sourceDiseaseCtrl.get(0));
//		
//		List<SourcePestCtrl> sourcePestCtrl = pestCtrlManager.listByTaskId(plantTaskId);
//		model.addAttribute("pestCtrlModel", sourcePestCtrl.size()==0?null:sourcePestCtrl.get(0));
//		
//		List<SourceOther> sourceOther = otherManager.listByTaskId(plantTaskId);
//		model.addAttribute("otherModel", sourceOther.size()==0?null:sourceOther.get(0));
//		
//		String farmerId = transport.getFarmerId();
//		
//		if(null==farmerId || "".equals(farmerId)){
//			model.addAttribute("farmerModel", null);
//		}else{
//			model.addAttribute("farmerModel", farmerManager.findById(transport.getFarmerId()));
//		}
//		
//		model.addAttribute("seedModel", seedManager.findById(transport.getSeedId()));
//		
//		model.addAttribute("processModel", processManager.findById(transport.getProcessId()));
//		
//		model.addAttribute("processPackageModel", processPackageManager.findById(transport.getProcessPackageId()));
//		
//		model.addAttribute("plantTaskModel", plantTaskManager.findById(transport.getPlantTaskId()));
//		
//		model.addAttribute("harvestModel", harvestManager.findById(transport.getHarvestId()));
//		
//		model.addAttribute("storeModel", storeManager.findById(transport.getStoreId()));
//		
//		model.addAttribute("soldModel", soldManager.findById(transport.getSoldId()));
//		// sso前置校验
		return "searchPageProduceNo";
		
	}

}

package cn.com.taiji.sample.manager.medicine.produce;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.pub.ExcelReadRowHandler;
import cn.com.taiji.common.manager.pub.ExcelTemplateHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.pub.PoiExcelInfo;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.dict.IOStatus;
import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.manager.LoginHelper;
import cn.com.taiji.sample.repo.jpa.source.SourcePurchaseRepo;
import cn.com.taiji.sample.repo.jpa.source.SourceTransportRepo;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourcePurchase")
public class SourcePurchaseManagerImpl extends AbstractManager implements SourcePurchaseManager{
	@Autowired
	private SourcePurchaseRepo repo;
	@Autowired
	private SourceTransportRepo transportRepo;
	private final String password="11111111";

	@Override
	public Pagination queryPage(SourcePurchasePageRequest req) {
		return repo.page(req);
	}


	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public SourcePurchase findById(String id) {
		return repo.findById(id).get();
	}


	@Override
	public void save(@Valid SourcePurchase pageModel) {
		repo.save(pageModel);
	}


	@Override
	public void update(SourcePurchase entity) {
		// TODO Auto-generated method stub
		String id = entity.getId();
		SourcePurchase model = repo.findById(id).get();
		BeanUtils.copyProperties(entity, model);
		repo.save(model);
	}


	@Override
	public boolean checkFileType(InputStream inputStream, boolean isXlsx) throws IOException {

		PoiExcelInfo info = new PoiExcelInfo();
		info.setColSize(27);
		info.setExcelInput(inputStream);
		info.setSheetIndex(0);
		info.setFromRow(0);
		info.setToRow(1);
		info.setBreakOnRowNull(true);
		if(isXlsx)
			info.setXlsx(true);
		List<String> list = 
				ExcelTemplateHelper.readData(info, new ExcelReadRowHandler<String>() {
						@Override
						public String row2Model(int row, Map<Integer, Cell> rowData) {
									if (row == 0){
										if(rowData.get(0).getRowIndex()==0){
											rowData.get(26).setCellType(Cell.CELL_TYPE_STRING);
											
											return rowData.get(26).getStringCellValue();
										}
									}
									return "";
								}
							});
		if(list.size()!=0&&password.equals(list.get(0))){
			return true;
		}
		return false;
	}


	@Override
	public void importExcel(MultipartFile file, HttpServletRequest request, Map<String, IOStatus> iostate) {

		User user = (User)LoginHelper.getLoginUser(request);
		int i = 0;
		HttpSession session=request.getSession();
		
//		List<ExamOrderPlanModel> errorList = new ArrayList<>();
//		if(file == null) return "文件为空";
//		InputStream is = file.getInputStream();
//		PoiExcelInfo info = new PoiExcelInfo();
//		info.setColSize(9);
//		info.setExcelInput(is);
//		info.setSheetIndex(0);
//		info.setFromRow(1);
//		info.setToRow(-1);
//		info.setBreakOnRowNull(true);
//		info.setXlsx(true);
//		List<ExamOrderPlanModel> list = ExcelTemplateHelper.readData(info, this);//读取Excel中的数据
//		if(list.size()==0){//判断文件内容是否为null，标题除外
//			
//			iostate.put(user.getLoginName(), IOStatus.FILEEMPTY);
//			return "文件为空";
//		}
//	
//		for(ExamOrderPlanModel planModel : list){
//			//1预约受理日期
//			if(planModel.getAcceptDate()==null|| "".equals(planModel.getAcceptDate())){
//				planModel.setErrorInfo("预约受理日期未填写");
//				errorList.add(planModel);
//				continue;
//			}
//			//2受理时间
//			if(planModel.getTimeSrc()==null|| "".equals(planModel.getTimeSrc())){
//				planModel.setErrorInfo("受理时间未填写");
//				errorList.add(planModel);
//				continue;
//			}
//			//3受理点
//			if(planModel.getAcceptAddress()==null||"".equals(planModel.getAcceptAddress())){
//				planModel.setErrorInfo("受理点未填写");
//				errorList.add(planModel);
//				continue;
//			}
//			//4受理点代码
//			if(planModel.getAddressNo()==null||"".equals(planModel.getAddressNo())){
//				planModel.setErrorInfo("受理点代码未填写");
//				errorList.add(planModel);
//				continue;
//			}
//			//5受理量
//			if( planModel.getAcceptTotalNum()==null|| "".equals(planModel.getAcceptTotalNum())){
//				planModel.setErrorInfo("受理量未填写");
//				errorList.add(planModel);
//				continue;
//			}
////			//6受理种类
////			if( planModel.getBusinessType()==null||"".equals(planModel.getBusinessType())){
////				planModel.setErrorInfo("受理种类未填写");
////				errorList.add(planModel);
////				continue;
////			}
//			String addressNo=planModel.getAddressNo();
//			String acceptDate=planModel.getAcceptDate();
//			String timeSrc=planModel.getTimeSrc();
//			String acceptAddress=planModel.getAcceptAddress();
//			String acceptTotalNum=planModel.getAcceptTotalNum();
//			try{
//				Integer.valueOf(acceptDate);//判断acceptDate"受理日期"yyyyMMdd，是否可以转换为数字，yyyyMMdd格式必然可以转换为数字
//			}catch(Exception e){
//				planModel.setErrorInfo("您填写的受理日期"+acceptDate+"，必须符合yyyyMMdd的格式，如：20161026");
//				errorList.add(planModel);
//				continue;
//			}
//			if(acceptDate.length()!=8){
//				planModel.setErrorInfo("您填写的受理日期"+acceptDate+"，必须符合yyyyMMdd的格式，如：20161027");
//				errorList.add(planModel);
//				continue;
//			}
//			try{
//				Integer.valueOf(addressNo);//判断addressNo"受理点"是否可以转换为数字
//			}catch(Exception e){
//				planModel.setErrorInfo("您填写的受理点代码"+addressNo+"必须是两位的数字");
//				errorList.add(planModel);
//				continue;
//			}
//			if(addressNo.length()!=2){
//				planModel.setErrorInfo("您填写的受理点代码"+addressNo+"必须是两位数字");
//				errorList.add(planModel);
//				continue;
//			}
//			ExamSite examSiteTemp=siteRepo.findByAddressNo(addressNo);
//			if(examSiteTemp==null){
//				planModel.setErrorInfo("您填写的受理点代码"+addressNo+"不存在,与受理点"+acceptAddress+"不相匹配");
//				errorList.add(planModel);
//				continue;
//			}
//			if(!examSiteTemp.getAcceptAddress().equals(acceptAddress)){
//				planModel.setErrorInfo("您填写的受理点代码"+addressNo+",与受理点"+acceptAddress+"不相匹配");
//				errorList.add(planModel);
//				continue;
//			}
//			try{
//				Integer.valueOf(addressNo);//判断addressNo"受理点"是否可以转换为数字
//			}catch(Exception e){
//				planModel.setErrorInfo("您填写的受理点代码"+addressNo+"必须是两位的数字");
//				errorList.add(planModel);
//				continue;
//			}
//			try{
//				Integer.valueOf(acceptTotalNum);//判断acceptTotalNum"受理量"是否可以转换为数字
//			}catch(Exception e){
//				planModel.setErrorInfo("您填写的受理量"+acceptTotalNum+"必须是数字");
//				errorList.add(planModel);
//				continue;
//			}
//			
////			List<ExamOrderPlan>list01=repo.listByTimeSrc01(addressNo,acceptDate);//上午的、有效的、该addressNo的受理计划
////			List<ExamOrderPlan>list02=repo.listByTimeSrc02(addressNo,acceptDate);//下午的、有效的、该addressNo的受理计划
//			
//			List<ExamOrderPlan> listUnique=repo.unique(acceptDate, timeSrc, addressNo);
//			if(listUnique.size()!=0){
//				planModel.setErrorInfo(planModel.getAcceptDate()+(timeSrc.equals("01")?"上午":"下午")+"、"+planModel.getAcceptAddress()+" "+" 已有受理计划");
//				errorList.add(planModel);
//				continue;
//			}
////			if(list01.size()!=0 && list02.size()!=0){
////				planModel.setErrorInfo(planModel.getAcceptDate()+"、"+planModel.getAcceptAddress()+" "+"上午下午已经有受理计划");
////				errorList.add(planModel);
////				continue;
////			}
//			Date date=today.getTime();
//			String todayStr=sdf.format(date);
//			if(Integer.valueOf(todayStr)>Integer.valueOf(acceptDate)){
//				planModel.setErrorInfo("受理计划只能填写今天以后的");
//				errorList.add(planModel);
//				continue;
//			}
//			
//			ExamOrderPlan plan=new ExamOrderPlan();
//			plan.setAcceptDate(planModel.getAcceptDate());
//		
//			plan.setAcceptTotalNum(Integer.valueOf(planModel.getAcceptTotalNum()));
//			
//			ExamSite examSite=siteRepo.findByAddressNo(addressNo);
//		
//			plan.setExamSite(examSite);//根据addressNo,将受理计划examOrderPlan和examSite关联起来
//			plan.setTimeSrc(planModel.getTimeSrc());
//			plan.setValid(true);
//			
//			repo.save(plan);
//		}
//		
//		i=i+1;
//		session.setAttribute(user.getLoginName(), i);
//		try {
//			if(errorList.size()!=0){
//				iostate.put(user.getLoginName(), IOStatus.SUCERROR);
//			}else{
//				iostate.put(user.getLoginName(), IOStatus.SUCCESS);
//			}
//			exportErrorList(errorList,user);
//			return "文件内容有问题";
//		} catch (Exception e) {
//			e.printStackTrace();
//			iostate.put(user.getLoginName(), IOStatus.FAILED);
//		}
//		return "上传成功";
	}


	@Override
	public void createTask(SourcePurchase req, HttpServletRequest request) {
		String transportId = req.getTransportId();
		SourceTransport sourceTransport = transportRepo.findById(transportId).get();
		
		req.setPlantTaskId(sourceTransport.getPlantTaskId());
		req.setFarmerId(sourceTransport.getFarmerId());
		req.setSeedId(sourceTransport.getSeedId());
		req.setHarvestId(sourceTransport.getHarvestId());
		req.setProcessId(sourceTransport.getProcessId());
		req.setStoreId(sourceTransport.getStoreId());
		req.setSoldId(sourceTransport.getSoldId());
		req.setProcessPackageId(sourceTransport.getProcessPackageId());
		repo.save(req);
	}

	
}

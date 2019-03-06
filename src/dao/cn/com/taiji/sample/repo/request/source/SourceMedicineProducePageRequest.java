/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceMedicineProduce;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceMedicineProducePageRequest extends JpaPageableDataRequest<SourceMedicineProduce>
{
	private String materialName;//药材名称
	private String originalPlace;//产地
	private String medicineId;//药材批号
	private String standard;//规格
	private String leftNum;//库存数量(个)
	private String executeStandard;//*执行标准
	private String productionName;//*产品名称
	private String medicineCode;//饮片编码
	private String manufactureMethod;//炮制方法
	private String processStandard;//饮片加工规格
	private String unit;//计量单位
	private String produceId;//*产品批号
	private String amount;//领用量(kg)
	private LocalDateTime produceTime ;//*生产日期
	private Double productionNum;
	
	public SourceMedicineProducePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and materialName like :materialName", like(materialName));
		hql.append(" and medicineId=:medicineId", medicineId);
		hql.append(" and medicineCode=:medicineCode", medicineCode);
		hql.append(" and produceId=:produceId", produceId);
		return hql;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
	}
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(String leftNum) {
		this.leftNum = leftNum;
	}
	public String getExecuteStandard() {
		return executeStandard;
	}
	public void setExecuteStandard(String executeStandard) {
		this.executeStandard = executeStandard;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public String getManufactureMethod() {
		return manufactureMethod;
	}
	public void setManufactureMethod(String manufactureMethod) {
		this.manufactureMethod = manufactureMethod;
	}
	public String getProcessStandard() {
		return processStandard;
	}
	public void setProcessStandard(String processStandard) {
		this.processStandard = processStandard;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProduceId() {
		return produceId;
	}
	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public LocalDateTime getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(LocalDateTime produceTime) {
		this.produceTime = produceTime;
	}
	public Double getProductionNum() {
		return productionNum;
	}
	public void setProductionNum(Double productionNum) {
		this.productionNum = productionNum;
	}
}

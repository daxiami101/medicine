/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceProcessPackage;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceProcessPackagePageRequest extends JpaPageableDataRequest<SourceProcessPackage>
{
	private String plantTaskId;//种植任务id
	private String harvestId;//采收id
	private Calendar packageDate;//包装时间 	
	private String standard;//包装规格 	
	private Double weight;//包装重量 	
	private String packageMaterial;//包装材料 	
	private String name;//品名 	
	private String level;//等级 	
	private String place;//产地 	
	private Calendar harvestDate;//采收日期 	
	private String packageNo;//批次号 	
	private String produceCom;//生产单位 
	private String dataSource;//数据源
	private String medicineName;//药材名称
	public SourceProcessPackagePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc= true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and plantTaskId=:plantTaskId", plantTaskId);
		hql.append(" and harvestId=:harvestId", harvestId);
		hql.append(" and medicineName=:medicineName", medicineName);
		hql.append(" and dataSource=:dataSource", dataSource);
		
		return hql;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getPlantTaskId() {
		return plantTaskId;
	}
	public void setPlantTaskId(String plantTaskId) {
		this.plantTaskId = plantTaskId;
	}
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	public Calendar getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Calendar packageDate) {
		this.packageDate = packageDate;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getPackageMaterial() {
		return packageMaterial;
	}
	public void setPackageMaterial(String packageMaterial) {
		this.packageMaterial = packageMaterial;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Calendar getHarvestDate() {
		return harvestDate;
	}
	public void setHarvestDate(Calendar harvestDate) {
		this.harvestDate = harvestDate;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	public String getProduceCom() {
		return produceCom;
	}
	public void setProduceCom(String produceCom) {
		this.produceCom = produceCom;
	}
}

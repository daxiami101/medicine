/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.dict.source.ReproduceMaterial;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceSeedHandlePageRequest extends JpaPageableDataRequest<SourceSeedHandle>
{ 
	private String taskId;
	private String seedId;//种源信息
	private String taskNo;//种植批号	
	private String medicineName;//种植药材	
	private PlantMethod plantMethod;//种植方式	
	private LocalDateTime startPlantTime;//种植起始时间	 
	private LocalDateTime endPlantTime;//种植结束时间	 
	private Double area;//种植面积(亩)	
	private String originalPalce;//产地
	private String farmerId;//农户信息
	private String year;
	private String selectFarmerId;
	
	private ReproduceMaterial reproduceMaterial;//繁殖材料
	private String handleMethod;//处理方式
	private String handleNote;//处理说明
	private LocalDateTime handleTime;//处理时间
	
	private LocalDateTime transplantTime;//移栽时间
	private String transplantMethod;//播种/移栽方式
	private Double seedTotal;//播种量
	private Double transplateNum;//移栽量
	public SourceSeedHandlePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc=true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and taskNo=:taskNo", taskNo);
		hql.append(" and medicineName like:medicineName", like(medicineName));
		hql.append(" and plantMethod=:plantMethod", plantMethod);
		hql.append(" and originalPalce=:originalPalce", originalPalce);
		return hql;
	}
	public LocalDateTime getTransplantTime() {
		return transplantTime;
	}
	public void setTransplantTime(LocalDateTime transplantTime) {
		this.transplantTime = transplantTime;
	}
	public String getTransplantMethod() {
		return transplantMethod;
	}
	public void setTransplantMethod(String transplantMethod) {
		this.transplantMethod = transplantMethod;
	}
	public Double getSeedTotal() {
		return seedTotal;
	}
	public void setSeedTotal(Double seedTotal) {
		this.seedTotal = seedTotal;
	}
	public Double getTransplateNum() {
		return transplateNum;
	}
	public void setTransplateNum(Double transplateNum) {
		this.transplateNum = transplateNum;
	}
	public ReproduceMaterial getReproduceMaterial() {
		return reproduceMaterial;
	}
	public void setReproduceMaterial(ReproduceMaterial reproduceMaterial) {
		this.reproduceMaterial = reproduceMaterial;
	}
	public String getHandleMethod() {
		return handleMethod;
	}
	public void setHandleMethod(String handleMethod) {
		this.handleMethod = handleMethod;
	}
	public String getHandleNote() {
		return handleNote;
	}
	public void setHandleNote(String handleNote) {
		this.handleNote = handleNote;
	}
	public LocalDateTime getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(LocalDateTime handleTime) {
		this.handleTime = handleTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getSelectFarmerId() {
		return selectFarmerId;
	}
	public void setSelectFarmerId(String selectFarmerId) {
		this.selectFarmerId = selectFarmerId;
	}
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public PlantMethod getPlantMethod() {
		return plantMethod;
	}
	public void setPlantMethod(PlantMethod plantMethod) {
		this.plantMethod = plantMethod;
	}
	public LocalDateTime getStartPlantTime() {
		return startPlantTime;
	}
	public void setStartPlantTime(LocalDateTime startPlantTime) {
		this.startPlantTime = startPlantTime;
	}
	public LocalDateTime getEndPlantTime() {
		return endPlantTime;
	}
	public void setEndPlantTime(LocalDateTime endPlantTime) {
		this.endPlantTime = endPlantTime;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getOriginalPalce() {
		return originalPalce;
	}
	public void setOriginalPalce(String originalPalce) {
		this.originalPalce = originalPalce;
	}
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	
}

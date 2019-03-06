/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.source.SourceFarmer;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceFarmerPageRequest extends JpaPageableDataRequest<SourceFarmer>
{
//	@Size(min = 3, max = 16, message = "{userName.error}")
	private String farmerNo;//农户代码
	private FarmerType farmerType;//农户类型
	private String provinceId;//省
	private String village;//村
	private String medicineName;//	种植药材名称
	private String contractNum;//种植合同号
	private Double area;//面积（亩）
	private String dataSource;//数据源
	public SourceFarmerPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and farmerNo=:farmerNo", farmerNo);
		hql.append(" and farmerType=:farmerType", farmerType);
		hql.append(" and provinceId=:provinceId", provinceId);
		hql.append(" and village=:village", village);
		hql.append(" and medicineName  like:medicineName", like(medicineName));
		hql.append(" and contractNum  like:contractNum", like(contractNum));
		hql.append(" and medicineName  like:medicineName", like(medicineName));
		hql.append(" and dataSource  =:dataSource", like(dataSource));
		return hql;
	}
	
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getFarmerNo() {
		return farmerNo;
	}
	public void setFarmerNo(String farmerNo) {
		this.farmerNo = farmerNo;
	}
	public FarmerType getFarmerType() {
		return farmerType;
	}
	public void setFarmerType(FarmerType farmerType) {
		this.farmerType = farmerType;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
}

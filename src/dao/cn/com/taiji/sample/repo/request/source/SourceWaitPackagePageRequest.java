/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.PackageStatus;
import cn.com.taiji.sample.entity.source.SourceWaitPackage;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceWaitPackagePageRequest extends JpaPageableDataRequest<SourceWaitPackage>
{
	private String produceName;//产品名称
	private String originalPlace;//产地
	private String productionId;//产品批号
	private String comcode;//企业编码
	private String medicineCode;
	private PackageStatus packageStatus;
	public SourceWaitPackagePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and produceName like :produceName", like(produceName));
		hql.append(" and originalPlace=:originalPlace", originalPlace);
		hql.append(" and productionId=:productionId", productionId);
		hql.append(" and comcode=:comcode", comcode);
		hql.append(" and medicineCode=:medicineCode", medicineCode);
		hql.append(" and packageStatus=:packageStatus", packageStatus);
		return hql;
	}
	
	public PackageStatus getPackageStatus() {
		return packageStatus;
	}
	public void setPackageStatus(PackageStatus packageStatus) {
		this.packageStatus = packageStatus;
	}
	public String getProduceName() {
		return produceName;
	}
	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
	}
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public String getComcode() {
		return comcode;
	}
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
}

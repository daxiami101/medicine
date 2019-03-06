/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceProvince;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceProvincePageRequest extends JpaPageableDataRequest<SourceProvince>
{
	private String provinceId;
	private String provinceName;
	
	public SourceProvincePageRequest()
	{
//		this.orderBy = "namePy";
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and provinceId like :provinceId", like(provinceId));
		hql.append(" and provinceName=:provinceName", provinceName);
		return hql;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}

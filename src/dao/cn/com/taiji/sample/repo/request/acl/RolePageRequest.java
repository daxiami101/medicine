/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.Role;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午1:39:14<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class RolePageRequest extends JpaPageableDataRequest<Role>
{
	private String name;
	private String unitLikeCode;
	public RolePageRequest()
	{
		this.orderBy = "list";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUnitLikeCode() {
		return unitLikeCode;
	}

	public void setUnitLikeCode(String unitLikeCode) {
		this.unitLikeCode = unitLikeCode;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + Role.class.getName() + " a where 1=1 ");
		hql.append(" and name like :name", like(name));
		hql.append(" and exists(select b from Unit b where b.id=a.unit.id and b.code like :code)",rightLike(unitLikeCode));
		return hql;
	}

}

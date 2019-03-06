package cn.com.taiji.sample.manager.medicine;

import java.util.List;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceCity;
import cn.com.taiji.sample.repo.request.source.SourceCityPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceCityManager
{

	public Pagination queryPage(SourceCityPageRequest req);

	public List<SourceCity> listByProvinceId(String provinceId);


}

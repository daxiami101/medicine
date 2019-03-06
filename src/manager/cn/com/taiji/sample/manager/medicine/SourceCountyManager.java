package cn.com.taiji.sample.manager.medicine;

import java.util.List;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceCounty;
import cn.com.taiji.sample.repo.request.source.SourceCountyPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceCountyManager
{

	public Pagination queryPage(SourceCountyPageRequest req);

	public List<SourceCounty> listByProvinceId(String key);


}

package cn.com.taiji.sample.manager.medicine.produce;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.repo.request.source.SourceCheckPassPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceCheckPassManager
{
	public Pagination queryPage(SourceCheckPassPageRequest req);

	public Object findById(String id);

	public void delete(String id);


}

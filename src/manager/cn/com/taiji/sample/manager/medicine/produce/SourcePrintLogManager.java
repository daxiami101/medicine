package cn.com.taiji.sample.manager.medicine.produce;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.repo.request.source.SourcePrintLogPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourcePrintLogManager
{
	public Pagination queryPage(SourcePrintLogPageRequest req);

	public void delete(String id);

	public Object findById(String id);


}

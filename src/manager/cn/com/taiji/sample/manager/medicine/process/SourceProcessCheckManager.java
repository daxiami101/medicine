package cn.com.taiji.sample.manager.medicine.process;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.repo.request.source.SourceProcessPageRequest;

/**
 * 加工管理--检验
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceProcessCheckManager
{
	public Pagination queryPage(SourceProcessPageRequest req);


}

package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourceDiseaseCtrl;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.repo.request.source.SourceDiseaseCtrlPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceDiseaseCtrlManager
{
	public Pagination queryPage(SourceDiseaseCtrlPageRequest req);

	public void delete(String id);

	public SourceDiseaseCtrl findById(String id);

	public void createTask(SourceDiseaseCtrl req, HttpServletRequest request);

	public List<SourceDiseaseCtrl> listByTaskId(String plantTaskId);


}

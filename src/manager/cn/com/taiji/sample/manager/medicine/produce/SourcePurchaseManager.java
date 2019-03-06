package cn.com.taiji.sample.manager.medicine.produce;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.dict.IOStatus;
import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.repo.request.source.SourcePurchasePageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourcePurchaseManager
{
	public Pagination queryPage(SourcePurchasePageRequest req);

	public void delete(String id);

	public SourcePurchase findById(String id);

	public void save(@Valid SourcePurchase pageModel);

	public void update(SourcePurchase entity);

	public boolean checkFileType(InputStream inputStream, boolean isXlsx)throws IOException;

	public void importExcel(MultipartFile file, HttpServletRequest request, Map<String, IOStatus> iostate);

	public void createTask(SourcePurchase req, HttpServletRequest request);


}

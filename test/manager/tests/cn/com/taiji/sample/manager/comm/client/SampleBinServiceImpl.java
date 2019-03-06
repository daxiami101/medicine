/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.sample.manager.comm.client;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.net.http.binclient.AbstractBinCommManager;
import cn.com.taiji.common.manager.net.http.binclient.AbstractBinResponseHandler;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.manager.net.http.binclient.Response2Modelhandler;
import cn.com.taiji.common.model.file.FileProtocolRequest;
import cn.com.taiji.sample.model.comm.protocol.AbstractSampleRequest;
import cn.com.taiji.sample.model.comm.protocol.AbstractSampleResponse;
import cn.com.taiji.sample.model.comm.protocol.SampleCommRequest;
import cn.com.taiji.sample.model.comm.protocol.SampleCommResponse;
import cn.com.taiji.sample.model.comm.protocol.SampleServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午4:37:39<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SampleBinServiceImpl extends AbstractBinCommManager implements SampleBinService
{
	private String authStr = "zhimakaimen";

	@Autowired
	public SampleBinServiceImpl(@Value("#{commProperties.sampleServiceURL}") String sampleServiceURL)
	{
		super(sampleServiceURL);
	}

	@Override
	public SampleCommResponse commSample(SampleCommRequest request) throws IOException, ApiRequestException
	{
		String name = SampleServiceType.COMMSAMPLE + "_REQ_" + getTimeMillStr() + ".json";
		return binPost(name, request, SampleCommResponse.class);
	}

	@Override
	public <T> T binSample(FileProtocolRequest request, AbstractBinResponseHandler<T> handler)
			throws IOException, ApiRequestException
	{
		return super.filePost(request, handler);
//		return null;
	}

	private <T extends AbstractSampleResponse> T binPost(String filename, AbstractSampleRequest req, Class<T> clazz)
			throws IOException, ApiRequestException
	{
		FileProtocolRequest request = req.toRequest(false, filename, true).setAuthStr(authStr);
		return super.filePost(request, new Response2Modelhandler<>(clazz));
	}
}

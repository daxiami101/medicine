/*
 * Date: 2013年8月26日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager.comm.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.mycomm.manager.comm.sso.SsoTopicHandler;
import cn.com.taiji.mycomm.model.comm.protocol.sso.SsoLogoutTopic;
import cn.com.taiji.mycomm.model.comm.protocol.sso.SsoUserTopic;
import cn.com.taiji.mycomm.model.comm.protocol.sso.UserUnlinkTopic;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.manager.SessionTicketManager;
import cn.com.taiji.sample.manager.acl.SsoHelper;
import cn.com.taiji.sample.repo.jpa.UserRepo;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年8月26日 下午1:52:45<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SampleSsoTopicHandler extends AbstractManager implements SsoTopicHandler
{
	@Autowired
	private SessionTicketManager ticketManager;
	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional
	public void handleUserTopic(SsoUserTopic info)
	{
		logger.debug("handle user topic:{}", info);
		User user = userRepo.findByLoginName(info.getAppLoginName());
		if (user == null)
		{
			logger.warn("本地找不到登陆名为({})的用户", info.getAppLoginName());
			return;
		}
		SsoHelper.protocol2User(info, user);
		userRepo.save(user);
	}

	@Override
	public void handleLogoutTopic(SsoLogoutTopic logout)
	{
		logger.debug("remove ticketId:{}", logout.getTicketId());
		ticketManager.removeTicket(logout.getTicketId());
	}

	@Override
	public void handleUserUnlinkTopic(UserUnlinkTopic topic)
	{
		// TODO Auto-generated method stub
		logger.info("收到用户与应用取消关联的消息:{}", topic);
	}

}

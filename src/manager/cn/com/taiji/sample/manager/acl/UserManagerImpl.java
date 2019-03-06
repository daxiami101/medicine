package cn.com.taiji.sample.manager.acl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.pub.SecurityUtil;
import cn.com.taiji.common.pub.SecurityUtil.HashType;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.mycomm.manager.comm.sso.SsoClient;
import cn.com.taiji.mycomm.manager.comm.sso.SsoClientFactory;
import cn.com.taiji.mycomm.model.comm.protocol.sso.AddUserRequest;
import cn.com.taiji.mycomm.model.comm.protocol.sso.AddUserResType;
import cn.com.taiji.mycomm.model.comm.protocol.sso.AddUserResponse;
import cn.com.taiji.mycomm.model.comm.protocol.sso.AppUserExistRequest;
import cn.com.taiji.mycomm.model.comm.protocol.sso.BoolResponse;
import cn.com.taiji.mycomm.model.comm.protocol.sso.EditUserRequest;
import cn.com.taiji.mycomm.model.comm.protocol.sso.EditUserResType;
import cn.com.taiji.mycomm.model.comm.protocol.sso.EditUserResponse;
import cn.com.taiji.mycomm.model.comm.protocol.sso.LoginNameExistRequest;
import cn.com.taiji.mycomm.model.comm.protocol.sso.SsoLoginResponse;
import cn.com.taiji.sample.entity.Role;
import cn.com.taiji.sample.entity.Unit;
import cn.com.taiji.sample.entity.User;
import cn.com.taiji.sample.entity.User.SystemUser;
import cn.com.taiji.sample.entity.User.UserStatus;
import cn.com.taiji.sample.manager.comm.CommParamConfig;
import cn.com.taiji.sample.repo.jpa.RoleRepo;
import cn.com.taiji.sample.repo.jpa.UnitRepo;
import cn.com.taiji.sample.repo.jpa.UserRepo;
import cn.com.taiji.sample.repo.request.acl.UserPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2010-5-31 上午10:39:21<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("userManager")
public class UserManagerImpl extends AbstractManager implements UserManager
{
	@Autowired
	private UnitRepo unitRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private CommParamConfig paramConfig;

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public String add(User user, User loginUser) throws JsonManagerException
	{
		Role role = addValidate(user, loginUser);
		user.setRole(role);
		String rawPwd = user.getPasswd();
		user.setPasswd(user.getPasswd());
		userRepo.save(user);
		ssoAdd(user, rawPwd);// sso 更新放在最后，如果抛出异常，本地的修改也会回滚
		return user.getId();
	}

	private void ssoAdd(User user, String rawPwd) throws JsonManagerException
	{
		if (!paramConfig.isEnableSso()) return;
		try
		{
			SsoClient client = SsoClientFactory.getSsoClient();
			AddUserRequest request = new AddUserRequest();
			SsoHelper.user2Protocol(user, request);
			request.setLoginName(user.getLoginName());
			request.setPasswd(rawPwd);
			AddUserResponse res = client.addUser(request);
			if (res.getResType() != AddUserResType.SUCCESS) throw new JsonManagerException(res.getErrorInfo());
		}
		catch (IOException e)
		{
			logger.error("", e);
			throw new JsonManagerException("与SSO通信错误,请稍后重试.");
		}
	}

	private Role addValidate(User user, User loginUser) throws JsonManagerException
	{
		AssertUtil.notNull(user);
		user.validate();// 一般的多字段关联校验
		if (!hasText(user.getPasswd())) throw new MyViolationException("passwd", "不能为空");
		if (!hasText(user.getConfirm_pw())) throw new MyViolationException("confirm_pw", "不能为空");
		if (!user.getConfirm_pw().equals(user.getPasswd())) throw new MyViolationException("confirm_pw", "两次密码不一致");

		User tmp = userRepo.findByLoginName(user.getLoginName());
		if (tmp != null) throw new MyViolationException("loginName", "用户已经存在:" + user.getLoginName());
		Role role = roleRepo.findById(user.getRoleId()).orElse(null);
		if (role == null) throw new MyViolationException("roleId", "所选的角色不存在");
		Unit unit = unitRepo.findById(user.getUnit().getId()).orElse(null);
		// 校验选择的单位属于当前用户权限内的
		if (unit == null) throw new MyViolationException("unit\\.name", "所选的单位不存在");
		if (!unit.belongTo(loginUser.getUnit())) throw new JsonManagerException("单位超出范围");

		if (!paramConfig.isEnableSso()) return role;
		// sso 相关校验
		try
		{
			SsoClient client = SsoClientFactory.getSsoClient();
			AppUserExistRequest request = new AppUserExistRequest(user.getLoginName());
			BoolResponse res = client.appUserExist(request);
			if (res.isResult()) throw new MyViolationException("loginName", "用户已经在SSO中存在.");
			LoginNameExistRequest ler = new LoginNameExistRequest(user.getLoginName());
			res = client.loginNameExist(ler);
			if (res.isResult()) throw new MyViolationException("loginName", "登录名在SSO中已经被使用.");
		}
		catch (IOException e)
		{
			logger.error("", e);
			throw new JsonManagerException("与SSO通信错误,请稍后重试");
		}
		return role;
	}

	@Override
	public User findById(String id)
	{
		User user = userRepo.findById(id).orElse(null);
		if (user == null) return null;
		if (user.getRole() != null) user.setRoleId(user.getRole().getId());
		return user;
	}

	@Override
	@Transactional
	public User saveOrUpdate(SsoLoginResponse loginRes)
	{
		User user = userRepo.findByLoginName(loginRes.getAppLoginName());
		Role role = user == null ? roleManager.getDefaultRole() : user.getRole();
		String pwd = user == null ? SecurityUtil.encryptStr(User.DEFAULT_PWD, HashType.SHA_512, true)
				: user.getPasswd();
		if (user == null)
		{
			user = new User();
			user.setId(loginRes.getId());
		}
		user.setPasswd(pwd);
		user.setRole(role);
		SsoHelper.protocol2User(loginRes, user);
		userRepo.save(user);
		return user;
	}

	@Override
	public User findByLoginName(String loginName)
	{
		return userRepo.findByLoginName(loginName);
	}

	@Override
	public Pagination queryPage(UserPageRequest req, User user)
	{
		req.setUnitLikeCode(user.getUnitLikeCode());
		return userRepo.page(req);
	}

	@Override
	public LargePagination<User> queryLargePage(UserPageRequest req, User user)
	{
		req.setUnitLikeCode(user.getUnitLikeCode());
		return userRepo.largePage(req);
	}
	
	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public User update(User user, User loginUser) throws JsonManagerException
	{
		User po = updateValidate(user, loginUser);
		BeanTools.copyProperties(user, po, new String[] { "id", "loginName", "role", "passwd", "status" });
		userRepo.save(po);
		ssoUpdate(user);
		return po;
	}

	private void ssoUpdate(User user) throws JsonManagerException
	{
		if (!paramConfig.isEnableSso()) return;
		try
		{
			SsoClient client = SsoClientFactory.getSsoClient();
			EditUserRequest request = new EditUserRequest();
			SsoHelper.user2Protocol(user, request);
			EditUserResponse res = client.editUser(request);
			if (res.getResType() != EditUserResType.SUCCESS) throw new JsonManagerException(res.getErrorInfo());
		}
		catch (IOException e)
		{
			logger.error("", e);
			throw new JsonManagerException("与SSO通信错误,请稍后重试.");
		}
	}

	private User updateValidate(User user, User loginUser) throws JsonManagerException
	{
		user.validate();// 一般的多字段关联校验
		User po = userRepo.findById(user.getId()).orElse(null);
		if (po == null) throw new JsonManagerException("用户不存在,id:" + user.getId());
		String roleId = user.getRoleId();
		if (paramConfig.isEnableSso())
		{
			try
			{
				SsoClient client = SsoClientFactory.getSsoClient();
				AppUserExistRequest request = new AppUserExistRequest(user.getLoginName());
				BoolResponse res = client.appUserExist(request);
				if (!res.isResult()) throw new JsonManagerException("用户在SSO中不存在.");
			}
			catch (IOException e)
			{
				logger.error("", e);
				throw new JsonManagerException("与SSO通信错误,请稍后重试.");
			}
		}
		if (!hasText(roleId)) return po;
		if (SystemUser.isSystemUser(po.getLoginName()) && !roleId.equals(po.getRole().getId()))
			throw new MyViolationException("roleId", "不能修改内置用户的角色:" + po.getLoginName());
		Role role = roleRepo.findById(roleId).orElse(null);
		if (role == null)
			logger.warn("角色不存在:{}", roleId);
		else
			po.setRole(role);
		Unit unit = unitRepo.findById(user.getUnit().getId()).orElse(null);
		if (unit == null) new MyViolationException("unit\\.name", "所选的单位不存在");
		if (!unit.belongTo(loginUser.getUnit())) throw new JsonManagerException("单位超出范围");

		return po;
	}

	@Override
	@Transactional
	public User updateStatus(String id, UserStatus status) throws ManagerException
	{
		AssertUtil.notNull(status);
		User user = userRepo.findById(id).orElse(null);
		if (user == null) throw new ManagerException("用户不存在,id:" + id);
		if (SystemUser.isSystemUser(user.getLoginName()))
			throw new ManagerException("不能修改内置用户的状态:" + user.getLoginName());
		user.setStatus(status);
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public User modPasswd(String passwd, String uid) throws ManagerException
	{
		Optional<User> user = userRepo.findById(uid);
		if (!user.isPresent()) throw new ManagerException("用户不存在");
		// user.setPasswd(SecurityUtil.encryptStr(passwd, HashType.SHA_512, true));
		user.get().setPasswd(passwd);
		return userRepo.save(user.get());
	}
}

package com.tedu.config;

import com.tedu.dao.SysMenuDao;
import com.tedu.dao.SysUserDao;
import com.tedu.entity.SysUser;
import com.tedu.util.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

    private SysUserDao sysUserDao;

    private SysMenuDao sysMenuDao;

    public ShiroUserRealm(SysUserDao sysUserDao, SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
        this.sysUserDao = sysUserDao;
    }

    /**
     * 设置凭证匹配器(与用户添加操作使用相同的加密算法)
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 认证
     * 通过此方法完成认证数据的获取及封装,系统
     * 底层会将认证数据传递认证管理器，由认证
     * 管理器完成认证操作。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String username = token1.getUsername();
        // 从数据库获取用户信息
        SysUser user = sysUserDao.findObjectByColumn( SysUser.USERNAME, username);
        // 验证用户是否存在
        if (user == null) throw new UnknownAccountException();
        // 验证用户是否被禁用
        if (user.getValid() == null || user.getValid() != 1) throw new LockedAccountException();
        // 封装信息
        ByteSource byteSource = ByteSource.Util.bytes(user.getSalt());

        return new SimpleAuthenticationInfo(
                user, // 身份
                user.getPassword(), // 已加密的密码
                byteSource, // 盐值
                getName());// realm名字
    }

    /**
     * 授权
     *
     * @param principals 已认证信息
     * @return 权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取登录用户
        SysUser sysUser = ShiroUtil.getUser();
        // 通过用户角色id 获取菜单权限信息
        List<String> permissionByUserId = sysUserDao.findPermissionsByUserId(sysUser.getId());
        if (permissionByUserId == null || permissionByUserId.size() == 0) throw new AuthorizationException();
        Set<String> permissionString = new HashSet<>();
        permissionByUserId.stream().filter((per) -> per != null && per.length() > 1).forEach(permissionString::add);
        // 将权限列表添加对象
        simpleAuthorizationInfo.setStringPermissions(permissionString);
        return simpleAuthorizationInfo;
    }

}

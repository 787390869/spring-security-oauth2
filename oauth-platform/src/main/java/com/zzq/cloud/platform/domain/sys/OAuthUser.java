package com.zzq.cloud.platform.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author ZZQ
 * @date 2021/1/25 17:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuthUser implements UserDetails {

    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 用户账户 */
    private String username;

    /** 密码 */
    private String password;

    /** 用户手机号 */
    private String phone;

    /** 用户昵称 */
    private String nickname;

    /** 0-未知 1-男 2-女 */
    private Integer sex;

    /** 头像地址 */
    private String avator;

    /** 邮箱 */
    private String email;

    /** 启用状态 0-未启用 1-已启用 */
    private Integer state;

    /** 角色列表 */
    private Set<String> roles;

    /** 权限列表 */
    private Set<String> permissions;

    /** 授权信息 */
    private Collection<GrantedAuthority> auth;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

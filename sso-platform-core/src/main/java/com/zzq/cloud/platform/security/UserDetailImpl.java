package com.zzq.cloud.platform.security;

import com.zzq.cloud.platform.domain.sys.OAuthUser;
import com.zzq.cloud.platform.domain.sys.SysPermission;
import com.zzq.cloud.platform.domain.sys.SysRole;
import com.zzq.cloud.platform.mapper.sys.SysPermissionMapper;
import com.zzq.cloud.platform.service.sys.IOAuthUserBuilder;
import com.zzq.cloud.platform.util.ServletUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZZQ
 * @date 2021/1/25 17:12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailImpl implements UserDetailsService {

    private final SysPermissionMapper permissionMapper;
    private final IOAuthUserBuilder platformOAuthUserBuilder;

    @Override
    public UserDetails loadUserByUsername(String buildInfo) throws UsernameNotFoundException {
        HttpServletRequest request = ServletUtil.getRequest();
        String platform = Optional.ofNullable(request.getHeader("platform")).orElse("");
        OAuthUser user = new OAuthUser();

        switch (platform) {
            case "APP": /*user = appUserDetailsBuilder.build(buildInfo);*/ break;
            default: user = platformOAuthUserBuilder.build(buildInfo); break;
        }

        /** 超管赋予所有权限, 所有酒店 */
        if (user.getRoles().contains(SysRole.SUPER_ROLE)) {
            Set<String> allPermission = permissionMapper.selectList(null)
                    .stream().map(SysPermission::getPerm).collect(Collectors.toSet());
            user.setPermissions(allPermission);
            //user.setHotels(allHotel);
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        user.getPermissions().forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission));
        });
        user.setAuth(authorities);

        return user;
    }
}

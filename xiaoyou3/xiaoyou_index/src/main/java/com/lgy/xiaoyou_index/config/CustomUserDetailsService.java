package com.lgy.xiaoyou_index.config;

import com.lgy.tools.entity.TbRole;

import com.lgy.tools.entity.TbStu;
import com.lgy.tools.entity.TbUserRole;
import com.lgy.xiaoyou_index.service.ITbRoleService;
import com.lgy.xiaoyou_index.service.ITbStuService;
import com.lgy.xiaoyou_index.service.ITbUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ITbStuService stuService;

    @Autowired
    private ITbRoleService roleService;

    @Autowired
    private ITbUserRoleService userRoleService;

    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        TbStu stu=stuService.getByUserName(username);
        if(stu==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        session.setAttribute("tbStu",stu);
        List<TbUserRole> userRoles = userRoleService.listByUserId(stu.getUserId());
        for (TbUserRole userRole : userRoles) {
            TbRole role = roleService.getById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(stu.getUsername(),stu.getPassword(),authorities);
    }
}

package com.ys.appler.config.auth;


import com.ys.appler.dto.MemberDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private MemberDto memberDto;

    public PrincipalDetails(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    //권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Collection<GrantedAuthority> collect = new ArrayList<>();
       collect.add(new GrantedAuthority() {
           @Override
           public String getAuthority() {
               return memberDto.getAuthority();
           }
       });

        return collect;
    }

    @Override
    public String getPassword() {

        return memberDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDto.getUserid();
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

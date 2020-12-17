package com.ys.appler.config.auth;


import com.ys.appler.dto.MemberDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private MemberDto memberDto;
    private Map<String, Object> attributes;

    //일반 폼 로그인
    public PrincipalDetails(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    //Oauth 로그인
    public PrincipalDetails(MemberDto memberDto, Map<String, Object> attributes) {
        this.memberDto = memberDto;
        this.attributes = attributes;
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
        return memberDto.getNickname();
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

    @Override
    public Map<String, Object> getAttributes() {


        return attributes;
    }


    @Override
    public String getName() {
        return memberDto.getName();
    }
}

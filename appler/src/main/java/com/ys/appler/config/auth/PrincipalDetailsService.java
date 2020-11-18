package com.ys.appler.config.auth;

import com.ys.appler.dto.MemberDto;
import com.ys.appler.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
System.out.println("username : "+ username);
        MemberDto member = memberService.findByusernameService(username);
        if(member != null){

            return new PrincipalDetails(member);
        }

        return null;
    }
}

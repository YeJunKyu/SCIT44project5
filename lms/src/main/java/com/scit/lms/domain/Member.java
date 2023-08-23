package com.scit.lms.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails {
    String memberid;
    String memberpw;
    String membername;
    String birthday;
    String phone;
    String gender;
    String email;
    String address;
    String note;
    String rolename;
    boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.memberpw;
    }

    @Override
    public String getUsername() {
        return this.memberid;
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

    public String getGender(){
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}

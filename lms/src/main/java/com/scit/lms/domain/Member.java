package com.scit.lms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
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
    String memberphoto;
    String photopath;
//    String originalfile;
//    String savedfile;

    boolean enabled;
    int count;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rolename)); // rolename 예: "ROLE_ADMIN"
        return authorities;
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

    public String getMembername(){
        return membername;
    }
    public void setMembername(String membername){
        this.membername = membername;
    }


//    public String getOriginalfile() {
//        return originalfile;
//    }
//
//    public void setOriginalfile(String originalfile) {
//        this.originalfile = originalfile;
//    }
//
//    public String getSavedfile() {
//        return savedfile;
//    }
//
//    public void setSavedfile(String savedfile) {
//        this.savedfile = savedfile;
//    }

}

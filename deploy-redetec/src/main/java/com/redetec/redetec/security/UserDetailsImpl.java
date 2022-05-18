package com.redetec.redetec.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.redetec.redetec.model.Usuario;



public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;


	public UserDetailsImpl(Usuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}

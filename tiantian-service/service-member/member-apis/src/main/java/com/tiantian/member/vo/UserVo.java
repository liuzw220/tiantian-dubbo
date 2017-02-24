package com.tiantian.member.vo;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserVo extends BaseVo {
  
	private static final long serialVersionUID = -2511654751699601095L;
	private Long id;
    //message: 错误提示
    @Length(min = 6, max = 15, message = "用户名的长度为6~15.")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 15, message = "密码的长度为6~15.")
    private String password;
    @Length(min = 11, max = 11, message = "手机号的长度为11.")
    private String phone;
    @Email(message = "Email不符合规则!")
    private String email;
    private Date created;
    private Date updated;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", email=" + email
				+ ", created=" + created + ", updated=" + updated + "]";
	}
    
    

}

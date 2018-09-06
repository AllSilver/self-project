package org.zerock.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



public class MDto {

	@NotEmpty 
	@Size(min=4, max=12)  // 유효성 체크 Annotation
	private String userId;
	@NotEmpty 
	@Size(min=4, max=12)
	private String userPw;
	@NotEmpty
	private String userName;
	@Email
	private String userEmail;
	Timestamp userRegdate;
	
	
	private boolean useCookie;
    
    public boolean isUseCookie() {
        return useCookie;
    }
     
    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

  
	public MDto(String userId, String userPw) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.userPw = userPw;
	
	}
	
	public MDto() {
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Timestamp getUserRegdate() {
		return (Timestamp) userRegdate;
	}
	public void setUserRegdate(Timestamp userRegdate) {
		this.userRegdate = userRegdate;
	}
	public Date getUserUpdatedate() {
		return userUpdatedate;
	}
	public void setUserUpdatedate(Date userUpdatedate) {
		this.userUpdatedate = userUpdatedate;
	}
	private Date userUpdatedate;
	
	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userPw=" + userPw + ",userName" + userName +",userEmail="
				+userEmail+",userRegdate="+userRegdate+",userUpdatedate="+userUpdatedate + "]";
	}


}

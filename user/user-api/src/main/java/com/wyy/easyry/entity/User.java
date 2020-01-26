package com.wyy.easyry.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String phone;

    private String email;

    private Long openId;

    private Long unionId;

    private Long portraitId;

    private String jobTitle;

    private String address;

    private Long status;

    private Date createDate;

    private Date modifiedDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", openId=" + openId +
                ", unionId=" + unionId +
                ", portraitId=" + portraitId +
                ", jobTitle='" + jobTitle + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getOpenId() {
        return openId;
    }

    public User setOpenId(Long openId) {
        this.openId = openId;
        return this;
    }

    public Long getUnionId() {
        return unionId;
    }

    public User setUnionId(Long unionId) {
        this.unionId = unionId;
        return this;
    }

    public Long getPortraitId() {
        return portraitId;
    }

    public User setPortraitId(Long portraitId) {
        this.portraitId = portraitId;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public User setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getStatus() {
        return status;
    }

    public User setStatus(Long status) {
        this.status = status;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public User setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public User setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }
}

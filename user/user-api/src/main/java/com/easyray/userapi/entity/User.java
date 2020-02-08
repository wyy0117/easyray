package com.easyray.userapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import java.util.Date;

import static com.wyy.actable.constants.MySqlDataType.*;

/**
 * <p>
 *
 * </p>
 *
 * @author easyray
 * @since 2020-01-26
 */
@TableName("sys_user")
@Table(name = "sys_user")
public class User extends BaseEntity<Long> {

    @Column(name = "username", type = VARCHAR, length = 20, nullable = false, comment = "用户的登录名")
    private String username;

    @Column(name = "password", type = VARCHAR, length = 75, nullable = false)
    private String password;

    @Column(name = "phone", type = VARCHAR, length = 10)
    private String phone;

    @Column(name = "email", type = VARCHAR, length = 75)
    private String email;

    @Column(name = "open_id", type = VARCHAR, length = 75)
    private String openId;

    @Column(name = "union_id", type = VARCHAR, length = 75)
    private Long unionId;

    @Column(name = "portrait_id", type = BIGINT, length = 20)
    private Long portraitId;

    @Column(name = "job_title", type = VARCHAR, length = 75)
    private String jobTitle;

    @Column(name = "address", type = VARCHAR, length = 75)
    private String address;
    /**
     * 0：新注册未激活，1：已激活可正常使用，2：被锁定，暂时无法使用，3：已删除
     */
    @Column(name = "status", type = INT, length = 1, defaultValue = "0", comment = "账号状态")
    private Long status;

    @Column(name = "birthday", type = DATETIME)
    private Date birthday = new Date(0);

    public User(Long id) {
        super(id);
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", openId='" + openId + '\'' +
                ", unionId=" + unionId +
                ", portraitId=" + portraitId +
                ", jobTitle='" + jobTitle + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", birthday=" + birthday +
                "} " + super.toString();
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

    public String getOpenId() {
        return openId;
    }

    public User setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}

package com.panghu.entity;

import com.example.entity.BaseUUIDEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.entity
 * @ClassName: User
 * @Author: wxy
 * @Description: 用户测试表
 * @Date: 2020/5/3 15:22
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "sys_user")
@EntityListeners(AuditingEntityListener.class)
public class SysUser extends BaseUUIDEntity {
    /**
     * 登陆帐户
     */
    @Column(name = "account")
    private String account;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 用户类型(1普通用户2管理员3系统用户)
     */
    @Column(name = "user_type")
    private String userType;
    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 姓名拼音
     */
    @Column(name = "name_pinyin")
    private String namePinyin;
    /**
     * 性别(0:未知;1:男;2:女)
     */
    @Column(name = "sex")
    private Integer sex;
    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;
    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 身份证号码
     */
    @Column(name = "id_card")
    private String idCard;
    /**
     * 微信
     */
    @Column(name = "wei_xin")
    private String weiXin;
    /**
     * 微博
     */
    @Column(name = "wei_bo")
    private String weiBo;
    /**
     * QQ
     */
    @Column(name = "qq")
    private String qq;
    /**
     * 出生日期
     */
    @Column(name = "birth_day")
    private Date birthDay;
    /**
     * 部门编号
     */
    @Column(name = "dept_id")
    private Long deptId;
    /**
     * 职位
     */
    @Column(name = "position")
    private String position;
    /**
     * 详细地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 工号
     */
    @Column(name = "staff_no")
    private String staffNo;

    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "user_type_text")
    private String userTypeText;

    @Column(name = "salt")
    private String salt;


    @ElementCollection(fetch = FetchType.EAGER )
    @CollectionTable(name="sys_user_role")
    @MapKeyJoinColumn(name="role_id")
    private List<SysRole> roles;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getWeiBo() {
        return weiBo;
    }

    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserTypeText() {
        return userTypeText;
    }

    public void setUserTypeText(String userTypeText) {
        this.userTypeText = userTypeText;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}

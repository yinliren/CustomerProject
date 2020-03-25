package com.customer.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("serial")
@TableName("customer_table")
public class Customer extends Model<Customer> {
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;
    @TableField(value = "customer_name")
    private String customerName;
    @TableField(value = "gender")
    private String gender;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "addr")
    private String addr;

    public Customer(Integer customerId, String customerName, String gender, String phone, String addr) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = gender;
        this.phone = phone;
        this.addr = addr;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customerId +
                ", customer_name='" + customerName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}

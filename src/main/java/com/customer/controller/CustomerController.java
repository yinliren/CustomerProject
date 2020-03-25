package com.customer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.customer.entity.Customer;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param customer 查询实体
     * @return All Data Entries
     */
    @GetMapping
    @CrossOrigin
    public R<IPage<Customer>> selectAll(Page<Customer> page, Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>(customer);
        return R.ok (this.customerService.page(page, queryWrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return One data entry
     */
    @GetMapping("{id}")
    @CrossOrigin
    public R<Customer> selectOne(@PathVariable Serializable id) {
        return R.ok(this.customerService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param customer 实体对象
     * @return Add Result
     */
    @PostMapping
    @CrossOrigin
    public R<Integer> insert(@RequestBody Customer customer) {
        boolean rs = this.customerService.save(customer);
        return R.ok(rs?customer.getCustomerId():0);
    }

    /**
     * 修改数据
     *
     * @param customer 实体对象
     * @return Edit Result
     */
    @PutMapping
    @CrossOrigin
    public R<Integer>  update(@RequestBody Customer customer) {
        boolean rs = this.customerService.updateById(customer);
        return R.ok(customer.getCustomerId());
    }

    /**
     * Delete Data
     *
     * @param idList 主键结合
     * @return Delete Result
     */
    @DeleteMapping
    @CrossOrigin
    public R<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.customerService.removeByIds(idList));
    }
}

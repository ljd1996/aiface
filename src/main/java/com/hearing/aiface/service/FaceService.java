package com.hearing.aiface.service;

import com.hearing.aiface.bean.Admin;
import com.hearing.aiface.bean.AdminExample;
import com.hearing.aiface.dao.AdminMapper;
import com.hearing.aiface.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by hearing on 18-10-17
 */
@Service
public class FaceService {

    @Autowired
    private AdminMapper adminMapper;

    public Msg login(Admin admin){
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andNameEqualTo(admin.getName());
        criteria.andPasswordEqualTo(admin.getPassword());
        List<Admin> users = adminMapper.selectByExample(adminExample);
        if (users.size() == 0){
            //无用户信息
            return Msg.fail();
        }

        return Msg.success();
    }
}

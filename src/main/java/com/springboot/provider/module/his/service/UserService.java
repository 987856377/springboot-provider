package com.springboot.provider.module.his.service;

import com.springboot.provider.module.his.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-12-10
 */
public interface UserService extends IService<User> {
    List<User> getAllUser();

    User getByUserId(Long id);
}

package edu.zafu.user_center_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zafu.user_center_backend.model.PO.User;
import edu.zafu.user_center_backend.service.UserService;
import edu.zafu.user_center_backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-09-05 19:57:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}





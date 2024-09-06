package edu.zafu.user_center_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zafu.user_center_backend.mapper.UserMapper;
import edu.zafu.user_center_backend.model.PO.User;
import edu.zafu.user_center_backend.model.VO.UserVO;
import edu.zafu.user_center_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ColaBlack
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-09-05 19:57:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public UserVO userRegister(String userAccount, String userPassword, String checkPassword) {
        return null;
    }

    @Override
    public UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        return false;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean isAdmin(User user) {
        return false;
    }

    @Override
    public UserVO getUserVO(User user) {
        return null;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        return List.of();
    }
}





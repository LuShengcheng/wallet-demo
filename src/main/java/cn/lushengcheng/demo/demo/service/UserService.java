package cn.lushengcheng.demo.demo.service;

import cn.lushengcheng.demo.demo.dto.request.UserRequestBody;
import cn.lushengcheng.demo.demo.enums.BusinessError;
import cn.lushengcheng.demo.demo.exception.BusinessException;
import cn.lushengcheng.demo.demo.model.Account;
import cn.lushengcheng.demo.demo.model.User;
import cn.lushengcheng.demo.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;

    public User saveOrUpdate(UserRequestBody userRequestBody) {

        if (userRequestBody.getUserId() == null) {
            log.info("----------------------新增用户----------------------");
            Account save = Account.builder()
                    .balanceFee(new BigDecimal("0.0"))
                    .build();
            User user = User.builder()
                    .name(userRequestBody.getUsername())
                    .account(save)
                    .build();
            return userRepository.save(user);
        } else {
            log.info("----------------------更新用户----------------------");
            User user = userRepository
                    .findById(userRequestBody.getUserId())
                    .orElseThrow(() -> new BusinessException(BusinessError.USER_NOT_EXIST));
            user.setName(userRequestBody.getUsername());
            return userRepository.save(user);
        }
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(BusinessError.USER_NOT_EXIST);
        });
    }

}

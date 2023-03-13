package cn.lushengcheng.demo.demo.service;

import cn.lushengcheng.demo.demo.dto.request.UserRequestBody;
import cn.lushengcheng.demo.demo.enums.BusinessError;
import cn.lushengcheng.demo.demo.exception.BusinessException;
import cn.lushengcheng.demo.demo.model.Account;
import cn.lushengcheng.demo.demo.model.User;
import cn.lushengcheng.demo.demo.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lushengcheng
 * @version 1.0.0
 */
@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Account findAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(BusinessError.ACCOUNT_NOT_EXIST));
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional(rollbackOn = Exception.class)
    public Account findAccountByName(UserRequestBody userRequestBody) {
        User byUsername = userService.findById(userRequestBody.getUserId());
        return accountRepository.findById(byUsername.getAccount().getAccountId()).orElseThrow(() -> new IllegalArgumentException("账户不存在"));

    }

    @Transactional(rollbackOn = Exception.class)
    public void save(Account account) {
        accountRepository.save(account);
    }
}

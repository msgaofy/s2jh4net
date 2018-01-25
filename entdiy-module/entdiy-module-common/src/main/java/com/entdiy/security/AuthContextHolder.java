/**
 * Copyright (c) 2012
 */
package com.entdiy.security;

import com.entdiy.auth.entity.Account;
import com.entdiy.auth.service.AccountService;
import com.entdiy.core.context.SpringContextHolder;
import com.entdiy.core.security.AuthUserDetails;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 以ThreadLocal方式实现Web端登录信息传递到业务层的存取
 */
public class AuthContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(AuthContextHolder.class);

    /**
     * 基于Spring Security获取用户认证信息
     */
    public static DefaultAuthUserDetails getDefaultAuthUserDetails() {
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }
        if (subject == null) {
            return null;
        }
        Object principal = null;
        try {
            principal = subject.getPrincipal();
        } catch (InvalidSessionException e) {
            //如果是没有有效的Shiro Session则直接返回null，避免在后台处理时相关审计处理逻辑代码异常
            logger.trace(e.getMessage());
        }
        if (principal == null) {
            return null;
        }
        return (DefaultAuthUserDetails) principal;
    }


    /**
     * 获取当前登录用户Account对象
     */
    public static Account findAuthAccount() {
        AuthUserDetails authUserDetails = AuthContextHolder.getDefaultAuthUserDetails();
        if (authUserDetails == null) {
            return null;
        }
        AccountService accountService = SpringContextHolder.getBean(AccountService.class);
        return accountService.findOne(authUserDetails.getAccountId());
    }

    /**
     * 返回当前登录Account对象，并且校验不能为空（必须登录访问）
     *
     * @return
     */
    public static Account findRequiredAuthAccount() {
        Account account = findAuthAccount();
        Assert.notNull(account, "Login required");
        return account;
    }
}
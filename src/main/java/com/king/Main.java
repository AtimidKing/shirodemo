package com.king;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by king on 2017/9/15.
 */
public class Main {
    private static final transient Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        log.info("My First Apache Shiro Application");
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        log.info(currentUser.toString());
//        System.out.println("输入用户名：");
//        String name=in.next();
        String name="yang";
//        System.out.println("输入密码");
//        String password = in.next();
        String password ="123456";
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(name, password);
        log.info("登录否" +
                (currentUser.isAuthenticated() ? "true" : "false"));
        currentUser.login(usernamePasswordToken);
        log.info("用户名:"+ currentUser.getPrincipal());
        log.info("登录否" +
                (currentUser.isAuthenticated() ? "true" : "false"));
        log.info("role:admin:"+currentUser.hasRole("admin"));
        log.info("permission:delete:" +
                (currentUser.isPermitted("delete")?"true":"false"));
        log.info("permission:create:" +
                (currentUser.isPermitted("create")?"true":"false"));
        currentUser.checkPermission("delete");
        System.exit(0);

    }
}

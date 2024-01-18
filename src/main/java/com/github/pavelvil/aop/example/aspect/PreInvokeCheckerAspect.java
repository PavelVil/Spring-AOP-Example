package com.github.pavelvil.aop.example.aspect;

import com.github.pavelvil.aop.example.annotation.PreInvoke;
import com.github.pavelvil.aop.example.exception.ApplicationException;
import com.github.pavelvil.aop.example.model.RoleType;
import com.github.pavelvil.aop.example.utils.UserContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(2)
public class PreInvokeCheckerAspect {

    private static final Map<String, List<RoleType>> USERS = new HashMap<>();

    static {
        USERS.put("admin", List.of(RoleType.ADMIN, RoleType.USER));
        USERS.put("user", List.of(RoleType.USER));
    }

    @Pointcut("@annotation(preInvoke)")
    public void checkRolePointcut(PreInvoke preInvoke) {}

    @Before("checkRolePointcut(preInvoke)")
    public void before(PreInvoke preInvoke) {
        String currentUser = UserContext.getUsername();

        if (!USERS.containsKey(currentUser)) {
            throw new ApplicationException("Пользователь не найден: " + currentUser);
        }

        var roles = Arrays.stream(preInvoke.roles()).toList();
        var userRoles = USERS.get(currentUser);

        if (roles.stream().noneMatch(userRoles::contains)) {
            throw new ApplicationException("Доступ запрещен! Роли: " + userRoles);
        }
    }

}

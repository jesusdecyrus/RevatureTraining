package com.revature.aspects;

import com.revature.controllers.UserController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Advice that checks for authentication
 * @author Cyrus De Jesus
 */
@Aspect
@Component
public class AuthenticationAspect {

    /**
     * Advice that checks if the user is logged in before calling methods
     */
    @Before("execution(* com.revature.controllers.UserController.*(..)) " +
            "&& !execution(* com.revature.controllers.UserController.signUpUser(..))" +
            "&& !execution(* com.revature.controllers.UserController.loginUser(..))" +
            "|| execution(* com.revature.controllers.ReimbursementController.*(..))")
    public void checkLogin(){
        if(UserController.session == null){
            throw new IllegalAccessError("You Must Be Logged In!");
        }
    }

    /**
     * Advice that checks for manager privileges before calling methods with @ManagerOnly
     */
    @Before("@annotation(com.revature.aspects.ManagerOnly)")
    public void checkManager() {
        if (!UserController.session.getAttribute("role").equals("Manager")) {
            throw new IllegalAccessError("You Must Be A Manager!");
        }
    }

}

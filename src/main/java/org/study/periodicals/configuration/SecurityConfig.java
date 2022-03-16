package org.study.periodicals.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Configuration
public class SecurityConfig {

    Map<Integer, Set<Pattern>> rolesToUrls = new HashMap<>();
    Pattern p1 = Pattern.compile("/adminPage/*");
    Pattern p2 = Pattern.compile("/personal/*");

    public Map<Integer, Set<Pattern>> getRolesToUrls() {

        return rolesToUrls;
    }

    //    public void setRolesToUrls(Map<Integer, Set<String>> rolesToUrls) {
//        Set<String> setAdmin = new HashSet<>();
//        setAdmin.add("/adminPage");
//        setAdmin.add("/adminPage/allUsers");
//        setAdmin.add("/adminPage/deleteUser");
//        setAdmin.add("/adminPage/addEdition");
//        rolesToUrls.put(1, setAdmin);
//        Set<String> set = new HashSet<>();
//        set.add("/personal/addFormSubscription");
//        set.add("/personal/userPage");
//        set.add("personal/mySubscriptions");
//        set.add("/personal/myPayments");
//        rolesToUrls.put(2, set);
//       this.rolesToUrls = rolesToUrls;
//    }
    public void setRolesToUrls(Map<Integer, Set<Pattern>> rolesToUrls) {
        this.rolesToUrls =rolesToUrls;
    }


}

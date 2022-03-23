package org.study.periodicals.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.regex.Pattern;

@Configuration
public class SecurityConfig {

    Map<Integer, Set<Pattern>> rolesToUrls = new HashMap<>();
    Pattern p1 = Pattern.compile("/adminPage/*");
    Pattern p2 = Pattern.compile("/personal/*");

    public Map<Integer, Set<Pattern>> getRolesToUrls() {
        return rolesToUrls;
    }

    public void setRolesToUrls(Map<Integer, Set<Pattern>> rolesToUrls) {
        Set<Pattern> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        rolesToUrls.put(1, set);
        Set<Pattern> set2 = new HashSet<>();
        set2.add(p2);
        rolesToUrls.put(2, set2);

        this.rolesToUrls = rolesToUrls;
    }


}

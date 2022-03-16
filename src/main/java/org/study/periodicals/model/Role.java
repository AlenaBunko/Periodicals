package org.study.periodicals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
public enum Role {

    ADMIN(1), USER(2);

    @Getter
    private final Integer roleId;


    public static Role getById(Integer roleId) {
        for (Role userRole : values()) {
            if (Objects.equals(userRole.getRoleId(), roleId)) {
                return userRole;
            }
        }
        throw new RuntimeException("Unknown role type: " + roleId);
    }
}

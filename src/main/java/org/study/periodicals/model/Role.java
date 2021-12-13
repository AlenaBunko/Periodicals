package org.study.periodicals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {

    ADMIN(1), USER(2);

    @Getter
    private final Integer roleId;

}

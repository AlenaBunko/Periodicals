package org.study.periodicals.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Edition {

    private Integer id;

    private String title;

    private Integer index;

    private String publishingHouse;

    private Integer recommendedPrice;

    private Set<User> users;

    private Subscription subscription;

    public Edition() {

    }
}

package org.study.periodicals.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class Subscription {

    private Integer id;

    private Integer actualPrice;

    private Integer quantity;

    private Date startDate;

    private Date finishDate;

    private Date subscriptionDate;

    private User user;

    private Set<Edition> editions;

    private Payment payment;

}

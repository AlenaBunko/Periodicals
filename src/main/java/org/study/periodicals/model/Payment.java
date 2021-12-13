package org.study.periodicals.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

    private Integer id;

    private Integer totalAmount;

    private boolean paymentStatus;

    private Integer cardNumber;

    private User user;

    private Subscription subscription;

}

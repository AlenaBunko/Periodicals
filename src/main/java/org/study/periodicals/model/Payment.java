package org.study.periodicals.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Payment {

    private Integer id;

    private Integer totalAmount;

    private boolean paymentStatus;

    private Integer cardNumber;

    private User user;

    private Subscription subscription;

}

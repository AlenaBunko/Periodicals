package org.study.periodicals.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Subscription {

    private Integer id;

    private Integer actualPrice;

    private Integer quantity;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date finishDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date subscriptionDate;

    private User user;

    private Set<Edition> editions;

    private Payment payment;

    public Subscription() {

    }

}

package org.study.periodicals.service;

import org.study.periodicals.model.Edition;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.*;

public class SubscriptionService {

    private DefaultEditionsRepository editionsRepository;

    private DefaultUsersRepository usersRepository;

    public SubscriptionService(DefaultEditionsRepository editionsRepository, DefaultUsersRepository usersRepository) {
        this.editionsRepository = editionsRepository;
        this.usersRepository = usersRepository;
    }

//?????????????????????
//    public Subscription createSubscription(String title,Subscription subscription) throws Exception {
//        List<Edition> allEditions = editionsRepository.findAllEditions();
//        Optional<Edition> searchEdition = allEditions
//                .stream()
//                .filter(e -> e.getTitle().equalsIgnoreCase(title))
//                .findFirst();
//        if (searchEdition.isPresent()) {
//            searchEdition.get().setSubscription(subscription);
//            usersRepository.addEditionInSubscription(searchEdition.get().getTitle());
//        } else {
//            throw new Exception("The Edition title" + title + " not found");
//        }
//        usersRepository.addSubscription(subscription);
//        return subscription;
//    }

    public Subscription addSubscriptionToUser(Subscription subscription) {
        usersRepository.addSubscription(subscription);
        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        return usersRepository.findAllSubscriptions();
    }


    public Subscription getSubscriptionById(Integer subscriptionsId) {
        List<Subscription> subscriptionsList = usersRepository.findAllSubscriptions();
        Optional<Subscription> optionalSubscription = subscriptionsList.stream()
                .filter(e -> e.getId().equals(subscriptionsId))
                .findFirst();
        Subscription subscription = optionalSubscription.orElseGet(Subscription::new);
        return subscription;
    }
}




























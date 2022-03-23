package org.study.periodicals.service;

import org.study.periodicals.model.Edition;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SubscriptionService {

    private DefaultEditionsRepository editionsRepository;

    private DefaultUsersRepository usersRepository;

    public SubscriptionService(DefaultEditionsRepository editionsRepository, DefaultUsersRepository usersRepository) {
        this.editionsRepository = editionsRepository;
        this.usersRepository = usersRepository;
    }

    public List<Edition> getAllEditions() {
        return editionsRepository.findAllEditions();
    }

    public Subscription createSubscription(String title,Integer quantity, Date startDate, Date finishDate, Date subscriptionDate, User user) throws Exception {
        Subscription subscription = new Subscription();
        List<Edition> allEditions = editionsRepository.findAllEditions();
        Optional<Edition> searchEdition = allEditions
                .stream()
                .filter(e -> e.getTitle().equalsIgnoreCase(title))
                .findFirst();
        if (searchEdition.isPresent()) {
            searchEdition.get().setSubscription(subscription);
            usersRepository.addEditionInSubscription(searchEdition.get().getTitle());
        } else {
            throw new Exception("The Edition title" + title + " not found");
        }
        subscription.setActualPrice((searchEdition.get().getRecommendedPrice()*120)/100);
        subscription.setQuantity(quantity);
        subscription.setStartDate(startDate);
        subscription.setFinishDate(finishDate);
        subscription.setSubscriptionDate(subscriptionDate);
        subscription.setUser(user);
        usersRepository.addSubscription(subscription);
        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        return usersRepository.findAllSubscriptions();
    }
}




























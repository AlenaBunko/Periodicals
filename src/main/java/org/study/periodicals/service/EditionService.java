package org.study.periodicals.service;

import org.study.periodicals.model.Edition;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EditionService {

    DefaultEditionsRepository editionsRepository;

    public EditionService(DefaultEditionsRepository editionsRepository) {
        this.editionsRepository = editionsRepository;
    }

    public List<Edition> getAllEditions() {
        return editionsRepository.findAllEditions();
    }

    public Edition addEdition(String title, Integer index, String publishingHouse, Integer recommendedPrice) {
        Edition edition = new Edition();
        edition.setTitle(title);
        edition.setIndex(index);
        edition.setPublishingHouse(publishingHouse);
        edition.setRecommendedPrice(recommendedPrice);
        editionsRepository.addEdition(edition);
        return edition;
    }

    public Edition findEditionById(Integer id) {
        Optional<Edition> optEdition = Optional.of(editionsRepository.findEditionById(id));
        return optEdition.get();
    }

    public void deleteEdition(Integer id) {
        editionsRepository.delete(id);
    }
}

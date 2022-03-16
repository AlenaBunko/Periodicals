package org.study.periodicals.repository.interfaces;

import org.study.periodicals.model.Edition;
import org.study.periodicals.model.User;

import java.util.List;

public interface EditionsRepository {

    void addEdition(Edition edition);

    Edition findEditionByTitle(String title);

    List<Edition> findAllEditions();

    List<Edition> findEditionsByUser(int id);

    void updateEdition (Edition edition);

    void delete (Integer index);
}

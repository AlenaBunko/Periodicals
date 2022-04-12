package org.study.periodicals.repository.interfaces;

import org.study.periodicals.model.Edition;
import org.study.periodicals.model.User;

import java.util.List;

public interface EditionsRepository {

    void addEdition(Edition edition);

    Edition findEditionById(Integer Id);

    List<Edition> findAllEditions();

    List<Edition> findEditionsByUser(int id);

    void delete (Integer index);
}

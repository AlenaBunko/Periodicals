package org.study.periodicals.repository.interfaces;

import org.study.periodicals.model.Edition;
import org.study.periodicals.model.User;

import java.util.List;

public interface EditionsRepository {

    void addEdition();

    List<Edition> findAllEditions(User login);

    void updateEdition (Edition edition);

    void delete (Integer index);
}

package repository;

import model.Edition;
import model.User;

import java.util.List;

public interface EditionsDAO {

    void addEdition();

    List<Edition> findAllEditions(User login);

    void updateEdition (Edition edition);

    void delete (Integer index);
}

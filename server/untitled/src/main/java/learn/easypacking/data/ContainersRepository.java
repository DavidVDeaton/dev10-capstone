package learn.easypacking.data;

import learn.easypacking.models.Container;

import java.util.List;

public interface ContainersRepository {
    List<Container> findAll();

    Container findById (int containerId);

    Container add(Container container);

    boolean update(Container container);

    boolean deleteById(int containerId);
}

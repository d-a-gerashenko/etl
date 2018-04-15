package project.repository;

import project.entity.AppValue;
import org.springframework.data.repository.CrudRepository;

public interface AppValueRepository extends CrudRepository<AppValue, Long> {

    public AppValue findOneByKey(String key);
}

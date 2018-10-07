package io.interglobe.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.interglobe.dto.TodoAppDTO;

@Repository
public interface TodoRepository extends JpaRepository<TodoAppDTO, Integer> {

	List<TodoAppDTO> findAllByOrderByIdDesc();

	TodoAppDTO findById(int id);

}

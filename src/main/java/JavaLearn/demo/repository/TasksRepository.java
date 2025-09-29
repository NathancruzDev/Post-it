package JavaLearn.demo.repository;

import JavaLearn.demo.DTO.TaskIdDTO;
import JavaLearn.demo.DTO.TaskModelDTO;
import JavaLearn.demo.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<TaskModel,Long> {

    boolean existsByTarefa(String tarefa);

    List<TaskModel> findById(TaskModelDTO id);
    List<TaskModel> findById(TaskIdDTO id);
}

package com.example.spring_rest_api_session_java7.api.converter.task;

import com.example.spring_rest_api_session_java7.dto.task.TaskResponse;
import com.example.spring_rest_api_session_java7.entities.Task;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class TaskResponseConverter {
    public TaskResponse viewTask(Task task) {
        if (task == null) {
            return null;
        }
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadline(task.getDeadline());

        return taskResponse;
    }

    public List<TaskResponse> view(List<Task> tasks) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : tasks) {
            taskResponseList.add(viewTask(task));
        }
        return taskResponseList;
    }
}

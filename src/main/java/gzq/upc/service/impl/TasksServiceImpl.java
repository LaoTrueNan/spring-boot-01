package gzq.upc.service.impl;

import gzq.upc.dataobject.Tasks;
import gzq.upc.repository.TasksRepository;
import gzq.upc.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService{

    @Autowired
    private TasksRepository tasksRepository;

    @Override
    public Tasks findOne(String orderId) {
        return tasksRepository.findByOrderId(orderId);
    }
}

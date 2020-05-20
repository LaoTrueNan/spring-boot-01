package gzq.upc.service;

import gzq.upc.dataobject.Tasks;

public interface TasksService {
    Tasks findOne(String orderId);
}

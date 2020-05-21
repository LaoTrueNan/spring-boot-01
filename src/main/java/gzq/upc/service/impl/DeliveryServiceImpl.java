package gzq.upc.service.impl;

import gzq.upc.dataobject.Deliverer;
import gzq.upc.enums.ResultEnum;
import gzq.upc.exception.SellException;
import gzq.upc.repository.DelivererRepository;
import gzq.upc.repository.TasksRepository;
import gzq.upc.service.DeliveryService;
import gzq.upc.utils.IntUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DelivererRepository delivererRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Override
    public Page<Deliverer> findList(Integer status,Pageable pageable) {
        Page<Deliverer> delivererPage = delivererRepository.findByStatus(status,pageable);

        return new PageImpl<Deliverer>(delivererPage.getContent(),pageable,delivererPage.getTotalElements());
    }

    @Override
    public Deliverer findOne(String idNum) {
        Optional<Deliverer> delivererOptional = delivererRepository.findById(idNum);
        if(delivererOptional != null){
            return delivererOptional.get();
        }
        return null;
    }

    @Override
    public Deliverer update(String idNum,Integer sellId) {
        Deliverer deliverer = this.findOne(idNum);
        if(deliverer==null){
            throw new SellException(ResultEnum.DELIVERER_NOT_EXIST);
        }

        //给通过申请的配送员分配工作ID(随机数)
        Integer deliveryId= IntUtil.getUniqueKey();
        deliverer.setDeliveryId(String.valueOf(deliveryId));
        deliverer.setStatus(sellId);
        deliverer=delivererRepository.save(deliverer);
        if(deliverer==null){
            throw new SellException(ResultEnum.HFIRE_FAILED);
        }
        return deliverer;
    }

    @Override
    public Deliverer fire(String idNum) {
        Deliverer deliverer = this.findOne(idNum);
        if(deliverer==null){
            throw new SellException(ResultEnum.DELIVERER_NOT_EXIST);
        }else if(deliverer.getOrderTask()!=0){
            throw new SellException(ResultEnum.FIRE_FAILED);
        }
        deliverer.setStatus(-1);
        tasksRepository.deleteByDeliveryId(deliverer.getDeliveryId());
        //解雇必须将tasks表中的记录删除
        deliverer.setDeliveryId("");
        deliverer=delivererRepository.save(deliverer);
        if(deliverer == null){
            throw new SellException(ResultEnum.HFIRE_FAILED);
        }
        return deliverer;
    }

    @Override
    public Deliverer findByDeliveryId(String deliveryId) {
        return delivererRepository.findByDeliveryId(deliveryId);
    }
}

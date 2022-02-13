package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.model.view.ShopTransactionModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopTransactionMapper {
    @Autowired
    private MapperService mapperService;

    public ShopTransaction shopTransactionModelToEntity(ShopTransactionModel model) {
        ModelMapper mapper = mapperService.getModelMapper();
        mapper.typeMap(ShopTransactionModel.class, ShopTransaction.class);
        mapper.addMappings(new PropertyMap<ShopTransactionModel, ShopTransaction>() {
            @Override
            protected void configure() {
                skip(destination.getOrder());
                skip(destination.getShop());
            }
        });

        mapper.validate();
        return mapper.map(model, ShopTransaction.class);
    }

    public ShopTransactionModel shopTransactionEntityToModel(ShopTransaction entity) {
        ShopTransactionModel model = new ShopTransactionModel();

        model.setId(entity.getId());
        model.setAmount(entity.getAmount());
        model.setDescription(entity.getDescription());
        model.setDate(entity.getDate());
        model.setStatus(entity.getStatus());

        model.setShop(new ShopModel(entity.getShop()));
        model.setOrder(new OrderModel(entity.getOrder()));

        return model;
    }
}

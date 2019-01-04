package com.example.miaoshademo.service.impl;

import com.example.miaoshademo.service.ItemService;
import com.example.miaoshademo.service.model.ItemModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) {

        return null;
    }

    @Override
    public List<ItemModel> listItem() {
        return null;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        return null;
    }
}

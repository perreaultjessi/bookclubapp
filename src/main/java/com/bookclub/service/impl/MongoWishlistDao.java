package com.bookclub.impl;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoWishlistDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addItem(WishlistItem item) {
    }

    @Override
    public void removeItem(String itemId) {
    }

    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }
}

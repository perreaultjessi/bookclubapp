package com.bookclub.impl;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao
{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoWishlistDao(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addItem(WishlistItem item)
    {
    }

    @Override
    public void removeItem(String itemId)
    {
    }

    @Override
    public List<WishlistItem> list(String username)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("Username").is(username));
        return mongoTemplate.find(query, WishlistItem.class);
    }

    @Override
    public void add(WishlistItem entity)
    {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishlistItem entity)
    {
        WishlistItem wishlistItem = mongoTemplate.findById(entity.getId(), WishlistItem.class);
        if (wishlistItem != null)
        {
            wishlistItem.setIsbn(entity.getIsbn());
            wishlistItem.setTitle(entity.getTitle());
            wishlistItem.setUsername(entity.getUsername());
            mongoTemplate.save(wishlistItem);
        }
    }

    @Override
    public boolean remove(String key)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(key));
        mongoTemplate.remove(query, WishlistItem.class);
        return true;
    }
}

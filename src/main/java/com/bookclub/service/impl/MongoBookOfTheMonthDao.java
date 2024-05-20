package com.bookclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao
{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(BookOfTheMonth bookOfTheMonth)
    {
        mongoTemplate.insert(bookOfTheMonth);
    }

    @Override
    public void remove(String id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, BookOfTheMonth.class);
    }

    @Override
    public List<BookOfTheMonth> list(String key)
    {
        int month = Integer.parseInt(key);
        System.out.println("Month: " + month);

        if (month == 999)
        {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("month").is(month));
        return mongoTemplate.find(query, BookOfTheMonth.class);
    }
}
package com.bookclub.service.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericCrudDao; //was genericDao

public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {
}


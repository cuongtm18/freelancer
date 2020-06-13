package com.btl.utils;

import java.util.List;

public interface ExecuteObjectDAO<T> {

    public List<T> findAll();

    public List<T> onSearchData(T entity);

    public boolean insertData(T entity);

    public boolean updateData(T entity);

    public boolean deleteData(T entity);

}

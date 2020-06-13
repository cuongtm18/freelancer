package com.btl.utils;

public interface ExecuteObjectController<T> {

    void onShowData();

    void onSearch();

    void prepareInsert();

    void onInsert();

    void onComfirmUpdate();

    void onDelete(T entity);

    void resetDialogForm();

    void findById(int entity);
}

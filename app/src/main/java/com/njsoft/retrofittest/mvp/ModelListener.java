package com.njsoft.retrofittest.mvp;

/**
 *
 * @param <T>
 */
public interface ModelListener<T> {
        void modelSuccessful(T androidbean);

        void modelErro(String error);
    }
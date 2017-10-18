package com.lining.yokinjar.ErJi;

public abstract class BasePresenter {

    protected ViewCallBack mViewCallBack;

    void add(ViewCallBack viewCallBack) {
        this.mViewCallBack = viewCallBack;
    }

    void remove() {
        this.mViewCallBack = null;
    }

    protected abstract void getData();
}

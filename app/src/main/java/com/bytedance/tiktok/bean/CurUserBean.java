package com.bytedance.tiktok.bean;

import java.io.Serializable;

/**
 * create i小灰
 * create on 2020-06-04
 * description 当前播放视频的作者Userbean切换
 */
public class CurUserBean implements Serializable {
    private VideoBean.UserBean userBean;

    public CurUserBean(VideoBean.UserBean userBean) {
        this.userBean = userBean;
    }

    public VideoBean.UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(VideoBean.UserBean userBean) {
        this.userBean = userBean;
    }
}

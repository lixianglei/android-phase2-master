package com.ge.med.mobile.nursing.dao.entity;

/**定义网络访问实体JSON解析Bean
 * Created by Administrator on 2016/11/8.
 */
public class RWentity {
    private int imageView;
    private String name;

    public RWentity(int imageView, String name) {
        this.imageView = imageView;
        this.name = name;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

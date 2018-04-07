package com.example.jiao.cityapplication.chatview.domain;


import com.example.jiao.cityapplication.chatview.domain.Emojicon.Type;

import java.util.List;

/**
 * 一组表情所对应的实体类
 *
 */
public class EmojiconGroupEntity {
    /**
     * 表情数据
     */
    private List<Emojicon> emojiconList;
    /**
     * 图片
     */
    private int icon;
    /**
     * 组名
     */
    private String name;
    /**
     * 表情类型
     */
    private Type type;
    
    public EmojiconGroupEntity(){}
    
    public EmojiconGroupEntity(int icon, List<Emojicon> emojiconList){
        this.icon = icon;
        this.emojiconList = emojiconList;
        type = Type.NORMAL;
    }
    
    public EmojiconGroupEntity(int icon, List<Emojicon> emojiconList, Type type){
        this.icon = icon;
        this.emojiconList = emojiconList;
        this.type = type;
    }
    
    public List<Emojicon> getEmojiconList() {
        return emojiconList;
    }
    public void setEmojiconList(List<Emojicon> emojiconList) {
        this.emojiconList = emojiconList;
    }
    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    
}

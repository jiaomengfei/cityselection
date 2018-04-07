package com.example.jiao.cityapplication.chatview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.jiao.cityapplication.chatview.domain.Emojicon;


public class EmojiconMenuBase extends LinearLayout {
    protected EaseEmojiconMenuListener listener;
    
    public EmojiconMenuBase(Context context) {
        super(context);
    }
    
    @SuppressLint("NewApi")
    public EmojiconMenuBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public EmojiconMenuBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    
    /**
     * set emojicon menu listener
     * @param listener
     */
    public void setEmojiconMenuListener(EaseEmojiconMenuListener listener){
        this.listener = listener;
    }
    
    public interface EaseEmojiconMenuListener{
        /**
         * on emojicon clicked
         * @param emojicon
         */
        void onExpressionClicked(Emojicon emojicon);
        /**
         * on delete image clicked
         */
        void onDeleteImageClicked();
    }
}

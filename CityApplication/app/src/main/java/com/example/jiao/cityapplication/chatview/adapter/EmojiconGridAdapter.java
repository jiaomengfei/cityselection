package com.example.jiao.cityapplication.chatview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.chatview.domain.Emojicon;
import com.example.jiao.cityapplication.chatview.domain.Emojicon.Type;
import com.example.jiao.cityapplication.chatview.utils.SmileUtils;

import java.util.List;

public class EmojiconGridAdapter extends ArrayAdapter<Emojicon> {

    private Type emojiconType;


    public EmojiconGridAdapter(Context context, int textViewResourceId, List<Emojicon> objects, Type emojiconType) {
        super(context, textViewResourceId, objects);
        this.emojiconType = emojiconType;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            if(emojiconType == Type.BIG_EXPRESSION){
                convertView = View.inflate(getContext(), R.layout.chat_row_big_expression, null);
            }else{
                convertView = View.inflate(getContext(), R.layout.chat_row_expression, null);
            }
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_expression);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_name);
        Emojicon emojicon = getItem(position);
        if(textView != null && emojicon.getName() != null){
            textView.setText(emojicon.getName());
        }

       if(SmileUtils.DELETE_KEY.equals(emojicon.getEmojiText())){
            imageView.setImageResource(R.drawable.chat_delete_expression);
        }else{
            if(emojicon.getIcon() != 0){
                imageView.setImageResource(emojicon.getIcon());
            }
//            else if(emojicon.getIconPath() != null){
//                Glide.with(getContext()).load(emojicon.getIconPath()).placeholder(R.drawable.ease_default_expression).into(imageView);
//            }
        }
        
        
        return convertView;
    }
    
}

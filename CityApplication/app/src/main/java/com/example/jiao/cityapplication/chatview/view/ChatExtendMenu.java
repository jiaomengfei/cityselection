package com.example.jiao.cityapplication.chatview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.chatview.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Extend menu when user want send image, voice clip, etc
 *
 */
public class ChatExtendMenu extends GridView {

    protected Context context;
    private List<ChatMenuItemModel> itemModels = new ArrayList<ChatMenuItemModel>();
    public static final int ITEM_TAKE_PICTURE = 1;
    public static final int ITEM_PICTURE = 2;
    public static final int ITEM_DIAMOND = 3;
    protected int[] itemStrings = {R.string.attach_picture, R.string.attach_take_pic, R.string.send_diamond};
    protected int[] itemdrawables = {R.drawable.chat_image_selector, R.drawable.chat_takepic_selector,
            R.drawable.chat_location_selector};
    protected int[] itemIds = {ITEM_PICTURE, ITEM_TAKE_PICTURE, ITEM_DIAMOND};
    public ChatExtendMenuItemClickListener listener;
    public ChatExtendMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public ChatExtendMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChatExtendMenu(Context context) {
        super(context);
        init(context, null);
    }
    
    private void init(Context context, AttributeSet attrs){
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChatExtendMenu);
        int numColumns = ta.getInt(R.styleable.ChatExtendMenu_numColumns, 4);
        ta.recycle();
        
        setNumColumns(numColumns);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        setGravity(Gravity.CENTER_VERTICAL);
        setVerticalSpacing(DensityUtil.dip2px(context, 8));
    }
    
    /**
     * init
     */
    public void init(){
        registerExtendMenuItem();
        setAdapter(new ItemAdapter(context, itemModels));
    }

    private void registerExtendMenuItem() {
        for (int i = 0; i < itemStrings.length; i++) {
            registerMenuItem(itemStrings[i], itemdrawables[i], itemIds[i]);
        }
    }

    /**
     * register menu item
     * 
     * @param name
     *            item name
     * @param drawableRes
     *            background of item
     * @param itemId
     *             id
     *            on click event of item
     */
    public void registerMenuItem(String name, int drawableRes, int itemId) {
        ChatMenuItemModel item = new ChatMenuItemModel();
        item.name = name;
        item.image = drawableRes;
        item.id = itemId;
        itemModels.add(item);
    }
    
    /**
     * register menu item
     * 
     * @param nameRes
     *            resource id of item name
     * @param drawableRes
     *            background of item
     * @param itemId
     *             id
     *             on click event of item
     */
    public void registerMenuItem(int nameRes, int drawableRes, int itemId) {
        registerMenuItem(context.getString(nameRes), drawableRes, itemId);
    }
    
    
    private class ItemAdapter extends ArrayAdapter<ChatMenuItemModel> {

        private Context context;

        @SuppressLint("ResourceType")
        public ItemAdapter(Context context, List<ChatMenuItemModel> objects) {
            super(context, 1, objects);
            this.context = context;
        }
        
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ChatMenuItem menuItem = null;
            if(convertView == null){
                convertView = new ChatMenuItem(context);
            }
            menuItem = (ChatMenuItem) convertView;
            menuItem.setImage(getItem(position).image);
            menuItem.setText(getItem(position).name);
            menuItem.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onClick(getItem(position).id, v);
                    }
                }
            });
            return convertView;
        }
        
        
    }
    
    
    public interface ChatExtendMenuItemClickListener {
        void onClick(int itemId, View view);
    }

    public void setChatExtendMenuItemClickListener(ChatExtendMenuItemClickListener listener) {
        this.listener = listener;
    }
    
    class ChatMenuItemModel{
        String name;
        int image;
        int id;
    }
    
    class ChatMenuItem extends LinearLayout {
        private ImageView imageView;
        private TextView textView;

        public ChatMenuItem(Context context, AttributeSet attrs, int defStyle) {
            this(context, attrs);
        }

        public ChatMenuItem(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);
        }

        public ChatMenuItem(Context context) {
            super(context);
            init(context, null);
        }

        private void init(Context context, AttributeSet attrs) {
            LayoutInflater.from(context).inflate(R.layout.chat_menu_item, this);
            imageView = (ImageView) findViewById(R.id.image);
            textView = (TextView) findViewById(R.id.text);
        }

        public void setImage(int resid) {
            imageView.setBackgroundResource(resid);
        }

        public void setText(int resid) {
            textView.setText(resid);
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}

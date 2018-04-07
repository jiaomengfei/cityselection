/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jiao.cityapplication.chatview.utils;

import android.content.Context;
import android.net.Uri;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.style.ImageSpan;


import com.example.jiao.cityapplication.chatview.domain.DefaultEmojiconDatas;
import com.example.jiao.cityapplication.chatview.domain.Emojicon;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmileUtils {
    public static final String DELETE_KEY = "em_delete_delete_expression";
	public static final String ee_1 = "[呵呵]";
	public static final String ee_2 = "[嘻嘻]";
	public static final String ee_3 = "[哈哈]";
	public static final String ee_4 = "[爱你]";
	public static final String ee_5 = "[挖鼻屎]";
	public static final String ee_6 = "[吃惊]";
	public static final String ee_7 = "[晕]";
	public static final String ee_8 = "[泪]";
	public static final String ee_9 = "[馋嘴]";
	public static final String ee_10 = "[抓狂]";
	public static final String ee_11 = "[哼]";
	public static final String ee_12 = "[可爱]";
	public static final String ee_13 = "[怒]";
	public static final String ee_14 = "[汗]";
	public static final String ee_15 = "[害羞]";
	public static final String ee_16 = "[睡觉]";
	public static final String ee_17 = "[钱]";
	public static final String ee_18 = "[偷笑]";
	public static final String ee_19 = "[笑cry]";
	public static final String ee_20 = "[doge]";
	public static final String ee_21 = "[喵喵]";
	public static final String ee_22 = "[酷]";
	public static final String ee_23 = "[衰]";
	public static final String ee_24 = "[闭嘴]";
	public static final String ee_25 = "[鄙视]";
	public static final String ee_26 = "[花心]";
	public static final String ee_27 = "[鼓掌]";
	public static final String ee_28 = "[悲伤]";
	public static final String ee_29 = "[思考]";
	public static final String ee_30 = "[生病]";
	public static final String ee_31 = "[亲亲]";
	public static final String ee_32 = "[怒骂]";
	public static final String ee_33 = "[太开心]";
	public static final String ee_34 = "[懒得理你]";
	public static final String ee_35 = "[右哼哼]";
	
	private static final Factory spannableFactory = Factory
	        .getInstance();
	
	private static final Map<Pattern, Object> emoticons = new HashMap<Pattern, Object>();
	

	static {
	    Emojicon[] emojicons = DefaultEmojiconDatas.getData();
		for (Emojicon emojicon : emojicons) {
			addPattern(emojicon.getEmojiText(), emojicon.getIcon());
		}
//		EaseEmojicon[] emojbigicons = EaseDefaultEmojiconDatas.getBigData();
//		for (EaseEmojicon emojicon : emojbigicons) {
//			addPattern(emojicon.getEmojiText(), emojicon.getIcon());
//		}
//	    EaseEmojiconInfoProvider emojiconInfoProvider = EaseUI.getInstance().getEmojiconInfoProvider();
//	    if(emojiconInfoProvider != null && emojiconInfoProvider.getTextEmojiconMapping() != null){
//	        for(Entry<String, Object> entry : emojiconInfoProvider.getTextEmojiconMapping().entrySet()){
//	            addPattern(entry.getKey(), entry.getValue());
//	        }
//	    }
	    
	}

	/**
	 * add text and icon to the map
	 * @param emojiText-- text of emoji
	 * @param icon -- resource id or local path
	 */
	public static void addPattern(String emojiText, Object icon){
	    emoticons.put(Pattern.compile(Pattern.quote(emojiText)), icon);
	}
	

	/**
	 * replace existing spannable with smiles
	 * @param context
	 * @param spannable
	 * @return
	 */
	public static boolean addSmiles(Context context, Spannable spannable) {
	    boolean hasChanges = false;
	    for (Entry<Pattern, Object> entry : emoticons.entrySet()) {
	        Matcher matcher = entry.getKey().matcher(spannable);
	        while (matcher.find()) {
	            boolean set = true;
	            for (ImageSpan span : spannable.getSpans(matcher.start(),
	                    matcher.end(), ImageSpan.class))
	                if (spannable.getSpanStart(span) >= matcher.start()
	                        && spannable.getSpanEnd(span) <= matcher.end())
	                    spannable.removeSpan(span);
	                else {
	                    set = false;
	                    break;
	                }
	            if (set) {
	                hasChanges = true;
	                Object value = entry.getValue();
	                if(value instanceof String && !((String) value).startsWith("http")){
	                    File file = new File((String) value);
	                    if(!file.exists() || file.isDirectory()){
	                        return false;
	                    }
	                    spannable.setSpan(new ImageSpan(context, Uri.fromFile(file)),
	                            matcher.start(), matcher.end(),
	                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	                }else{
	                    spannable.setSpan(new ImageSpan(context, (Integer)value),
	                            matcher.start(), matcher.end(),
	                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	                }
	            }
	        }
	    }
	    
	    return hasChanges;
	}

	public static Spannable getSmiledText(Context context, CharSequence text) {
	    Spannable spannable = spannableFactory.newSpannable(text);
	    addSmiles(context, spannable);
	    return spannable;
	}
	
	public static boolean containsKey(String key){
		boolean b = false;
		for (Entry<Pattern, Object> entry : emoticons.entrySet()) {
	        Matcher matcher = entry.getKey().matcher(key);
	        if (matcher.find()) {
	        	b = true;
	        	break;
	        }
		}
		
		return b;
	}
	
	public static int getSmilesSize(){
        return emoticons.size();
    }
    
	
}

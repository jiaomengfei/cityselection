package com.example.jiao.cityapplication.chatview.domain;


import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.chatview.utils.SmileUtils;

public class DefaultEmojiconDatas {

    private static String[] bigemojis=new String[]{
            "[猪头]",
		"[熊猫]",
		"[兔子]",   "[猪头]",
            "[熊猫]",
            "[兔子]"
    };
private static int[] bigicons = new int[]{
        R.drawable.community_biaoqing_zhutou,
        R.drawable.community_biaoqing_xiongmao,
        R.drawable.community_biaoqing_tuzi,
        R.drawable.community_biaoqing_zhutou,
        R.drawable.community_biaoqing_xiongmao,
        R.drawable.community_biaoqing_tuzi
};
    private static String[] emojis = new String[]{
        SmileUtils.ee_1,
        SmileUtils.ee_2,
        SmileUtils.ee_3,
        SmileUtils.ee_4,
        SmileUtils.ee_5,
        SmileUtils.ee_6,
        SmileUtils.ee_7,
        SmileUtils.ee_8,
        SmileUtils.ee_9,
        SmileUtils.ee_10,
        SmileUtils.ee_11,
        SmileUtils.ee_12,
        SmileUtils.ee_13,
        SmileUtils.ee_14,
        SmileUtils.ee_15,
        SmileUtils.ee_16,
        SmileUtils.ee_17,
        SmileUtils.ee_18,
        SmileUtils.ee_19,
        SmileUtils.ee_20,
        SmileUtils.ee_21,
        SmileUtils.ee_22,
        SmileUtils.ee_23,
        SmileUtils.ee_24,
        SmileUtils.ee_25,
        SmileUtils.ee_26,
        SmileUtils.ee_27,
        SmileUtils.ee_28,
        SmileUtils.ee_29,
        SmileUtils.ee_30,
        SmileUtils.ee_31,
        SmileUtils.ee_32,
        SmileUtils.ee_33,
        SmileUtils.ee_34,
        SmileUtils.ee_35,
    };
    
    private static int[] icons = new int[]{
        R.drawable.community_biaoqing_hehe,
        R.drawable.community_biaoqing_xixi,
        R.drawable.community_biaoqing_haha,
        R.drawable.community_biaoqing_aini,
        R.drawable.community_biaoqing_wabishi,
        R.drawable.community_biaoqing_chijing,
        R.drawable.community_biaoqing_yun,
        R.drawable.community_biaoqing_lei,
        R.drawable.community_biaoqing_chanzui,
        R.drawable.community_biaoqing_zhuakuang,
        R.drawable.community_biaoqing_heng,
        R.drawable.community_biaoqing_keai,
        R.drawable.community_biaoqing_nu,
        R.drawable.community_biaoqing_han,
        R.drawable.community_biaoqing_haixiu,
        R.drawable.community_biaoqing_shuijiao,
        R.drawable.community_biaoqing_qian,
        R.drawable.community_biaoqing_touxiao,
        R.drawable.community_biaoqing_xiaoku,
        R.drawable.community_biaoqing_dog,
        R.drawable.community_biaoqing_miao,
        R.drawable.community_biaoqing_ku,
        R.drawable.community_biaoqing_shuai,
        R.drawable.community_biaoqing_bizui,
        R.drawable.community_biaoqing_bishi,
        R.drawable.community_biaoqing_huaxin,
        R.drawable.community_biaoqing_guzhang,
        R.drawable.community_biaoqing_beishang,
        R.drawable.community_biaoqing_sikao,
        R.drawable.community_biaoqing_shengbing,
        R.drawable.community_biaoqing_qinqin,
        R.drawable.community_biaoqing_numa,
        R.drawable.community_biaoqing_taikaixin,
        R.drawable.community_biaoqing_landelini,
        R.drawable.community_biaoqing_youhengheng,
    };


    
    
    private static final Emojicon[] DATA = createData();
    private static final Emojicon[] BIGDATA=createBigData();

    private static Emojicon[] createBigData() {
        Emojicon[] data = new Emojicon[bigemojis.length];
        for(int i = 0; i < bigemojis.length; i++){
            data[i] = new Emojicon(bigicons[i], bigemojis[i], Emojicon.Type.BIG_EXPRESSION);
        }
        return data;
    }

    private static Emojicon[] createData(){
        Emojicon[] datas = new Emojicon[icons.length];
        for(int i = 0; i < icons.length; i++){
            datas[i] = new Emojicon(icons[i], emojis[i], Emojicon.Type.NORMAL);
        }
        return datas;
    }
    public static Emojicon[] getBigData(){
        return BIGDATA;
    }
    public static Emojicon[] getData(){
        return DATA;
    }
}

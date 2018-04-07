package com.example.jiao.cityapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.chatview.domain.Emojicon;
import com.example.jiao.cityapplication.chatview.utils.ChatUtils;
import com.example.jiao.cityapplication.chatview.utils.luban.Luban;
import com.example.jiao.cityapplication.chatview.utils.luban.OnCompressListener;
import com.example.jiao.cityapplication.chatview.view.ChatExtendMenu;
import com.example.jiao.cityapplication.chatview.view.ChatInputMenu;

import java.io.File;

import static com.example.jiao.cityapplication.chatview.view.ChatExtendMenu.ITEM_DIAMOND;
import static com.example.jiao.cityapplication.chatview.view.ChatExtendMenu.ITEM_PICTURE;
import static com.example.jiao.cityapplication.chatview.view.ChatExtendMenu.ITEM_TAKE_PICTURE;

public class ViewActivity extends AppCompatActivity {

    private ChatInputMenu inputMenu;
    private ChatExtendMenu chatExtendMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        inputMenu = (ChatInputMenu) findViewById(R.id.input_menu);
        chatExtendMenu = (ChatExtendMenu) findViewById(R.id.extend_menu);
        inputMenu.init(null);
        chatExtendMenu.setChatExtendMenuItemClickListener(new ChatExtendMenu.ChatExtendMenuItemClickListener() {
            @Override
            public void onClick(int itemId, View view) {
                switch (itemId) {
                    case ITEM_TAKE_PICTURE:
                        ChatUtils.selectPicFromCamera(ViewActivity.this);
                        break;
                    case ITEM_PICTURE:
                        ChatUtils.selectPicFromLocal(ViewActivity.this);
                        break;
                    case ITEM_DIAMOND:
                        Toast.makeText(ViewActivity.this, "送砖石", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        inputMenu.setChatInputMenuListener(new ChatInputMenu.ChatInputMenuListener() {
            @Override
            public void onSendMessage(String content) {
                Toast.makeText(ViewActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBigExpressionClicked(Emojicon emojicon) {

            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ChatUtils.REQUEST_CODE_PICTURE) {
                if (data != null) {
                    Uri selectedImage = data.getData();
                    if (selectedImage != null) {
                        Toast.makeText(ViewActivity.this, selectedImage.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (requestCode == ChatUtils.REQUEST_CODE_CAMERA) {
                if (resultCode == RESULT_CANCELED) {
                    ChatUtils.deleteImageUri(this, ChatUtils.imageUriFromCamera);
                } else {
                    Uri imageUriCamera = ChatUtils.imageUriFromCamera;

                    try {
                        Luban.get(this).load(new File(ChatUtils.getImageAbsolutePath19(this, imageUriCamera)))
                                .putGear(Luban.FIRST_GEAR).setCompressListener(new CompressListenerImpl()).launch();
                    } catch (Exception e) {

                    }
                }

            }
        }
    }
    /**
     * 压缩图片回调
     */
    public class CompressListenerImpl implements OnCompressListener {

        @Override
        public void onStart() {
            // showLoading();
        }

        @Override
        public void onSuccess(File file) {
            Log.e("imgFilePath: %s", file.getAbsolutePath());

            // UtilLog.d("file: %d", file.length());
            if (file.length() < 1000 * 1000) {
//                图片<1M
                Uri uri = Uri.fromFile(file);
                Toast.makeText(ViewActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
            } else {
//提示图片太大了
                //     GGToast.showToast(getString(R.string.str_post_image_size_max), true);
            }
            // dismissLoading();

        }

        @Override
        public void onError(Throwable e) {
            // dismissLoading();
        }
    }


}

package com.example.jiao.cityapplication.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.jiao.cityapplication.R
import com.example.jiao.cityapplication.chatview.domain.Emojicon
import com.example.jiao.cityapplication.chatview.utils.ChatUtils
import com.example.jiao.cityapplication.chatview.utils.luban.Luban
import com.example.jiao.cityapplication.chatview.utils.luban.OnCompressListener
import com.example.jiao.cityapplication.chatview.view.ChatExtendMenu
import com.example.jiao.cityapplication.chatview.view.ChatInputMenu

import java.io.File

import com.example.jiao.cityapplication.chatview.view.ChatExtendMenu.ITEM_DIAMOND
import com.example.jiao.cityapplication.chatview.view.ChatExtendMenu.ITEM_PICTURE
import com.example.jiao.cityapplication.chatview.view.ChatExtendMenu.ITEM_TAKE_PICTURE

class ViewActivity : AppCompatActivity() {

  private var inputMenu: ChatInputMenu? = null
  private var chatExtendMenu: ChatExtendMenu? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_view)
    inputMenu = findViewById(R.id.input_menu) as ChatInputMenu
    chatExtendMenu = findViewById(R.id.extend_menu) as ChatExtendMenu
    inputMenu!!.init(null)
    chatExtendMenu!!.setChatExtendMenuItemClickListener { itemId, view ->
      when (itemId) {
        ITEM_TAKE_PICTURE -> ChatUtils.selectPicFromCamera(this@ViewActivity)
        ITEM_PICTURE -> ChatUtils.selectPicFromLocal(this@ViewActivity)
        ITEM_DIAMOND -> Toast.makeText(this@ViewActivity, "送砖石", Toast.LENGTH_SHORT).show()
        else -> {
        }
      }
    }
    inputMenu!!.setChatInputMenuListener(object : ChatInputMenu.ChatInputMenuListener {
      override fun onSendMessage(content: String) {
        Toast.makeText(this@ViewActivity, content, Toast.LENGTH_SHORT).show()
      }

      override fun onBigExpressionClicked(emojicon: Emojicon) {

      }

    })
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == ChatUtils.REQUEST_CODE_PICTURE) {
        if (data != null) {
          val selectedImage = data.data
          if (selectedImage != null) {
            Toast.makeText(this@ViewActivity, selectedImage.toString(), Toast.LENGTH_SHORT).show()
          }
        }
      } else if (requestCode == ChatUtils.REQUEST_CODE_CAMERA) {
        if (resultCode == Activity.RESULT_CANCELED) {
          ChatUtils.deleteImageUri(this, ChatUtils.imageUriFromCamera)
        } else {
          val imageUriCamera = ChatUtils.imageUriFromCamera

          try {
            Luban.get(this).load(File(ChatUtils.getImageAbsolutePath19(this, imageUriCamera)))
                .putGear(Luban.FIRST_GEAR).setCompressListener(CompressListenerImpl()).launch()
          } catch (e: Exception) {

          }

        }

      }
    }
  }

  /**
   * 压缩图片回调
   */
  inner class CompressListenerImpl : OnCompressListener {

    override fun onStart() {
      // showLoading();
    }

    override fun onSuccess(file: File) {
      Log.e("imgFilePath: %s", file.absolutePath)

      // UtilLog.d("file: %d", file.length());
      if (file.length() < 1000 * 1000) {
        //                图片<1M
        val uri = Uri.fromFile(file)
        Toast.makeText(this@ViewActivity, uri.toString(), Toast.LENGTH_SHORT).show()
      } else {
        //提示图片太大了
        //     GGToast.showToast(getString(R.string.str_post_image_size_max), true);
      }
      // dismissLoading();

    }

    override fun onError(e: Throwable) {
      // dismissLoading();
    }
  }

}

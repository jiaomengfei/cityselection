package com.example.jiao.cityapplication.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.example.jiao.cityapplication.R
import com.example.jiao.cityapplication.ZBeanCity
import com.example.jiao.cityapplication.view.BaseTitleBar

import java.io.IOException
import java.io.InputStream

import io.realm.Realm
import utils.DataUtil

class FirstActivity : AppCompatActivity(), View.OnClickListener {

  private var mButton: Button? = null
  private var mDisplay: TextView? = null
  var myRealm = Realm.getDefaultInstance()
  private var mView: Button? = null
  private var mCommonTitleBar: BaseTitleBar? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_first)
    mButton = findViewById(R.id.city_choice) as Button
    mView = findViewById(R.id.view_display) as Button
    mDisplay = findViewById(R.id.city_display) as TextView
    mButton!!.setOnClickListener(this)
    mView!!.setOnClickListener(this)
    mCommonTitleBar = findViewById(R.id.ctbar) as BaseTitleBar?
    //initTitleBar();
    val `is` = DataUtil.getDatabaseConfig(this)
    if (!`is`) {
      createDatabase()
    }

  }

  private fun initTitleBar() {

    mCommonTitleBar!!.mainTitle = "自定义view"
    mCommonTitleBar!!.setSubMainTitle("自定义view")
    setDefaultLogo()
  }

  private fun setDefaultLogo() {
    var inputStream: InputStream? = null
    try {
      inputStream = this.assets.open("icon_sms_default.png")
      mCommonTitleBar!!.setLogo(BitmapFactory.decodeStream(inputStream))
    } catch (e: IOException) {
      e.printStackTrace()
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close()
        } catch (e: IOException) {
          e.printStackTrace()
        }

      }
    }
  }

  private fun createDatabase() {

    myRealm.executeTransaction { realm ->
      try {
        val `is` = this@FirstActivity.assets.open("china.json")
        realm.createAllFromJson<ZBeanCity>(ZBeanCity::class.java, `is`)
        DataUtil.setDatabaseConfig(this@FirstActivity, true)
      } catch (e: IOException) {
        throw RuntimeException()
      }
    }
  }

  override fun onClick(v: View) {
    when (v.id) {
      R.id.city_choice -> {
        val intent = Intent(this@FirstActivity, MainActivity().javaClass)
        startActivityForResult(
            intent,
            REQUEST_CODE_PICK_CITY
        )
      }
      R.id.view_display -> {
        val intent1 = Intent(this@FirstActivity, ViewActivity().javaClass)
        startActivity(intent1)
      }
    }

  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == Activity.RESULT_OK) {
      if (data != null) {
        val city = data.getStringExtra(MainActivity.KEY_PICKED_CITY)
        mDisplay!!.text = "当前选择：$city"
      }
    }
  }

  companion object {
    private val REQUEST_CODE_PICK_CITY = 112
  }
}

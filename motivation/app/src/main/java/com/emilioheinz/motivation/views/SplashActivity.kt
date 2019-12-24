package com.emilioheinz.motivation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.emilioheinz.motivation.R
import com.emilioheinz.motivation.util.MotivationConstants
import com.emilioheinz.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurity: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurity = SecurityPreferences(this)

        button_save.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name: String = edit_name.text.toString()

        if (name.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.inform_your_name_alert), Toast.LENGTH_LONG)
                .show()
        } else {
            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME, name)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}

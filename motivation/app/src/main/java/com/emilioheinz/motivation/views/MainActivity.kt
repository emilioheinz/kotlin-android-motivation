package com.emilioheinz.motivation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emilioheinz.motivation.R
import com.emilioheinz.motivation.mock.MotivationMock
import com.emilioheinz.motivation.util.MotivationConstants
import com.emilioheinz.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter = MotivationConstants.PHRASE_FILTER.ALL
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mMock = MotivationMock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)

        setListeners()

        handleFilter(R.id.image_all)
        refreshPhrase()
        verifyUserName()
    }

    private fun setListeners() {
        image_all.setOnClickListener(this)
        image_sun.setOnClickListener(this)
        image_happy.setOnClickListener(this)
        button_new_phrase.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id

        val listId = listOf(R.id.image_sun, R.id.image_all, R.id.image_happy)

        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.button_new_phrase) {
            refreshPhrase()
        }
    }

    private fun handleFilter(id: Int) {
        image_all.setImageResource(R.drawable.ic_all_inclusive_unselected)
        image_sun.setImageResource(R.drawable.ic_sun_unselected)
        image_happy.setImageResource(R.drawable.ic_happy_unselected)

        if (id == R.id.image_all) {
            mFilter = MotivationConstants.PHRASE_FILTER.ALL
            image_all.setImageResource(R.drawable.ic_all_inclusive_selected)
        } else if (id == R.id.image_sun) {
            mFilter = MotivationConstants.PHRASE_FILTER.MORNING
            image_sun.setImageResource(R.drawable.ic_sun_selected)
        } else {
            mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
            image_happy.setImageResource(R.drawable.ic_happy_selected)
        }

        refreshPhrase()
    }

    private fun refreshPhrase() {
        text_phrase.text = mMock.getPhrase(mFilter)
    }

    private fun verifyUserName() {
        text_user_name.text = "Olá " + mSecurityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
    }

}

package com.discoid.weather.fiveday.city

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.discoid.weather.fiveday.R
import com.discoid.weather.fiveday.model.Weather
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_weather.*
import javax.inject.Inject

class CityActivity : AppCompatActivity(), ICityPresenterView {

    @Inject
    lateinit var cityPresenter : ICityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_weather, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun display(weather: Weather?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        cityPresenter.start(this, CITY_CODE);
    }

    override fun onDestroy() {
        super.onDestroy()
        cityPresenter.close()
    }

    companion object {
        val CITY_CODE = CityCodes.getDefaultCity()
    }
}

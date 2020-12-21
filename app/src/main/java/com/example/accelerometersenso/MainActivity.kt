package com.example.accelerometersenso

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,SensorEventListener{

    lateinit var sensor :Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


         if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER) != null){
            sensor  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }


        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor!!.type == Sensor.TYPE_ACCELEROMETER){
            val x  =event.values[0]
            val y  =event.values[1]
            val z  =event.values[2]


            txt_number.text = "x = $x\ny = $y\nz = $z"

            if (z >= 9.8 || z <= -9.8) {
                tv_imageX.visibility = View.GONE
                tv_imageY.visibility = View.GONE
            } else if (y > 0 && x.toInt() == 0) {
                tv_imageY.visibility = View.VISIBLE
                tv_imageX.visibility = View.GONE
                tv_imageY.setImageResource(R.drawable.ic_arrow_up)
            } else if (y < 0 && x.toInt() == 0) {
                tv_imageY.visibility = View.VISIBLE
                tv_imageX.visibility = View.GONE
                tv_imageY.setImageResource(R.drawable.ic_arrow_down)
            } else if (x > 0 && y.toInt() == 0) {
                tv_imageX.visibility = View.VISIBLE
                tv_imageY.visibility = View.GONE
                tv_imageX.setImageResource(R.drawable.ic_arrow_forward)
            } else if (x < 0 && y.toInt() == 0) {
                tv_imageX.visibility = View.VISIBLE
                tv_imageY.visibility = View.GONE
                tv_imageX.setImageResource(R.drawable.ic_arrow_back)
            } else if (x > 0 && y > 0) {
                tv_imageX.visibility = View.VISIBLE
                tv_imageY.visibility = View.VISIBLE
                tv_imageX.setImageResource(R.drawable.ic_arrow_forward)
                tv_imageY.setImageResource(R.drawable.ic_arrow_up)
            } else if (x < 0 && y > 0) {
                tv_imageX.visibility = View.VISIBLE
                tv_imageY.visibility = View.VISIBLE
                tv_imageX.setImageResource(R.drawable.ic_arrow_back)
                tv_imageY.setImageResource(R.drawable.ic_arrow_up)
            } else if (y < 0 && x >0 ) {
                tv_imageX.visibility = View.VISIBLE
                tv_imageY.visibility = View.VISIBLE
                tv_imageX.setImageResource(R.drawable.ic_arrow_forward)
                tv_imageY.setImageResource(R.drawable.ic_arrow_down)
            } else if (y < 0 && x < 0) {
                tv_imageX.visibility = View.VISIBLE
                tv_imageY.visibility = View.VISIBLE
                tv_imageX.setImageResource(R.drawable.ic_arrow_back)
                tv_imageY.setImageResource(R.drawable.ic_arrow_down)
            }


        }
    }
}
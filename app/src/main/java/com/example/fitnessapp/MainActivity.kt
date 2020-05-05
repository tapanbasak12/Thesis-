package com.example.fitnessapp

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_steps.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    var running = false
    var sensorManager:SensorManager? = null
    var str: String  = " "
    private lateinit var stepsValue: TextView
    private lateinit var username: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)
        val addtoblockchain = findViewById(R.id.addtoblockchain) as Button
        val BtnviewAllData = findViewById(R.id.btnViewAllData) as Button


        var bundle :Bundle ?=intent.extras
        var message = bundle!!.getString("username") // 1
        var strUser: String = intent.getStringExtra("username")


        stepsValue = findViewById(R.id.textviewSteps)
        username= findViewById(R.id.usernameid)

        username.setText("Welcome\n\n"+ strUser)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        addtoblockchain.setOnClickListener {
            val value: String = strUser+ str

            val intent = Intent(this, nodeTestActivity::class.java)
            intent.putExtra("value", value)
            startActivity(intent)
        }

        BtnviewAllData.setOnClickListener {

            val intent = Intent(this, ViewAllDataActivity::class.java)
            startActivity(intent)
        }




    }

    override fun onResume() {
        super.onResume()
        running = true
                                                    var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(this, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (running) {
            var steps= event.values[0]
            var calories= steps*0.04
            var distance= steps*0.762
            stepsValue.setText("Steps= " +steps+ "\nCalories="+ calories+ "\nDistance="+ distance)
            str= ":" +steps+ ":"+  calories+ ":"+ distance

        }


    }
}


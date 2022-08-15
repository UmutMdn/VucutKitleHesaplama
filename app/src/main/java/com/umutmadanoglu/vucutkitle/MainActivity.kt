package com.umutmadanoglu.vucutkitle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.umutmadanoglu.vucutkitle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.buttonCalculate.setOnClickListener {

            if (binding.inputWeight.text.isNotEmpty() && binding.inputHeight.text.isNotEmpty()){
                val heightIn=binding.inputHeight.text.toString().toInt()
                val weightIn=binding.inputWeight.text.toString().toInt()
                val imb=calculateIMB(heightIn,weightIn)

                binding.gorunmez2.text=imb.toString()
                binding.gorunmez2.visibility=View.VISIBLE


                if (imb<18.5){
                    binding.gorunmez3.text="Under Weight"
                }else if(imb>=18.5 && imb<24.9){
                    binding.gorunmez3.text="Healthy"
                }else if(imb>=24.9 && imb<30){
                    binding.gorunmez3.text="Overweight"
                }else if (imb>=30){
                    binding.gorunmez3.text="Suffering from obesity"
                }
                  binding.gorunmez3.visibility=View.VISIBLE
                  binding.gorunmez1.visibility=View.VISIBLE
                  binding.buttonCalculate.visibility=View.GONE
                  binding.buttonRecalculate.visibility=View.VISIBLE

            }else{
                val hata=AlertDialog.Builder(this)
                .setTitle("HATA")
                .setMessage("Please enter weight and height")
                .setPositiveButton("Yes"){dialog,button->
                    dialog.dismiss()
                }
                hata.show()
            }



        }
         binding.buttonRecalculate.setOnClickListener {
             resetApp()
         }

    }





    private fun resetApp(){

        binding.buttonCalculate.visibility=View.VISIBLE
        binding.buttonRecalculate.visibility=View.GONE
        binding.inputHeight.text.clear()
        binding.inputWeight.text.clear()
        binding.gorunmez3.text=""
        binding.gorunmez2.text=""
        binding.gorunmez1.visibility=View.GONE

    }


    private fun calculateIMB(height:Int,weight:Int):Float{
        val heightInMetre = height.toFloat() / 100
        val BMI = weight.toFloat() / (heightInMetre * heightInMetre)

        return BMI
    }

}
package com.nyandori.quoteease

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nyandori.quoteease.databinding.ActivityMainBinding
import com.nyandori.quoteease.ui.theme.QuoteEaseTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getQuote()

        binding.nextBtn.setOnClickListener {
            getQuote()
        }

    }

    private  fun getQuote(){
    setInProgress(true)
        GlobalScope.launch{
            try{

                val response = RetrofitInstance.quoteApi.getRandomQuote()
                runOnUiThread {
                    setInProgress(false)
                    response.body()?.first()?.let {
                        setUI(it)
                    }
                }


            }catch (e:Exception){
                setInProgress(false)
                Toast.makeText(this@MainActivity, "Something is wrong", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun setUI(quote:QuoteModel){
        binding.quoteTv.text = quote.q
        binding.authorTv.text = quote.a
    }

    private fun setInProgress(progress:Boolean){
        if (progress){
            binding.progressBar.visibility = View.GONE
            binding.nextBtn.visibility = View.GONE



        }else{
            binding.progressBar.visibility = View.GONE
            binding.nextBtn.visibility = View.VISIBLE


        }
    }
}


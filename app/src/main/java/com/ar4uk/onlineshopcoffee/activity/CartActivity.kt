package com.ar4uk.onlineshopcoffee.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar4uk.onlineshopcoffee.R
import com.ar4uk.onlineshopcoffee.adapter.CartAdapter
import com.ar4uk.onlineshopcoffee.databinding.ActivityCartBinding
import com.ar4uk.onlineshopcoffee.databinding.ActivityDetailBinding
import com.ar4uk.onlineshopcoffee.helper.ChangeNumberItemsListener
import com.ar4uk.onlineshopcoffee.helper.ManagmentCart

class CartActivity : BaseActivity() {

    lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        with(binding) {
            rcCart.layoutManager =
                LinearLayoutManager(
                    this@CartActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )

            rcCart.adapter = CartAdapter(managmentCart.getListCart(), this@CartActivity, object: ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0

        tax = Math.round((managmentCart.getTotalFee() * percentTax)*100) / 100.0

        val total = Math.round((managmentCart.getTotalFee() + tax + delivery)*100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        with(binding) {
            totalFreeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }
}
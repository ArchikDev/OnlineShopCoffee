package com.ar4uk.onlineshopcoffee.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar4uk.onlineshopcoffee.Model.ItemsModel
import com.ar4uk.onlineshopcoffee.R
import com.ar4uk.onlineshopcoffee.ViewModel.MainViewModel
import com.ar4uk.onlineshopcoffee.adapter.PopularAdapter
import com.ar4uk.onlineshopcoffee.adapter.SizeAdapter
import com.ar4uk.onlineshopcoffee.databinding.ActivityDetailBinding
import com.ar4uk.onlineshopcoffee.databinding.ActivityMainBinding
import com.ar4uk.onlineshopcoffee.helper.ManagmentCart
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailActivity : BaseActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        bundle()

        initSizeList()

    }

    private fun initSizeList() {
        val sizeList = ArrayList<String>()

        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(
                this@DetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        val colorList = ArrayList<String>()

        for (imageUrl in item.picUrl!!) {
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(binding.picDetail)
    }

    @SuppressLint("SetTextI18n")
    private fun bundle() {
        binding.apply {
            item = intent.getParcelableExtra("object")!!

            titleDetail.text = item.title
            descriptionDetail.text = item.description
            priceDetail.text = "$" + item.price
            ratingBar.rating = item.rating.toFloat()

            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(counterDetail.text.toString())
                managmentCart.insertItems(item)
            }

            incDetail.setOnClickListener {
                counterDetail.text = (item.numberInCart + 1).toString()

                item.numberInCart++
            }

            decDetail.setOnClickListener {
                if (item.numberInCart > 0) {
                    counterDetail.text = (item.numberInCart - 1).toString()

                    item.numberInCart--
                }

            }

            btnBackDetail.setOnClickListener {
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }
        }
    }
}
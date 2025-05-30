package io.dnatechnology.dnataskandroid.ui.viewbased

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import io.dnatechnology.dnataskandroid.databinding.ActivityRootViewBinding
import io.dnatechnology.dnataskandroid.ui.viewmodel.ProductsModel
import kotlinx.coroutines.launch

class RootViewActivity : AppCompatActivity() {

    private lateinit var productsModel: ProductsModel

    private lateinit var adapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productsModel = ViewModelProvider(this).get(ProductsModel::class.java)

        val viewBinding = ActivityRootViewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsModel.products.collect { data ->
                    adapter = ProductsListAdapter(data ?: listOf())
                    viewBinding.recyclerView.adapter = adapter
                }
            }
        }

        productsModel.getProducts()
    }
}

package io.dnatechnology.dnataskandroid.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dnatechnology.dnataskandroid.ui.theme.Black
import io.dnatechnology.dnataskandroid.ui.theme.DNATaskAndroidTheme
import io.dnatechnology.dnataskandroid.ui.theme.MainBackground
import io.dnatechnology.dnataskandroid.ui.theme.White
import io.dnatechnology.dnataskandroid.ui.viewmodel.ProductsModel

class RootComposeActivity : ComponentActivity() {

    private val productsModel: ProductsModel by viewModels<ProductsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNATaskAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainBackground
                ) {
                    ProductsView(productsModel = productsModel)
                }
            }
        }
    }
}

@Composable
fun ProductsView(productsModel: ProductsModel) {
    DNATaskAndroidTheme {
        LaunchedEffect(Unit) {
            productsModel.getProducts()
        }

        val products = productsModel.products.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (products != null) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (product in products) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 8.dp)
                                .background(White)
                                .border(1.dp, Black)
                                .clickable {
                                    productsModel.addToCart(product.productID)
                                }
                        ) {
                            Text(
                                text = product.toString(),
                                color = Black,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            } else {
                Text(text = "LOADING")
            }


            Row(
                Modifier
                    .background(White)
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(io.dnatechnology.dnataskandroid.R.string.pay), color = Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DNATaskAndroidTheme {
        ProductsView(productsModel = ProductsModel())
    }
}

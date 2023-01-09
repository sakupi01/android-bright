package com.sakura.myandroidapp

import android.media.DrmInitData
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakura.myandroidapp.ui.theme.MyAndroidAppTheme
import me.nikhilchaudhari.library.NeuInsets
import me.nikhilchaudhari.library.neumorphic
import me.nikhilchaudhari.library.shapes.Punched

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAndroidAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Display()
                }
            }
        }
    }
}
data class Me(val category_icon: Int, val category_name: String, val info: String)

val my_info = listOf(
    Me(R.drawable.mortarboard, "Edu: ", "UTS"),
    Me(R.drawable.it, "Major: ","IT" ),
    Me(R.drawable.age,"Age: ","21"),
    Me(R.drawable.coffee_beans,"Fav: ","Coffee"),
    Me(R.drawable.countries,"Home: ","🇯🇵"),
)



@Composable
fun InfoCardContent(){
    // Url handler to LinkedIn bio
    val uriHandler = LocalUriHandler.current
    val url = "https://www.linkedin.com/in/sakura-a-96b41b243/"

    // Whole info scope
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 27.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        // Each info scope
        items(my_info){el ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ){
                Image(painter = painterResource(id = el.category_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .size(35.dp),
                    colorFilter = ColorFilter.tint(Color.Black.copy(0.6f))
                )
                Text(text = el.category_name, color = Color.Black.copy(0.6f), fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
                Text(text = el.info, color = Color.Black.copy(0.6f), fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
            }

        }
        // LinkedIn Button
        item{
            ExtendedFloatingActionButton(
                onClick = { uriHandler.openUri(url) },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
                backgroundColor = Color(255, 206, 206).copy(0.7f),
                contentColor = Color.White,
                text = { Text("LinkedIn Bio",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold) }
            )
        }

    }
}

@Composable
fun InfoCard(){
    Card(
        modifier = Modifier
            .neumorphic(
                elevation = 6.dp,
                lightShadowColor = Color.White.copy(0.7f),
                darkShadowColor = Color.Gray.copy(0.4f),
                neuInsets = NeuInsets(5.dp, 7.dp),
                neuShape =
                // Punched shape
                Punched.Rounded(radius = 27.dp)
            )
            .clip(
                RoundedCornerShape(30.dp)
            )
    ) {
        Box( // To make background gradient
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(206, 196, 225),
                            Color(255, 255, 255),
                            Color(255, 206, 206)
                        ),
                        start = Offset.Zero, end = Offset.Infinite
                    )
                )
                .padding(vertical = 10.dp, horizontal = 30.dp)
        ){
            InfoCardContent()
        }
    }
}

@Composable
fun Greeting() {
    Text("Hello! I am",
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        color = Color.Black.copy(0.6f)
    )
    Text("Sakura Adachi",
        fontSize = 30.sp,
        color = Color.DarkGray,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MyImage() {
    Card(
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .size(150.dp)
            .neumorphic(
                elevation = 6.dp,
                neuInsets = NeuInsets(5.dp, 5.dp),
                darkShadowColor = Color.Gray.copy(0.3f),
                neuShape =
                Punched.Rounded(radius = 50.dp)
            )
        ,
    ) {
        Image(
            painter = painterResource(R.drawable.me_inhackathon),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .aspectRatio(1f)
            ,
            contentDescription = "Sakura's Image",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Profile(my_info: List<Me>){

    // Display Background
        Column(
            modifier = Modifier
                .background(Color(235, 235, 235))
                .padding(horizontal = 25.dp, vertical = 80.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyImage()
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
            Greeting()
            Spacer(Modifier.fillMaxHeight(0.2f))
            InfoCard()
    }

}

@Composable
fun Display(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        Profile(my_info)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAndroidAppTheme {
        Display()
    }
}
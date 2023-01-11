package com.sakura.myandroidapp.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.CardDefaults
//import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sakura.myandroidapp.R
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
                    Navigation()
                }
            }
            }
        }
}


val main_purple = Color(99, 73,222)
val gradient = Brush.linearGradient(
    colors = listOf(
        Color(206, 196, 225).copy(0.6f),
        Color(255, 255, 255),
        Color(255, 206, 206).copy(0.4f)
    ),
    start = Offset.Zero, end = Offset.Infinite
)
val border = BorderStroke(1.dp, Color.DarkGray.copy(0.6f))

data class Me(val category_icon: Int, val category_name: String, val info: String)
val my_info = listOf(
    Me(R.drawable.mortarboard, "Edu: ", "UTS"),
    Me(R.drawable.it, "Major: ","IT" ),
    Me(R.drawable.age,"Age: ","21"),
    Me(R.drawable.coffee_beans,"Fav: ","Coffee"),
    Me(R.drawable.countries,"Home: ","🇯🇵"),
)

data class Skill(val icon: Int, val name: String, val description: String)
val skill_set = listOf(
    Skill(R.drawable.python, "Python3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,Lorem ipsum dolor sit amet, consectetur adipiscing elit, "),
    Skill(R.drawable.java, "Java", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,Lorem ipsum dolor sit amet, consectetur adipiscing elit, "),
    Skill(R.drawable.java_script, "JavaScript", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,Lorem ipsum dolor sit amet, consectetur adipiscing elit, "),
    Skill(R.drawable.react, "React", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,Lorem ipsum dolor sit amet, consectetur adipiscing elit, "),
    Skill(R.drawable.vue, "Vue.js", ""),
    Skill(R.drawable.html, "HTML5", ""),
    Skill(R.drawable.css, "CSS3", ""),
    Skill(R.drawable.sass, "Sass", ""),
    Skill(R.drawable.github, "Github", ""),
    Skill(R.drawable.android_logo, "Android", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,Lorem ipsum dolor sit amet, consectetur adipiscing elit, "),
    Skill(R.drawable.docker, "Docker", ""),
    Skill(R.drawable.plus, ",and so on!", ""),
)


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(){

    val navController = rememberAnimatedNavController()
        AnimatedNavHost(
            navController = navController,
            startDestination = "display1",
            enterTransition = {
                slideIn { fullSize -> IntOffset(fullSize.width, 0) }
            },
            popEnterTransition = {
                slideIn { fullSize -> IntOffset(-fullSize.width, 0) }
            },
            exitTransition = {
                slideOut { fullSize -> IntOffset(-fullSize.width, 0) }
            },
            popExitTransition = {
                slideOut { fullSize -> IntOffset(fullSize.width, 0) }
            },
            ) {
                composable(route = "display1") {
                    Display1(navController, Arrangement.Center, Alignment.CenterVertically, Alignment.CenterHorizontally,
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp))
                }
                composable(route = "display2") {
                    Display2(navController, Arrangement.Center, Alignment.CenterVertically, Alignment.CenterHorizontally,
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize())
                }
            }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    skill: Skill,
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier,
    ){
    var isClicked by remember{ mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(100.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
        ,
        onClick = {
            isClicked = !isClicked
        },
        border = border,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = center,
            horizontalAlignment = centerHorizontally
        ) {
            Row(
                verticalAlignment = centerVertically,
                horizontalArrangement = center
            ) {
//                    Text(text = "title")
                Image(painter = painterResource(id = skill.icon),
                    contentDescription = "icon",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(Color.DarkGray.copy(0.8f))
                )
            }
            if (isClicked) {
                Text(
                    text =  skill.description,
                )
            }
        }
    }
}

@Composable
fun SkillStackList(
    skills: List<Skill>,
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ){
        items(skills){ skill ->
            ExpandableCard(skill, center, centerVertically, centerHorizontally, modifier)
        }
    }
}

@Composable
fun EmailButton() {
    val context = LocalContext.current
    Button(onClick = {
        context.sendMail(to = "andasakura31@gmail.com", subject = "Subject")},
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        border = null,
        elevation = null,
    ) {
        Text(text = "andasakura31@gmail.com", color = main_purple, fontWeight = FontWeight.Bold)
    }
}

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

@Composable
fun Greeting() {
    Text("Sakura Adachi",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray
    )
    EmailButton()
}

@Composable
fun LinkedIn(){
    // Url handler to LinkedIn bio
    val uriHandler = LocalUriHandler.current
    val url = "https://www.linkedin.com/in/sakura-a-96b41b243/"
    Button(
        onClick = { uriHandler.openUri(url) },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = null
        ){
        Image(painter = painterResource(id = R.drawable.linkedin),
            contentDescription = "LinkedInBio",
            modifier = Modifier.size(20.dp),
            colorFilter = ColorFilter.tint(Color.Black.copy(0.6f),
            )
        )
    }
}

@Composable
fun InfoCardContent(
    navController: NavController,
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            BackButton(navController)
            LinkedIn()
        }

        Images()

        // Whole info scope
        WholeInfo(center, centerVertically, centerHorizontally, modifier)

        // Details
        Comment()
    }

}

@Composable
fun Comment() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " ,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp),
            color = Color.Black.copy(0.6f))
    }
}

@Composable
fun WholeInfo(
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier
) {
    LazyRow(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ){
        // Each info scope
        items(my_info){ el ->
            Card(modifier = Modifier.size(90.dp), border = border) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = centerHorizontally
                ) {
                    Row(
                        verticalAlignment = centerVertically,
                        horizontalArrangement = center
                    ){
                        Image(painter = painterResource(id = el.category_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(10.dp),
                            colorFilter = ColorFilter.tint(Color.Black.copy(0.6f))
                        )
                        Text(text = el.category_name, color = Color.Black.copy(0.6f), fontSize = 10.sp)
                    }
                    Text(text = el.info, color = Color.Black.copy(0.6f),  fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun Images() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.signeture),
            contentDescription = "signeture",
            modifier = Modifier.size(100.dp),
            alpha = 0.5F
        )
        Image(
            painter = painterResource(id = R.drawable.me_bw),
            contentDescription = "Me",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(15.dp)),
            alpha = 0.8F
        )
    }
}

@Composable
fun BackButton(navController: NavController) {
    Button(
        onClick = { navController.navigateUp() },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = null
    ) {
        Text(text = "< Back" , fontSize = 15.sp, color = Color.Black.copy(0.7f))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavCard(navController: NavController){
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(400.dp)
            .clickable { navController.navigate("display2") },
        border = border
    ) {
        Box( // To make background gradient
            modifier = Modifier
                .background(gradient)
                .padding(vertical = 20.dp, horizontal = 30.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("My Info")
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "arrow_forward",

                    )
            }
        }
    }
}

@Composable
fun MyImage() {
    Card(
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .size(160.dp)
        ,
        border = border
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
fun Profile(
    navController: NavController,
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier
    ){
    // Display Background
    Column(
        modifier = modifier,
        verticalArrangement = center,
        horizontalAlignment = centerHorizontally
    ) {
        MyImage()
        Spacer(modifier = Modifier.padding(bottom = 40.dp))
        Greeting()
        Spacer(Modifier.fillMaxHeight(0.1f))
        NavCard(navController)
        SkillStackList(skill_set, center, centerVertically, centerHorizontally, modifier)
    }

}

@Composable
fun Display2(
    navController: NavController,
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier
){
      InfoCardContent(navController , center, centerVertically, centerHorizontally, modifier)
}

@Composable
fun Display1(
    navController: NavController,
    center: Arrangement.HorizontalOrVertical,
    centerVertically: Alignment.Vertical,
    centerHorizontally: Alignment.Horizontal,
    modifier: Modifier) {
    Column(
        horizontalAlignment = centerHorizontally,
        modifier = modifier
    ) {

        Profile(navController, center, centerVertically, centerHorizontally, modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAndroidAppTheme {
        Navigation()
    }
}
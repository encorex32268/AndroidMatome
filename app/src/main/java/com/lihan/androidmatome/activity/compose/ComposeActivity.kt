package com.lihan.androidmatome.activity.compose

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lihan.androidmatome.R
import com.lihan.androidmatome.activity.compose.ui.AndroidMatomeTheme
import com.lihan.androidmatome.activity.compose.ui.shapes
import com.lihan.androidmatome.activity.retrofitapi.model.User
import com.lihan.androidmatome.activity.retrofitapi.ui.UserViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.coroutineContext

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidMatomeTheme{
                MyApp()
            }

        }
    }
}

@Composable
private fun MyApp(){
    var isShowWelComePage by rememberSaveable{ mutableStateOf(true)}
    if(isShowWelComePage){
        WelComePage(onContinueClicked = {isShowWelComePage = false})
    }else{
        UserListPage()
    }

}

@Composable
private fun WelComePage(onContinueClicked: () -> Unit){
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun UserListPage(){
    val viewModel : UserViewModel = viewModel()
    var users by remember { mutableStateOf(emptyList<User>()) }
    LaunchedEffect(Unit){
        viewModel.userData.collectLatest {
            when(it){
                is UserViewModel.Resource.Success->{ users = it.data }
                is UserViewModel.Resource.Error->{}
                is UserViewModel.Resource.Loading->{}
            }
        }
    }
    UserPageContent(users = users)

}

@Composable
private fun UserPageContent(users : List<User>){
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp)
    ){
        items(items = users){ user ->
            UserItem(user = user)
        }
    }
}



@Composable
private fun UserItem(user : User){
    var expanded by remember { mutableStateOf(false) }

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
            Text(text = user.name)
            Text(text = user.email)
            Divider()
                if (expanded) {
                    Text(
                        text = user.toString()
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }

                )
            }
        }
    }

}


@Preview(showSystemUi = false)
@Composable
fun MyPreview(){
//    RecyclerViewItem()
    LikeButton()
// SearchView
    //https://johncodeos.com/how-to-add-search-in-list-with-jetpack-compose/

}
@Composable
fun LikeButton(){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text ="Title")
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context,"Search",Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = null)
                    }
                }
            )
        }
    ) {

        Column {
            Button(
                onClick = { /*TODO*/ } ,
                Modifier.padding(8.dp)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Like",
                    modifier = Modifier.size(ButtonDefaults.IconSpacing)
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Like")
            }

            ExtendedFloatingActionButton(
                text = { Text(text = "Like")},
                onClick = { /*TODO*/ },
                icon ={
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                }
            )
        }
    }



}


//@Composable
//fun RecyclerViewItem(){
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ){
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(16.dp).fillMaxWidth()
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.shinodayuu),
//            contentDescription = "Japan",
//            modifier = Modifier.clip(
//                RoundedCornerShape(4.dp)
//            )
//            )
//        Column {
//            Text("篠田ゆう",
//                fontSize = 16.sp,
//                modifier = Modifier.padding(4.dp))
//            Text("3 minutes age",
//                color = Color.DarkGray,
//                modifier = Modifier.padding(4.dp)
//            )
//        }
//    }
//            Card(
//                modifier = Modifier.fillMaxWidth()
//                    .height(350.dp),
//                shape = RoundedCornerShape(4.dp),
//                border = BorderStroke(2.dp,Color.Blue),
//                elevation = 4.dp
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.shinodayuu),
//                    contentDescription = "Japan AV Girl",
//                    contentScale = ContentScale.FillBounds)
//
//            }
//
//        }
//
//}




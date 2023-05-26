package org.lotka.bp.presentation.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.presentation.navigation.BackPressHandler
import org.lotka.bp.presentation.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun LoginOptionsScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: LoginOptionsViewModel
) {

    val loading = viewModel.loading.value
    val context = LocalContext.current
    val dialogQueue = viewModel.dialogQueue
    val onBack = { onBackPressedFunction(context) }


    BackPressHandler(onBackPressed = onBack)

    AppTheme(
        displayProgressBar = loading,
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        dialogQueue = dialogQueue.queue.value,
    ) {
        Scaffold(
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
              //  ProfileLayout()

            }


        }
    }


}


fun onBackPressedFunction(context: Context) {

}

//
//@Composable
//fun ProfileLayout() {
//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//    ) {
//        val btnGoogle = createRef()
//        val linearLayout = createRef()
//        val textView = createRef()
//        val textView4 = createRef()
//
//        ConstraintLayout(
//            modifier = Modifier
//                .constrainAs(btnGoogle) {
//                    top.linkTo(textView4.bottom)
//                    start.linkTo(textView4.start)
//                    end.linkTo(textView4.end)
//                }
//                .background(
//                    shape = RoundedCornerShape(4.dp),
//                    color = Color.Red
//                )
//                .padding(16.dp)
//        ) {
//            Text(
//                text = "ورود / ثبت نام",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//                    .padding(8.dp),
//                style = MaterialTheme.typography.h1
//
//            )
//        }
//
//        Box(
//            modifier = Modifier
//                .constrainAs(linearLayout) {
//                    top.linkTo(btnGoogle.bottom)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//                .background(Color(0xFFE1E1E1))
//                .height(1.dp)
//                .fillMaxWidth()
//        )
//
//        Text(
//            text = "ورود یا ثبت نام",
//            modifier = Modifier
//                .constrainAs(textView) {
//                    top.linkTo(parent.top)
//                    end.linkTo(parent.end)
//                }
//                .padding(end = 18.dp),
//            style = MaterialTheme.typography.h1
//        )
//
//        Text(
//            text = "برای دسترسی به امکانات بیشتر برای جستجو و رزرو در جاباما وارد شوید یا ثبت نام کنید",
//            modifier = Modifier
//                .constrainAs(textView4) {
//                    top.linkTo(textView.bottom)
//                    start.linkTo(parent.start, margin = 20.dp)
//                    end.linkTo(parent.end, margin = 20.dp)
//                },
//            style = MaterialTheme.typography.h1,
//            lineHeight = 15.sp,
//            color = Color.Black
//        )
//    }
//}

package com.me.rickmorty.app.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.me.rickmorty.R
import com.me.rickmorty.app.App
import com.me.rickmorty.app.App.Companion.get
import com.me.rickmorty.app.ui.CharacterScreen
import com.me.rickmorty.app.ui.Routes
import com.me.rickmorty.app.ui.SplashScreen
import com.me.rickmorty.app.ui.theme.RickMortyTheme
import com.me.rickmorty.util.extensions.hasNetworkConnection
import com.me.rickmorty.util.tools.CoreListener
import com.me.rickmorty.util.tools.ErrorLoginException
import com.me.rickmorty.util.tools.ResultObject
import com.me.rickmorty.util.tools.ShowMessageException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.net.UnknownHostException

@AndroidEntryPoint
class BaseActivityCompose: ComponentActivity(), CoreListener {

    //    abstract val lifeCycleOwner: Lifecycle.State
//
    lateinit var titleAppBar: String
//

    private lateinit var navigator: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    BaseActivityView()
                }
            }
        }
    }

    private var isLoading = mutableStateOf(false)


    @Preview(showBackground = true)
    @Composable
    fun BaseActivityView() {

        navigator = rememberNavController()

        var showAppBar by remember { mutableStateOf(true) }

        var titleAppBar by remember { mutableStateOf("") }

        var context by remember { mutableStateOf<Context?>(null) }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                MaterialTheme.colorScheme.background.toArgb(),
                MaterialTheme.colorScheme.background.toArgb(),
            ),
            navigationBarStyle = SystemBarStyle.auto(
                MaterialTheme.colorScheme.background.toArgb(),
                MaterialTheme.colorScheme.background.toArgb(),
            )
        )

        Column {
            if (showAppBar) {
                AppBar(
                    title = titleAppBar,
                    onClickIcon = {
                        context.apply {
                            (this as Activity).finish()
                        }
                    }
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                //Here, all activity children will be add its content
                //ProvideActivityContent()
                NavHost(
                    navController = navigator,
                    startDestination = Routes.Splash.route
                ) {
                    composable(route = Routes.Splash.route) {
                        SplashScreen(navigator){
                            showAppBar = it
                        }
                    }
                    composable(route = Routes.Characters.route) {
                        CharacterScreen(
                            onClick = {
                                navigator.navigate(
                                    Routes.Characters.createRoute()
                                )
                            },
                            isLoading = {
                                isLoading.value = it
                            },
                            titleAppBar = {
                                titleAppBar = it
                            },
                            showAppBar = {
                                showAppBar = it
                            },
                            context = {
                                context = it
                            }
                        )
                    }
                }



                showLoading(isLoading.value)


            }
        }
    }

    @Composable
    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                //Avoid click events
                .pointerInput(Unit) {}
            ){
                CircularProgressIndicator(
                    color = Color(0xFF2E2E2E),
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
            }
        }
    }

//    @Composable
//    abstract fun ProvideActivityContent()

    @Composable
    fun <@Composable T> ObserveStateFlow(
        stateFlow: StateFlow<ResultObject<T>>,
        onSuccess: @Composable (T) -> Unit,
        onError: (Throwable) -> Unit = { },
        onEmpty: () -> Unit  = { },
        onLoading: @Composable () -> Unit
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current.lifecycle

        val result = remember {
            mutableStateOf<ResultObject<T>>(ResultObject.LoadingObject())
        }

        LaunchedEffect(result) {
//            lifecycleOwner.repeatOnLifecycle(lifeCycleOwner) {
//                stateFlow.collect {
//                    result.value = it
//                }
//            }
        }

        when (val result = result.value) {
            is ResultObject.SuccessObject -> {
                onSuccess(result.data)
            }

            is ResultObject.ErrorObject -> {
                onError(result.t)
                HandleError(result.t) {}
            }

            is ResultObject.EmptyObject -> {
                onEmpty()
            }

            is ResultObject.LoadingObject -> {
                onLoading()
            }
        }
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onError(throwable: Throwable) {
        // handleError(throwable) {}
    }

    @Composable
    fun showPopup(
        title: String,
        text: String,
        icon: Int? = null,
        onDismiss: () -> Unit
    ) {

    }

    @Composable
    open fun HandleError(
        throwable: Throwable,
        onDismiss: () -> Unit
    ) {
        var showDialog by remember {
            mutableStateOf(false)
        }

        var dialogMessage by remember {
            mutableStateOf("")
        }

        throwable.printStackTrace()

        LaunchedEffect(throwable) {
            when (throwable) {
                is ErrorLoginException -> {
                    //navigator.navigateToLogin()?.let { startActivity(it) }
                }
                is ShowMessageException -> {

                }
                is UnknownHostException -> {
                    showDialog = true
                    dialogMessage = "No internet"
                }
                else -> {
                    Timber.tag("Error").e(throwable, "Error")
                    // OnErrorDefaultCase(throwable, onDismiss)
                }
            }
        }

        if (showDialog) {
            ShowDialog(
                title = "Error",
                description = dialogMessage,
                onDismiss = {
                    // Toast.makeText(context, "Dismiss", Toast.LENGTH_SHORT).show()
                    showDialog = false
                    onDismiss()
                }
            )
        }
    }

    @Composable
    open fun OnErrorDefaultCase(
        throwable: Throwable,
        onDismiss: () -> Unit
    ) {
        if (hasNetworkConnection().not()) {
//            ShowDialog(
//                title = "Error",
//                description = "No internet",
//                onDismiss = {
//
//                }
//            )
//            showPopup(
//                getString(R.string.error),
//                getString(R.string.no_internet),
//                icon = null,
//                onDismiss = onDismiss
//            )
        } else {
//            showPopup(
//                getString(R.string.error),
//                getString(R.string.unexpected_error),
//                icon = null,
//                onDismiss = onDismiss
//            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppBar(
        title: String,
        onClickIcon: () -> Unit
    ){
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = Color(0xFFFFFFFF)
            ),
            title = {
                Text(
                    text = title,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )
            },
            modifier = Modifier.shadow(7.dp),
            navigationIcon = {
                IconButton(
                    onClick = {
                        onClickIcon()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFFFFFFFF)
                    )
                }
            }
        )
    }

    @Composable
    fun ShowDialog(
        title: String,
        description: String,
        onDismiss: () -> Unit
    ) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(
                modifier = Modifier
                    .testTag("dialog")
                    .fillMaxWidth(),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Add Task",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        TextButton(
                            onClick = {
                                onDismiss()
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                        TextButton(
                            onClick = {

                            },
                            enabled = true
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }

}
    

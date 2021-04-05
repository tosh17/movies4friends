package ru.thstdio.movies4friends.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.thstdio.movies4friends.R
import ru.thstdio.movies4friends.framework.navigation.AppRouterImpl
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: AppRouterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        M4FApp.daggerComponent.inject(this)
        val auth = Firebase.auth
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        router.setController(navController)
        if (auth.currentUser == null) {
            router.openAuth()
        } else {
            router.openAuth()
        }

//        val options = FirebaseOptions.Builder()
//            .setProjectId("movies4friend-c811b")
//            .setApplicationId("1:695547972648:android:4ea22572fa84fb1fb7f91d")
//            .setApiKey("AIzaSyBC5OLG3Ok-QsAUVIr9RolEjDvJDyuwCBM")
//            // .setDatabaseUrl(...)
//            // .setStorageBucket(...)
//            .build()
//        Firebase.initialize(this /* Context */, options, "secondary")
    }
}
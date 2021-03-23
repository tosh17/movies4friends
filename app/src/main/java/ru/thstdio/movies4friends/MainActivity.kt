package ru.thstdio.movies4friends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
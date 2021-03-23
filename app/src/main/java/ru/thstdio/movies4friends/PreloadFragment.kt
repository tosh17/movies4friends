package ru.thstdio.movies4friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PreloadFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Firebase.auth
        if (auth.currentUser == null) {
            findNavController(this).navigate(R.id.auth_graph)
        } else {
            findNavController(this).navigate(R.id.auth_graph)
        }
    }
}
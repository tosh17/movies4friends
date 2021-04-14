package ru.thstdio.core_remote_db.api

interface AppFireStore {
    fun saveUser()
    fun getUser()

    fun getMovies()
    fun saveReview()
    fun getReviews()
    fun getNews()
}
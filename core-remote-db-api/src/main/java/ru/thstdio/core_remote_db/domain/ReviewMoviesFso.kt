package ru.thstdio.core_remote_db.domain

data class ReviewMoviesFso(val id:String,
                           val moviesId:Long,
                           val userId:String,
                           val userName:String,
                           val text:String,
                           val date:Long,
                           val rating:Int
                           )

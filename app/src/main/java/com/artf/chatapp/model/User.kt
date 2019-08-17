package com.artf.chatapp.model

data class User(
    var userId: String? = null,
    var username: String? = null,
    var isOnline: Boolean? = null,
    var lastSeenTimestamp: Any? = null,
    var photoUrl: String? = null,
    var usernameList: ArrayList<String>? = null
) {
    companion object {
        fun nameToArray(username: String): ArrayList<String> {
            val usernameList = arrayListOf<String>()
            for (x in 1..username.length) usernameList.add(username.take(x))
            return usernameList
        }
    }
}
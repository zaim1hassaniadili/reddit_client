package com.example.redditek.services.Post

data class Preview(
    val enabled: Boolean,
    val images: List<Image>,
    val reddit_video_preview: RedditVideoPreview
)

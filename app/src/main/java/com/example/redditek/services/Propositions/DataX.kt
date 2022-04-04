package com.example.redditek.services.Propositions

import com.example.redditek.services.Subreddit

data class DataX(
    val accept_chats: Boolean,
    val accept_followers: Boolean,
    val accept_pms: Boolean,
    val accounts_active: Any,
    val accounts_active_is_fuzzed: Boolean,
    val active_user_count: Any,
    val advertiser_category: String,
    val all_original_content: Boolean,
    val allow_chat_post_creation: Boolean,
    val allow_discovery: Boolean,
    val allow_galleries: Boolean,
    val allow_images: Boolean,
    val allow_polls: Boolean,
    val allow_prediction_contributors: Boolean,
    val allow_predictions: Boolean,
    val allow_predictions_tournament: Boolean,
    val allow_talks: Boolean,
    val allow_videogifs: Boolean,
    val allow_videos: Boolean,
    val banner_background_color: String,
    val banner_background_image: String,
    val banner_img: String,
    val banner_size: Any,
    val can_assign_link_flair: Boolean,
    val can_assign_user_flair: Boolean,
    val collapse_deleted_comments: Boolean,
    val comment_karma: Int,
    val comment_score_hide_mins: Int,
    val community_icon: String,
    val community_reviewed: Boolean,
    val content_category: String,
    val created: Double,
    val created_utc: Double,
    val description: String,
    val description_html: String,
    val disable_contributor_requests: Boolean,
    val display_name: String,
    val display_name_prefixed: String,
    val emojis_custom_size: List<Int>,
    val emojis_enabled: Boolean,
    val free_form_reports: Boolean,
    val has_menu_widget: Boolean,
    val has_subscribed: Boolean,
    val has_verified_email: Boolean,
    val header_img: String,
    val header_size: List<Int>,
    val header_title: String,
    val hide_ads: Boolean,
    val hide_from_robots: Boolean,
    val icon_img: String,
    val icon_size: List<Int>,
    val id: String,
    val is_blocked: Boolean,
    val is_chat_post_feature_enabled: Boolean,
    val is_crosspostable_subreddit: Boolean,
    val is_employee: Boolean,
    val is_enrolled_in_new_modmail: Any,
    val is_friend: Boolean,
    val is_gold: Boolean,
    val is_mod: Boolean,
    val key_color: String,
    val lang: String,
    val link_flair_enabled: Boolean,
    val link_flair_position: String,
    val link_karma: Int,
    val mobile_banner_image: String,
    val name: String,
    val notification_level: String,
    val original_content_tag_enabled: Boolean,
    val over18: Boolean,
    val prediction_leaderboard_entry_type: String,
    val pref_show_snoovatar: Boolean,
    val primary_color: String,
    val public_description: String,
    val public_description_html: String,
    val public_traffic: Boolean,
    val quarantine: Boolean,
    val restrict_commenting: Boolean,
    val restrict_posting: Boolean,
    val should_archive_posts: Boolean,
    val show_media: Boolean,
    val show_media_preview: Boolean,
    val snoovatar_img: String,
    val snoovatar_size: Any,
    val spoilers_enabled: Boolean,
    val submission_type: String,
    val submit_link_label: String,
    val submit_text: String,
    val submit_text_html: String,
    val submit_text_label: String,
    val subreddit: Subreddit,
    val subreddit_type: String,
    val subscribers: Int,
    val suggested_comment_sort: Any,
    val title: String,
    val url: String,
    val user_can_flair_in_sr: Any,
    val user_flair_background_color: Any,
    val user_flair_css_class: Any,
    val user_flair_enabled_in_sr: Boolean,
    val user_flair_position: String,
    val user_flair_richtext: List<Any>,
    val user_flair_template_id: Any,
    val user_flair_text: Any,
    val user_flair_text_color: Any,
    val user_flair_type: String,
    val user_has_favorited: Boolean,
    val user_is_banned: Boolean,
    val user_is_contributor: Boolean,
    val user_is_moderator: Boolean,
    val user_is_muted: Boolean,
    val user_is_subscriber: Boolean,
    val user_sr_flair_enabled: Any,
    val user_sr_theme_enabled: Boolean,
    val verified: Boolean,
    val videostream_links_count: Int,
    val whitelist_status: String,
    val wiki_enabled: Boolean,
    val wls: Int
)
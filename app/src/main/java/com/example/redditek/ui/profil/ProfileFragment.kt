package com.example.redditek.ui.profil

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.redditek.MyPreference
import com.example.redditek.databinding.FragmentProfilBinding
import com.example.redditek.services.RedditService
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment(){
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myPreference = MyPreference(this.requireContext())

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewNameUser: TextView = binding.textName
        val redditServices = RedditService(this.requireContext())
        val Json = redditServices.getUserData()
        Log.d("Json", "$Json")
        val NameUser = Json.getJSONObject("subreddit").get("display_name")
        textViewNameUser.text = "Name User: $NameUser"

        val textViewDescription: TextView = binding.textDescription
        val DescriptionUser = Json.getJSONObject("subreddit").get("public_description")
        textViewDescription.text = "Description: $DescriptionUser"



        /*Log.d("OOOOOOOOOOOOOOOOOOOOO:::", Json.toString())*/
        // TO DO Aimé factoriser, ne pas utiliser shared preferences
        val textViewPms: TextView = binding.textPMS
        textViewPms.text = "Who can message you: ${myPreference.accept_private_messages}"

        val textViewIsPresent: TextView = binding.textPresence
        val isshowpresence = Json.get("pref_show_presence")
        if (isshowpresence == false) textViewIsPresent.text = "Show_presence: Yes"
        else textViewIsPresent.text = "Show_presence: No"

        val textViewOvereighteen: TextView = binding.textOvereighteen
        val overeighteen = Json.get("over_18")
        if (overeighteen == true) textViewOvereighteen.text = "Over_18: Yes"
        else textViewOvereighteen.text = "Over_18: No"


        val textViewIsRobots : TextView = binding.textRobots
        val isrobots = Json.get("hide_from_robots")
        if (isrobots == true) textViewIsRobots.text = "Hide from bots: Yes"
        else textViewIsRobots.text = "Hide from bots: No"

        val textViewSubscribers : TextView = binding.textSubscriber
        if (myPreference.enable_follower) textViewSubscribers.text = "Enable subscribers: Yes"
        else textViewSubscribers.text = "Enable subscribers: No"

        val textViewLocation : TextView = binding.textLocation
        if (myPreference.allow_location) textViewLocation.text = "Allow Location based recommandation: Yes"
        else textViewLocation.text = "Allow Location based recommandation: No"

        val ImageUser: ImageView = binding.imageView
        val ImageUrl =  Json.get("snoovatar_img")
        val url = ImageUrl.toString()
        Picasso.get().load(url).into(ImageUser);

        return root
    }

/*    private fun getUser() {
        val layout = R.layout.fragment_settings

    }*/


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
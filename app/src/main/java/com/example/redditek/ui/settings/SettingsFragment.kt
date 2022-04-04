package com.example.redditek.ui.settings

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.res.Configuration
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.redditek.databinding.FragmentSettingsBinding
import com.example.redditek.services.RedditService
import org.json.JSONException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class SettingsFragment : Fragment(){
    var TAG = "dark: "

    private var _binding: FragmentSettingsBinding? = null
        private val binding get() = _binding!!

        @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentSettingsBinding.inflate(inflater, container, false)
            val root: View = binding.root
            /*Log.d(TAG, "darkmode:$Json")*/
            val redditServices = RedditService(this.requireContext())
            val toggle_presence: Switch = binding.switch1
            toggle_presence.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    redditServices.showStatus(false)
                } else {
                   redditServices.showStatus(true)
                }
            }
            val toggle_pms : Switch = binding.switch2
            toggle_pms.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    redditServices.acceptPrivateMessage("everyone")
                } else {
                    redditServices.acceptPrivateMessage("whitelisted")
                }
            }

            val toggle_over18  : Switch = binding.switch3
            toggle_over18.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    redditServices.changeEighteen(true)
                } else {
                    redditServices.changeEighteen(false)
                }
            }

            val toggle_robots  : Switch = binding.switch4
            toggle_robots.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    redditServices.hideRobots(true)
                } else {
                    redditServices.hideRobots(false)
                }
            }

            val toggle_subscriber : Switch = binding.switch5
            toggle_subscriber.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    redditServices.allowSubscriber(true)
                } else {
                    redditServices.allowSubscriber(false)
                }
            }

            val toggle_location : Switch = binding.switch6
            toggle_location.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    redditServices.allowLocation(true)
                } else {
                    redditServices.allowLocation(false)
                }
            }

            
            return root
        }
/*    private fun getUser() {
        val layout = R.layout.fragment_settings

    }*/

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        override fun onResume() {
            super.onResume()

        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }


}
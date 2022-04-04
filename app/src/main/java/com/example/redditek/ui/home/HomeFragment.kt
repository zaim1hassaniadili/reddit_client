package com.example.redditek.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditek.MyPreference
import com.example.redditek.databinding.FragmentHomeBinding
import com.example.redditek.services.Post.Post
import com.example.redditek.services.Propositions.Propositions
import com.example.redditek.services.RetrofitInstance
import com.example.redditek.ui.home.RecyclerAdapter.onItemClickListener
import kotlinx.coroutines.NonDisposableHandle.parent
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.Locale.filter


class HomeFragment : Fragment(), onItemClickListener {

    private val TAG = "HOME"
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var access_token: String


    // This property is only valid between onCreateView and
    // onDestroyView.
    // private val binding get() = _binding!!

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var context: Context = this.requireContext()
        var gateHolder: String = ""
        var btn_new = binding.newest
        var btn_best = binding.best
        var btn_hot = binding.hot
        val recyclerView = binding.recyclerView
        var actv = binding.actv
        val myPreference = MyPreference(context)

        access_token = "Bearer " + myPreference.access_token
        adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.layoutManager = layoutManager

        fun managerFeed(subredditName: String = "", filter: String = "best") {


            lifecycleScope.launchWhenCreated {
                binding.recyclerView.isVisible = false
                binding.progressBar.isVisible = true

                Log.e(TAG, access_token)

                val response = try {
                    if (subredditName == "") {
                        RetrofitInstance.create().getFilteredMainFeed(access_token, filter)
                    } else {
                        RetrofitInstance.create()
                            .getFilteredSubFeed(access_token, subredditName, filter)
                    }

                } catch (e: IOException) {
                    Log.d(TAG, "You might not have internet connection")
                    e.printStackTrace()
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.d(TAG, "unexcepted response")
                    e.printStackTrace()
                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "Hello here" + response.body().toString())
                    var postObject: Post = response.body()!!
                    (adapter as RecyclerAdapter).posts = postObject.data.children
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e(TAG, "Response not successful")
                }
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = true
            }
        }
        managerFeed()




        actv.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.d("Auto", "beforeChange $s")

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //retrieve data s
                Log.d("Auto", "onChange $s")

                lifecycleScope.launchWhenCreated {
                    if (count > 2) {
                        val response = try {
                            RetrofitInstance.create().getPropositions(access_token, s.toString())
                        } catch (e: IOException) {
                            Log.d(TAG, "You might not have internet connection")
                            e.printStackTrace()
                            return@launchWhenCreated
                        } catch (e: HttpException) {
                            Log.d(TAG, "unexcepted response")
                            e.printStackTrace()
                            return@launchWhenCreated
                        }
                        if (response.isSuccessful && response.body() != null) {

                            var propositions: Propositions = response.body()!!
                            val props = propositions.data.children
                                .filter { it.data.display_name != null }
                                .map {
                                    it.data.display_name
                                }
                            val adapter =
                                ArrayAdapter(context, android.R.layout.simple_list_item_1, props)
                            actv.setAdapter(adapter)
                            adapter.notifyDataSetChanged()

                        } else {
                            Log.e(TAG, "Response not successful")
                        }

                    }

                }

            }

            override fun afterTextChanged(p0: Editable?) {
                actv.setOnItemClickListener { adapterView, view, i, l ->
                    actv.setText(p0.toString())
                    managerFeed(p0.toString())
                    gateHolder = p0.toString()
                    actv.setText("")
                }
            }
        })
        btn_new.setOnClickListener {
            managerFeed(gateHolder, "new")
        }
        btn_best.setOnClickListener {
            managerFeed(gateHolder, "best")
        }
        btn_hot.setOnClickListener {
            managerFeed(gateHolder, "hot")
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int, state: String, subredditName: String) {
        Log.d("Bitch ", "clicke on $position $state $subredditName")
        lifecycleScope.launchWhenCreated {
            RetrofitInstance.create().subscribe(
                access_token,
                if (state == "unsubscribe") "unsub" else "sub",
                subredditName
            )
        }

    }


}

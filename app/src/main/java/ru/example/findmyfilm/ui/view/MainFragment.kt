package ru.example.findmyfilm.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.example.findmyfilm.databinding.MainFragmentBinding
import ru.example.findmyfilm.model.NowPlaying
import ru.example.findmyfilm.model.UpComing
import ru.example.findmyfilm.viewmodel.AppState
import ru.example.findmyfilm.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner,
                Observer { state: AppState -> renderData(state) })
        viewModel.getNPFromLocaleSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val nowFilmData = appState.nowFilmData
                val upComFilmData = appState.upComFilmData
                binding.loadingLayout.visibility = View.GONE
                setNowFilmData(nowFilmData)
                setUpComFilmData(upComFilmData)
                Snackbar.make(binding.main, "Success", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                        .make(binding.main, "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") { viewModel.getNPFromLocaleSource() }
                        .show()
            }
        }
    }

    private fun setUpComFilmData(upComFilmData: UpComing) {

    }

    private fun setNowFilmData(nowFilmData: NowPlaying) {

    }
}
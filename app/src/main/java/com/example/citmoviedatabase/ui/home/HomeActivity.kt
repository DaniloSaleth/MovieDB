package com.example.citmoviedatabase.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.citmoviedatabase.R
import com.example.citmoviedatabase.databinding.ActivityHomeBinding
import com.example.citmoviedatabase.databinding.ErrorDialogBinding
import com.example.citmoviedatabase.networking.NetworkStatusChecker
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("NewApi")
class HomeActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter : HomeAdapter

    private val networkStatusChecker by lazy{
        NetworkStatusChecker(this.applicationContext?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this,binding.dlHome,R.string.open,R.string.close)
        binding.dlHome.addDrawerListener(toggle)
        toggle.syncState()


        if(networkStatusChecker.hasInternetConnection()) {
            viewModel.refreshFavorites()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            startObserverViewModel()

            binding.navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.btn_home -> {
                        viewModel.getNowPlaying(1)
                        binding.nowPaying.isChecked = true
                        binding.radioGroup.visibility = View.VISIBLE
                        binding.holderButton.visibility = View.GONE
                        binding.cvNext.visibility = View.GONE
                        binding.cvPrevious.visibility = View.GONE
                    }
                    R.id.btn_nowPlaying -> {
                        viewModel.getNowPlaying(1)
                        setupMainList("Now Playing")
                    }
                    R.id.btn_upcoming -> {
                        viewModel.getUpcoming(1)
                        setupMainList("Coming Soon")
                    }
                    R.id.btn_popular -> {
                        viewModel.getPopular(1)
                        setupMainList("Popular")
                    }
                    R.id.btn_favorites -> {
                        viewModel.getFavorites()
                        setupMainList("My Favorite List")
                    }
                    R.id.btn_watched -> Toast.makeText(applicationContext, "Clicou em Minha Lista", Toast.LENGTH_SHORT).show()
                    R.id.btn_toWatch -> Toast.makeText(applicationContext, "Clicou em À Assistir", Toast.LENGTH_SHORT).show()
                }
                binding.dlHome.closeDrawers()
                true
            }

            binding.searchMovie.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (currentFocus != null) {
                        val inputMethodManager: InputMethodManager =
                            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    }
                    viewModel.getMovieByName(binding.searchMovie.text.toString(),1)
                    setupMainList(binding.searchMovie.text.toString())
                    true
                }
                false
            }

            binding.nowPaying.setOnClickListener {
                viewModel.getNowPlaying(1)
            }

            binding.comingSoon.setOnClickListener {
                viewModel.getUpcoming(1)
            }

            viewModel.getNowPlaying(1)
        } else {
            showError("Verifique sua conexão.")
            binding.nowPaying.setOnClickListener {
                finish()
                startActivity(intent)
            }

            binding.comingSoon.setOnClickListener {
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setupMainList(listName : String){
        binding.holderButton.text = listName
        binding.holderButton.visibility = View.VISIBLE
        binding.radioGroup.visibility = View.INVISIBLE
        binding.cvNext.visibility = View.VISIBLE
        binding.cvPrevious.visibility = View.VISIBLE
        binding.next.setBackgroundColor(Color.parseColor("#40484D"))
        binding.previous.setBackgroundColor(Color.parseColor("#40484D"))
    }

    private fun startObserverViewModel(){
        viewModel.currentMovieList.observe(this){movieResponse ->
            if (movieResponse.results.isEmpty()){

            } else {
                viewModel.getGenres()
                viewModel.listGenres.observe(this){genresList ->
                    adapter = HomeAdapter(movieResponse.results, genresList)
                    addFavoriteToAdapter()
                    binding.movieRecyclerView.adapter = adapter

                    binding.shimmerFrameLayout.stopShimmer()
                    binding.shimmerFrameLayout.visibility = View.GONE

                    addFavorite()
                    removeFavorite()
                }
            }

            if(movieResponse.total_pages > movieResponse.page){
                binding.next.setBackgroundColor(Color.parseColor("#ED3838"))
                binding.next.setOnClickListener {
                    setupPagination(page = movieResponse.page+1)
                }
            } else {
                binding.next.setBackgroundColor(Color.parseColor("#40484D"))
            }

            if (movieResponse.page > 1 && movieResponse.total_pages > 1){
                binding.previous.setBackgroundColor(Color.parseColor("#ED3838"))
                binding.previous.setOnClickListener {
                    setupPagination((movieResponse.page)-1)
                }
            }else {
                binding.previous.setBackgroundColor(Color.parseColor("#40484D"))
            }
        }

        viewModel.error.observe(this){
            showError(it.message)
        }

        viewModel.currentMsg.observe(this){
            Toast.makeText( applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addFavoriteToAdapter(){
        viewModel.favorites.observe(this){
            adapter.listFavorite = it
        }
        viewModel.refreshFavorites()
    }

    private fun addFavorite(){
        adapter.addToFavorites {
            viewModel.setFavorite(it)
            viewModel.refreshFavorites()
        }
    }

    private fun removeFavorite(){
        adapter.removeFavorites {
            viewModel.removeFavorite(it)
            viewModel.refreshFavorites()
        }
    }

    private fun setupPagination(page : Int){
        var currentList = binding.holderButton.text
        if(currentList == "Now Playing"){
            viewModel.getNowPlaying(page)
        } else if( currentList == "Coming Soon"){
            viewModel.getUpcoming(page)
        } else if(currentList == "Popular"){
            viewModel.getPopular(page)
        } else if(currentList == "My Favorite List"){

        } else {
            viewModel.getMovieByName(currentList.toString(),page)
        }
    }

    private fun showError(msg : String?){
        val dialog = BottomSheetDialog(this)
        val dialogBinding = ErrorDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.dialogMsg.text = msg
        if (!(dialog.isShowing)) {
            dialog.show()
        }
    }

}
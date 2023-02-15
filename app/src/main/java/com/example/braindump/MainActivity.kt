package com.example.braindump

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.braindump.databinding.ActivityMainBinding
import com.example.braindump.db.DumpDatabase
import com.example.braindump.repository.DumpsRepository
import com.example.braindump.viewmodel.DumpsViewModelFactory
import com.example.braindump.viewmodel.DumpsViewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : DumpsViewmodel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dumpsRepository = DumpsRepository(DumpDatabase.getDatabase(this))
        val dumpsProvider = DumpsViewModelFactory(dumpsRepository)
        viewModel = ViewModelProvider(this,dumpsProvider)[DumpsViewmodel::class.java]

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
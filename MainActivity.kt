package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.preference.PreferenceManager
import com.google.android.filament.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
fun reqiuireContext(): Any {

}


// в colors добавляем <color>yellow</color>

//<MainActivity>

//<FacultyFragment>

    private var lastView: View? =null
    private fun updateCurrentView(view: View){
        lastView?.findById<ConstraintLayout>(R.id.clFaculty)?.setBackgroundColor(
            ContextCompat.getColor(reqiuireContext().R.color.white))
        lastView?.findById<ConstraintLayout>(R.id.tvFaculty)?.setBackgroundColor(
            ContextCompat.getColor(reqiuireContext().R.color.black))
        view.findViewById<ConstraintLayout>(R.id.clFaculty).setBackgroundColor(
            ContextCompat.getColor(reqiuireContext().R.color.yellow))
        view.findViewById<ConstraintLayout>(R.id.tvFaculty).setBackgroundColor(
            ContextCompat.getColor(reqiuireContext().R.color.white))
    lastView=view
    }

    <ItemHolder>
    if(faculty==viewModel.faculty)
        updateCurrentView(itemView)

    tv.setOnClickListener{
    viewModel.setCurrentFaculty(faculty)
        updateCurrentView(itemView)
    }

    val cl = View.OnClickListener{
        viewModel.setOnClickListener(faculty)
        updateCurrentView(itemView)
    }

    val icl = itemView.findViewById<ConstraintLayout>(R.id.clFaculty)
    icl.setOnClickListener(cl)
    icl.setVackground(
        ContextCompat.getColor(requireContext(), R.color.white)
    )

//<AppRepository>

    fun saveData(){
        val context = ListApp4PM_1_2425.context
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().apply{
            val gson = Gson()
            val lst=listOfFaculty-value?.items ?: listOf<Faculty>()
            val jsonString = gson.toJson(lst)
            putString(context.getString(R.string.preference-key_faculty_list), jsonString)
            apply()
        }
    }

// new KotlinClass/File -> ListApp4PM_1_2425
// <ListApp4PM_1_2425>

    class ListApp4PM_1_2425{

        init {
            instance = this
        }

        companion object{
            private var instance: MyApplication? = null

            val context
                get()= applicationContext()

            private fun applicationContext() : Context{
                return instance!!.applicationContext
            }
        }
    }

// AndroidManifest
// добавить android:name=".ListApp4PM_1_2425"

// strings
// <string name='preference_key_faculty_list'>faculty_list</>
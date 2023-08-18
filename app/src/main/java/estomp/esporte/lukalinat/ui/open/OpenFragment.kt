package estomp.esporte.lukalinat.ui.open

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import estomp.esporte.lukalinat.database.adapter.RecordAdapter
import estomp.esporte.lukalinat.database.adapter.RecycleItemDecoration
import estomp.esporte.lukalinat.database.entity.Record
import estomp.esporte.lukalinat.databinding.FragmentOpenBinding

class OpenFragment : Fragment() {
    private var _binding: FragmentOpenBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecordAdapter
    private lateinit var openViewModel: OpenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        openViewModel =
            ViewModelProvider(this)[OpenViewModel::class.java]

        _binding = FragmentOpenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        observeRecords()
    }

    private fun setRecyclerView() {
        val recyclerView = binding.recyclerViewTraining
        val recordClickListener = object : RecordAdapter.OnRecordClickListener {
            override fun onRecordClick(record: Record, position: Int) {
                watchCard(record)
            }
        }
        adapter =  RecordAdapter(recordClickListener)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(RecycleItemDecoration(2))

        recyclerView.adapter = adapter

        observeRecords()
    }

    private fun observeRecords() {
        adapter.update(openViewModel.getRecords())
        adapter.notifyDataSetChanged()
    }

    private fun watchCard(record: Record) {
        val imageView = ImageView(context)
        imageView.setImageResource(record.imageId)
        AlertDialog.Builder(context)
            .setView(imageView)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
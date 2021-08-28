package uz.projects.wallpaper.ui.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import uz.projects.wallpaper.MainActivity;
import uz.projects.wallpaper.R;
import uz.projects.wallpaper.adapter.RecyclerAdapter;
import uz.projects.wallpaper.databinding.FragmentRandomBinding;
import uz.projects.wallpaper.models.Category;
import uz.projects.wallpaper.models.ImageData;
import uz.projects.wallpaper.preference.MySharedPreference;

public class RandomFragment extends Fragment {


    private FragmentRandomBinding binding;
    private Gson gson = new Gson();
    private MySharedPreference preference;
    private List<Category> categoryList;
    private RecyclerAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRandomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        preference = MySharedPreference.getInstance(requireContext());
        String listString = preference.getNumberListString();
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        ((MainActivity) getActivity()).showBlurView();
        categoryList = gson.fromJson(listString, type);
        List<ImageData> imageDataList = categoryList.get(0).getImageDataList();
        Collections.shuffle(imageDataList);
        adapter = new RecyclerAdapter(imageDataList, new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ImageData imageData) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("image", imageData);
                Navigation.findNavController(requireView()).navigate(R.id.action_nav_random_to_imageFragment, bundle);
            }
        });

        binding.recycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
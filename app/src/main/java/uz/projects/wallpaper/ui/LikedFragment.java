package uz.projects.wallpaper.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.projects.wallpaper.MainActivity;
import uz.projects.wallpaper.R;
import uz.projects.wallpaper.adapter.RecyclerAdapter;
import uz.projects.wallpaper.databinding.FragmentLikedBinding;
import uz.projects.wallpaper.models.Category;
import uz.projects.wallpaper.models.ImageData;
import uz.projects.wallpaper.preference.MySharedPreference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikedFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public LikedFragment() {
        // Required empty public constructor
    }


    public static LikedFragment newInstance(String param1, String param2) {
        LikedFragment fragment = new LikedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Gson gson = new Gson();
    private FragmentLikedBinding binding;
    private MySharedPreference preference;
    private List<Category> categoryList;
    private RecyclerAdapter adapter;
    private List<ImageData> imageDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLikedBinding.inflate(inflater, container, false);
        preference = MySharedPreference.getInstance(requireContext());
        String listString = preference.getNumberListString();
        if (!listString.equalsIgnoreCase("")) {
            imageDataList = new ArrayList<>();
            Type type = new TypeToken<List<Category>>() {
            }.getType();
            categoryList = gson.fromJson(listString, type);
        }
        List<ImageData> dataList = categoryList.get(0).getImageDataList();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).isLiked()) {
                imageDataList.add(dataList.get(i));
            }
        }

        adapter = new RecyclerAdapter(imageDataList, new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ImageData imageData) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("image", imageData);
                Navigation.findNavController(requireView()).navigate(R.id.action_likedFragment_to_imageFragment, bundle);
            }
        });
        binding.recycler.setAdapter(adapter);

        return binding.getRoot();
    }
}
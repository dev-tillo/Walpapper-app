package uz.projects.wallpaper.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.projects.wallpaper.MainActivity;
import uz.projects.wallpaper.adapter.CategoryAdapter;
import uz.projects.wallpaper.databinding.FragmentHomeBinding;
import uz.projects.wallpaper.databinding.ItemTabBinding;
import uz.projects.wallpaper.models.Category;
import uz.projects.wallpaper.models.ImageData;
import uz.projects.wallpaper.preference.MySharedPreference;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private MySharedPreference preference;
    private Gson gson = new Gson();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        preference = MySharedPreference.getInstance(requireContext());
        if (preference.getNumberListString().equalsIgnoreCase("")) {
            loadCategories();
            String s = gson.toJson(categoryList);
            preference.setUserList(s);
        } else {
            Type type = new TypeToken<List<Category>>() {
            }.getType();
            categoryList = gson.fromJson(preference.getNumberListString(), type);
        }
        categoryAdapter = new CategoryAdapter(requireActivity(), categoryList);
        binding.viewpager.setAdapter(categoryAdapter);



        new TabLayoutMediator(binding.tabLayout, binding.viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                ItemTabBinding itemTabBinding = ItemTabBinding.inflate(getLayoutInflater());
                tab.setCustomView(itemTabBinding.getRoot());
                itemTabBinding.text.setText(categoryList.get(position).getTitle());
                if (position == 0) {
                    itemTabBinding.circle.setVisibility(View.VISIBLE);
                    itemTabBinding.text.setTextColor(Color.WHITE);
                } else {
                    itemTabBinding.circle.setVisibility(View.INVISIBLE);
                    itemTabBinding.text.setTextColor(Color.parseColor("#808a93"));
                }
            }
        }).attach();
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ItemTabBinding itemTabBinding = ItemTabBinding.bind(tab.getCustomView());
                itemTabBinding.circle.setVisibility(View.VISIBLE);
                itemTabBinding.text.setTextColor(Color.WHITE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ItemTabBinding itemTabBinding = ItemTabBinding.bind(tab.getCustomView());
                itemTabBinding.circle.setVisibility(View.INVISIBLE);
                itemTabBinding.text.setTextColor(Color.parseColor("#808a93"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    private void loadCategories() {
        categoryList = new ArrayList<>();
        String url;
        String website = "unsplash.com";
        String author = "Anonymous";
        String download = "123456789";
        String size = "3 MB";
        List<ImageData> imageDataList = new ArrayList<>();
        //animals
        url = "https://img.alltor.me/img/2021-07/04/07ram07z2is4hyr0510jke6vt.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://img.alltor.me/img/2021-07/04/qiqkiljf4kr4guoznvn6vivus.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));


        url = "https://img.alltor.me/img/2021-07/04/7ynsh41xe0ne3xv43fhst47of.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));


        url = "https://img.alltor.me/img/2021-07/04/f57af1yaxeswfnq5xqjz09d1k.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));


        url = "https://img.alltor.me/img/2021-07/04/a2dbkj6hjb8bvcuih9zcvflkf.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));


        url = "https://img.alltor.me/img/2021-07/04/ko6uoxyry91tgave9xzbh9jfx.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));


        url = "https://img.alltor.me/img/2021-07/04/bflx1thxirqdfafq2nkiju1gb.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));


        url = "https://img.alltor.me/img/2021-07/04/395wq72trl1fuivxn2xthp7ry.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://img.alltor.me/img/2021-07/04/gcondhm9535vtnd6ejpn5c2w1.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://img.alltor.me/img/2021-07/04/jc2jfdulskz1s9ctqp7kbns1d.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));
        categoryList.add(new Category("Animals", imageDataList));

        List<ImageData> allImage = new ArrayList<>(imageDataList);


        //cars

        imageDataList = new ArrayList<>();

        url = "https://wallpaperaccess.com/full/184117.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://i.pinimg.com/originals/d3/96/37/d396371a117c1d9b2169142e3b7ebd4f.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://wallpapercave.com/wp/wp6641680.png";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://wallpapercave.com/wp/wp4849001.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://wallpaperaccess.com/full/1215863.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://cdn.wallpapersafari.com/68/16/9Fros7.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://www.teahub.io/photos/full/270-2704734_new-hd-car-wallpaper-unique-cars-of-images.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://car-pictures-download.com/wp-content/uploads/Lamborghini-Aventador-car-phone-wallpaper.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://img.wallpapersafari.com/phone/1080/1920/93/91/mXLVQb.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://image.winudf.com/v2/image1/Y29tLm1laXRvLmNhcndhbGxwYXBlcnM0azIwMjFfc2NyZWVuXzBfMTYxMjE5OTY1M18wNjg/screen-0.jpg?fakeurl=1&type=.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://c0.wallpaperflare.com/preview/606/60/924/artistic-asphalt-automobiles-car-lights.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));

        url = "https://image.winudf.com/v2/image1/bXVkYS5jb20uYmVzdC50b3AuaGQubWVyY2VkZXN3YWxscGFwZXJzX3NjcmVlbl8wXzE1NTM5MzY1OTBfMDY2/screen-0.jpg?fakeurl=1&type=.jpg";
        imageDataList.add(new ImageData(url, website, author, download, size, false));
        categoryList.add(new Category("Cars", imageDataList));

        allImage.addAll(imageDataList);
        //dota 2
        imageDataList = new ArrayList<>();
        String s = "https://i.pinimg.com/736x/a3/ce/94/a3ce94f735eee60659776ac3318e581f.jpgLLLLL" +
                "https://pictozone.com/files/wallpapers/dota-2/1-Dota%202-1080x1920.jpgLLLLL" +
                "https://wallpapercave.com/wp/wp2964777.jpgLLLLL" +
                "https://i.pinimg.com/originals/9b/a6/5e/9ba65e5fc98d63795a7c69893e5c2a59.jpgLLLLL" +
                "https://cdn.wallpapersafari.com/30/76/XeNkQI.jpgLLLLL" +
                "https://img.alltor.me/img/2021-07/04/nxb1t8fyc8hrqjsesple1fedv.jpgLLLLL" +
                "https://wallpapercave.com/wp/wp6028912.jpgLLLLL" +
                "https://www.teahub.io/photos/full/97-979160_dota-2-heroes-wallpaper-dota-2-wallpaper-for.jpgLLLLL" +
                "https://wallpapercave.com/wp/wp6435604.jpgLLLLL" +
                "https://image.winudf.com/v2/image/Y29tLmFsaXNvbmx3YXRzb24uZG90YTJ3YWxscGFwZXJfc2NyZWVuXzBfMTUzMzIxMjI5MV8wOTM/screen-0.jpg?fakeurl=1&type=.jpgLLLLL" +
                "https://cdn.wallpapersafari.com/25/61/2Jyp0Z.jpgLLLLL" +
                "https://androidhdwallpapers.com/media/uploads/2016/10/Dota-2-007.jpg";
        String[] massiv = s.split("LLLLL");
        for (int i = 0; i < massiv.length; i++) {
            url = massiv[i];
            imageDataList.add(new ImageData(url, website, author, download, size, false));
        }
        categoryList.add(new Category("Dota 2", imageDataList));
        allImage.addAll(imageDataList);
        //nature
        imageDataList = new ArrayList<>();
        s = "https://wallpaperaccess.com/full/95154.jpgLLLLL" +
                "https://wallpapercave.com/wp/wp4828112.jpgLLLLL" +
                "https://i.pinimg.com/originals/1e/25/2c/1e252c3b14ecb5cf25f561288db74fb3.pngLLLLL" +
                "https://wallpapercave.com/wp/wp5186899.jpgLLLLL" +
                "https://wallpaperaccess.com/full/817866.jpgLLLLL" +
                "https://i.pinimg.com/originals/c6/55/a8/c655a8f6d2f7ed730f64881602129547.jpgLLLLL" +
                "https://www.wallpapertip.com/wmimgs/15-152205_live-nature-wallpapers-sony-xperia-smartphone-wallpapers-mountain.jpgLLLLL" +
                "https://wallpaperaccess.com/full/157077.jpgLLLLL" +
                "https://wallpapercave.com/wp/wp4828113.jpgLLLLL" +
                "https://www.teahub.io/photos/full/200-2000607_nature-phone-wallpaper-4k.jpgLLLLL" +
                "https://i2.wp.com/www.wallpapertip.com/wmimgs/3-34972_smartphone-wallpaper-nature-hd.jpg";
        massiv = s.split("LLLLL");
        for (int i = 0; i < massiv.length; i++) {
            url = massiv[i];
            imageDataList.add(new ImageData(url, website, author, download, size, false));
        }
        categoryList.add(new Category("Nature", imageDataList));
        allImage.addAll(imageDataList);

        //cs go

        imageDataList = new ArrayList<>();
        s = "https://wallpaperaccess.com/full/631679.jpgLLLL" +
                "https://i.pinimg.com/736x/3d/01/ed/3d01ed1af8454ecd15c456453bf56160.jpgLLLL" +
                "https://i.pinimg.com/originals/ee/d4/75/eed4757c336144abb435922560df0bcb.jpgLLLL" +
                "https://wallpaperaccess.com/full/897839.pngLLLL" +
                "https://wallpaperaccess.com/full/897833.jpgLLLL" +
                "https://i.pinimg.com/originals/74/62/f4/7462f4395adc7b5ef1fed40952d81de9.pngLLLL" +
                "https://img.alltor.me/img/2021-07/04/8oihd2p0vibuev9wm5i1xe7jc.jpgLLLL" +
                "https://img.alltor.me/img/2021-07/04/vfnxyrudz2gqhfcqhwv3vifpr.jpgLLLL" +
                "https://img.alltor.me/img/2021-07/04/7dv3myorbcgswjioqzvw3c4oy.jpgLLLL" +
                "https://wallpaper.dog/large/965643.jpgLLLL" +
                "https://wallpaperaccess.com/full/897842.jpgLLLL" +
                "https://i.pinimg.com/originals/54/39/21/543921dbfbd117042d9eac66499562ee.jpgLLLL";
        massiv = s.split("LLLL");
        for (int i = 0; i < massiv.length; i++) {
            url = massiv[i];
            imageDataList.add(new ImageData(url, website, author, download, size, false));
        }
        categoryList.add(new Category("CS:GO", imageDataList));
        allImage.addAll(imageDataList);


        //night

        imageDataList = new ArrayList<>();
        s = "https://i.pinimg.com/originals/49/15/5d/49155d4b9f88e2e3a098e3bf7db37444.jpgLLLL" +
                "https://i.pinimg.com/originals/da/b6/48/dab648dd0e246bae0a42f27c56ba0dcf.jpgLLLL" +
                "https://wallpaperaccess.com/full/929229.jpgLLLL" +
                "https://wallpaperaccess.com/full/698238.jpgLLLL" +
                "https://wallpaper.dog/large/5443274.jpgLLLL" +
                "https://wallpapercave.com/wp/wp5833268.pngLLLL" +
                "https://www.teahub.io/photos/full/134-1349228_night-sky-wide-mountain-star-shining-nature-android.jpgLLLL" +
                "https://wallpaperaccess.com/full/3434296.jpgLLLL" +
                "https://i.pinimg.com/originals/85/8b/48/858b48740b90c816ac1ca715816d2c4e.jpgLLLL" +
                "https://i.pinimg.com/originals/60/88/e9/6088e936878f81c4a68a5fbeaccc37d9.pngLLLL" +
                "https://wallpapercave.com/wp/wp5339453.jpgLLLL" +
                "https://i.pinimg.com/originals/ef/77/2d/ef772def079741369471592954cee935.jpgLLLL" +
                "https://4.bp.blogspot.com/-QhBXSs-o1kM/XGso036WicI/AAAAAAAACns/d-Vn7t_C924OqGSL_WA51gt3WZ3FCXNSQCKgBGAs/w2160-h3840-c/aurora-borealis-night-sky-stars-lake-nature-scenery-4K-163.jpg";
        massiv = s.split("LLLL");
        for (int i = 0; i < massiv.length; i++) {
            url = massiv[i];
            imageDataList.add(new ImageData(url, website, author, download, size, false));
        }
        categoryList.add(new Category("Technology", imageDataList));
        allImage.addAll(imageDataList);

        //technology

        imageDataList = new ArrayList<>();
        s = "https://wallpaperaccess.com/full/4156314.jpgLLLL" +
                "https://wallpaperaccess.com/full/3434296.jpgLLLL" +
                "https://i.pinimg.com/originals/3b/ed/b1/3bedb1ae9899443fae7a641fbaa4b1ef.jpgLLLL" +
                "https://i.pinimg.com/originals/93/f5/9f/93f59f2869f8402f21cebb9d4476a39b.jpgLLLL" +
                "https://wallpaperaccess.com/full/4156317.jpgLLLL" +
                "https://i.pinimg.com/originals/46/ad/cd/46adcdf3759f47ba7a68870d7194a88f.jpgLLLL" +
                "https://wallpaperaccess.com/full/913829.jpgLLLL" +
                "https://wallpaperaccess.com/full/5575484.jpgLLLL" +
                "https://i.pinimg.com/originals/2b/e6/c2/2be6c24d9eb1354206a8b6dd69aaf271.jpgLLLL" +
                "https://mfiles.alphacoders.com/638/638121.jpgLLLL" +
                "https://wallpaper.dog/large/5443449.jpgLLLL" +
                "https://cdn.wallpapersafari.com/42/92/ZwyQPX.jpegLLLL" +
                "https://wallpapercave.com/wp/wp2572055.jpg";
        massiv = s.split("LLLL");
        for (int i = 0; i < massiv.length; i++) {
            url = massiv[i];
            imageDataList.add(new ImageData(url, website, author, download, size, false));
        }
        categoryList.add(new Category("Winter", imageDataList));
        allImage.addAll(imageDataList);
        categoryList.add(0, new Category("All", allImage));
        categoryList.add(0, new Category("New", allImage));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
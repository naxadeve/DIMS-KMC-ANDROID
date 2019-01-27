package np.com.naxa.iset.mycircle.contactlistdialog;


import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import np.com.naxa.iset.R;
import np.com.naxa.iset.event.MyCircleContactAddEvent;
import np.com.naxa.iset.mycircle.ContactModel;
import np.com.naxa.iset.viewmodel.MyCircleContactViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedDialog extends DialogFragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnClose;
    MyCircleContactViewModel myCircleContactViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.contact_list_tab_dialog_layout, container, false);


        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootview.findViewById(R.id.masterViewPager);
        btnClose = (Button) rootview.findViewById(R.id.btnClose);

        CustomAdapter adapter = new CustomAdapter(getChildFragmentManager());

        myCircleContactViewModel = ViewModelProviders.of(this).get(MyCircleContactViewModel.class);


        myCircleContactViewModel.getAllRegisteredContacts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSubscriber<List<ContactModel>>() {
                    @Override
                    public void onNext(List<ContactModel> contactModels) {

                        adapter.addFragment("Registered", CustomFragment.createInstance(contactModels, true));
                        myCircleContactViewModel.getAllUnRegisteredContacts()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new DisposableSubscriber<List<ContactModel>>() {
                                    @Override
                                    public void onNext(List<ContactModel> contactModels) {
                                        adapter.addFragment("UnRegistered", CustomFragment.createInstance(contactModels, false));
                                        adapter.notifyDataSetChanged();

                                        viewPager.setAdapter(adapter);
                                        tabLayout.setupWithViewPager(viewPager);
                                    }

                                    @Override
                                    public void onError(Throwable t) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the dialog.
                EventBus.getDefault().post(new MyCircleContactAddEvent.MyCircleContactDialogCloseClick());

            }
        });

//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(rootview.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        return rootview;
    }


}

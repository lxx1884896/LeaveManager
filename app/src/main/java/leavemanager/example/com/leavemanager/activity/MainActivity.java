package leavemanager.example.com.leavemanager.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import leavemanager.example.com.leavemanager.R;

public class MainActivity extends Activity {
    private RadioGroup mRadioGroup = null;
    private RadioButton mRadioLeave = null;
    private RadioButton mRadioPermit = null;
    private RadioButton mRadioReportback = null;
    private RadioButton mRadioMine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = findViewById(R.id.radio_group);
        mRadioLeave = findViewById(R.id.leave_rb);
        mRadioPermit = findViewById(R.id.permit_rb);
        mRadioReportback = findViewById(R.id.reportback_rb);
        mRadioMine = findViewById(R.id.mine_rb);
        mRadioGroup.setOnCheckedChangeListener(new MyRadioGroupListener());
    }

    private void setTabState() {
        setHomeState();
        setLocationState();
        setLikeState();
        setMeState();
    }

    /**
     * set tab home state
     */
    private void setHomeState() {
        if (mRadioLeave.isChecked()) {
            mRadioLeave.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioLeave.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setLocationState() {
        if (mRadioPermit.isChecked()) {
            mRadioPermit.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioPermit.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setLikeState() {
        if (mRadioReportback.isChecked()) {
            mRadioReportback.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioReportback.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setMeState() {
        if (mRadioMine.isChecked()) {
            mRadioMine.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioMine.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }
    class MyRadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (checkedId) {
                case R.id.leave_rb:
                    if (mHomeFragment == null) {
                        mHomeFragment = HomeFragment.newInstance(getString(R.string.item_home));
                    }
                    transaction.replace(R.id.sub_content, mHomeFragment);
                    break;
                case R.id.permit_rb:
                    if (mLocationFragment == null) {
                        mLocationFragment = LocationFragment.newInstance(getString(R.string.item_location));
                    }
                    transaction.replace(R.id.sub_content, mLocationFragment);
                    break;
                case R.id.reportback_rb:
                    if (mLikeFragment == null) {
                        mLikeFragment = LikeFragment.newInstance(getString(R.string.item_like));
                    }
                    transaction.replace(R.id.sub_content, mLikeFragment);
                    break;
                case R.id.mine_rb:
                    if (mPersonFragment == null) {
                        mPersonFragment = PersonFragment.newInstance(getString(R.string.item_person));
                    }
                    transaction.replace(R.id.sub_content, mPersonFragment);
                    break;
            }
            setTabState();//设置状态
            transaction.commit();
        }


    }
}

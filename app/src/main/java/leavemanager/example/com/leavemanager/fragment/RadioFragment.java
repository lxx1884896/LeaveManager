package leavemanager.example.com.leavemanager.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.R;

public class RadioFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private TextView mTextView;
    private RadioButton mRadioLeave;
    private RadioButton mRadioPermit;
    private RadioButton mRadioReportback;
    private RadioButton mRadioMine;
    private RadioGroup mRadioGroup;
    private LeaveFragment mLeaveFragment;
    private PermitFragment mPermitFragment;
    private ReportbackFragment mReportbackFragment;
    private RadioFragment mRadioFragment;

    public static RadioFragment newInstance(String s) {
        RadioFragment radioFragment = new RadioFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        radioFragment.setArguments(bundle);
        return radioFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio_group, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        mRadioLeave = (RadioButton) view.findViewById(R.id.leave_rb);
        mRadioPermit = (RadioButton) view.findViewById(R.id.permit_rb);
        mRadioReportback = (RadioButton) view.findViewById(R.id.reportback_rb);
        mRadioMine = (RadioButton) view.findViewById(R.id.mine_rb);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString(Constants.ARGS);
            if (!TextUtils.isEmpty(s)) {
                mTextView.setText(s);
            }
        }
        mRadioGroup.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onStart() {
        setDefaultFragment();//写在onCreateView里面，当页面跑到其他Fragment再回来就不会生效
        super.onStart();
    }

    private void setDefaultFragment() {
        mRadioLeave.setChecked(true);
        mRadioPermit.setChecked(false);
        mRadioReportback.setChecked(false);
        mRadioMine.setChecked(false);
        if (mRadioLeave.isChecked()) {
            setTabState();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            mLeaveFragment = mLeaveFragment.newInstance(getString(R.string.leave));
            transaction.replace(R.id.sub_content, mHomeFragment).commit();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (checkId) {
            case R.id.leave_rb:
                if (mLeaveFragment == null) {
                    mLeaveFragment = LeaveFragment.newInstance(getString(R.string.leave));
                }
                transaction.replace(R.id.sub_content, mLeaveFragment);
                break;
            case R.id.permit_rb:
                if (mPermitFragment == null) {
                    mPermitFragment = PermitFragment.newInstance(getString(R.string.permit));
                }
                transaction.replace(R.id.sub_content, mPermitFragment);
                break;
            case R.id.reportback_rb:
                if (mReportbackFragment == null) {
                    mReportbackFragment = ReportbackFragment.newInstance(getString(R.string.reportback));
                }
                transaction.replace(R.id.sub_content, mReportbackFragment);
                break;
            case R.id.mine_rb:
                if (mRadioFragment == null) {
                    mRadioFragment = RadioFragment.newInstance(getString(R.string.mine));
                }
                transaction.replace(R.id.sub_content, mRadioFragment);
                break;
        }
        setTabState();
        transaction.commit();
    }

    private void setTabState() {
        setLeaveState();
        setPermitState();
        setReportbackState();
        setMineState();
    }

    /**
     * set tab home state
     */
    private void setLeaveState() {
        if (mRadioLeave.isChecked()) {
            mRadioLeave.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioLeave.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setPermitState() {
        if (mRadioPermit.isChecked()) {
            mRadioPermit.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioPermit.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setReportbackState() {
        if (mRadioReportback.isChecked()) {
            mRadioReportback.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioReportback.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setMineState() {
        if (mRadioMine.isChecked()) {
            mRadioMine.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioMine.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

}

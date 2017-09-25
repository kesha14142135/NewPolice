package com.klg.newpolice.ui.authorisation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.klg.newpolice.R;
import com.klg.newpolice.ui.missingchildren.MissingChildrenActivity;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class AuthorisationFragment extends Fragment implements AuthorisationContract.View, View.OnClickListener, Validator.ValidationListener {

    //control variables
    private View mView;
    private AuthorisationContract.Presenter mPresenter;
    private Validator mValidator;
    //ui elements
    private ProgressBar mProgressBar;
    @NotEmpty(messageResId = R.string.error_login_empty)
    private EditText mEditTextLogin;
    @NotEmpty(messageResId = R.string.error_password_empty)
    private EditText mEditTextPassword;

    public static AuthorisationFragment newInstance() {
        return new AuthorisationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_authorisation, container, false);
        updateViewDependencies(mView);
        mPresenter = new AuthorisationPresenter(this,getContext());
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(AuthorisationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void authorisationComplite() {
        Intent intent = new Intent(getActivity(), MissingChildrenActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void authorisationFailure() {
        showMessage(getString(R.string.error_authorisation));
    }

    @Override
    public void visibleProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void invisibleProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void showMessage(String text) {
        Snackbar.make(mView.findViewById(R.id.relative_layout_authorization), text, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_authorization: {
                mEditTextLogin.setBackgroundResource(R.drawable.edit_text_border_neutrally);
                mEditTextPassword.setBackgroundResource(R.drawable.edit_text_border_neutrally);
                mValidator.validate();
                break;
            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        mPresenter.authorisationUser(
                mEditTextLogin.getText().toString(),
                mEditTextPassword.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        String textError = "";
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(view.getContext());
            if (view instanceof EditText) {
                view.setBackgroundResource(R.drawable.edit_text_border);
                textError += message + "\n";
            } else {
                showMessage(message);
            }
        }
        showMessage(textError);
    }

    private void updateViewDependencies(View view) {
        mProgressBar = view.findViewById(R.id.progress_bar_authorisation);
        mEditTextPassword = view.findViewById(R.id.edit_text_password);
        mEditTextLogin = view.findViewById(R.id.edit_text_login);

        view.findViewById(R.id.button_authorization).setOnClickListener(this);
    }


}

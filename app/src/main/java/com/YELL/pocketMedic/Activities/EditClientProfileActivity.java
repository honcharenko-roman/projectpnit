package com.YELL.pocketMedic.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.YELL.pocketMedic.Entities.Account;
import com.YELL.pocketMedic.LocalDb.LoggedUser;
import com.YELL.pocketMedic.R;
import com.YELL.pocketMedic.Retrofit.NetworkService;
import com.YELL.pocketMedic.RxHelper;
import com.YELL.pocketMedic.ValidationResult;
import com.YELL.pocketMedic.ValidationUtils;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func4;


public class EditClientProfileActivity extends AppCompatActivity {


    EditText nameField;
    EditText surnameField;
    EditText telephoneField;
    EditText dateField;

    EditText addressField;
    EditText infoField;

    EditText middleNameField;
    private Subscription _subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_client);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nameField = findViewById(R.id.editClientProfileNameField);
        surnameField = findViewById(R.id.editClientProfileSurnameField);
        middleNameField = findViewById(R.id.editProfileMiddlenameField);
        addressField = findViewById(R.id.editClientProfileAddressField);
        infoField = findViewById(R.id.editClientProfileInformationField);
        telephoneField = findViewById(R.id.editClientProfilePhoneNumberField);
        dateField = findViewById(R.id.editClientProfileDateOfBirthField);

        findViewById(R.id.sendButton).setOnClickListener(v -> {

            Toast.makeText(getApplicationContext(),"You should fill-in all fields with *",Toast.LENGTH_LONG).show();

        });
        setupObservables();
    }

    private void setupObservables() {

        Observable<Boolean> usernameObservable = RxHelper.getTextWatcherObservable(nameField)
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        ValidationResult result = validateUsername(s);
                        nameField.setError(result.getReason());
                        return result.isValid();
                    }
                });

        Observable<Boolean> surnameObservable = RxHelper.getTextWatcherObservable(surnameField)
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        ValidationResult result = validateUsername(s);
                        surnameField.setError(result.getReason());
                        return result.isValid();
                    }
                });

        Observable<Boolean> phoneObservable = RxHelper.getTextWatcherObservable(telephoneField)
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        ValidationResult result = validatePhone(s);
                        telephoneField.setError(result.getReason());
                        return result.isValid();
                    }
                });

        Observable<Boolean> dateObservable = RxHelper.getTextWatcherObservable(dateField)
                .debounce(1600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        ValidationResult result = validateDate(s);
                        dateField.setError(result.getReason());
                        return result.isValid();
                    }
                });


        _subscription = Observable.combineLatest(usernameObservable, surnameObservable, phoneObservable, dateObservable, new Func4<Boolean, Boolean, Boolean, Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean validUsername, Boolean validSurname, Boolean validPhone, Boolean validDate) {
                return validUsername && validSurname && validPhone && validDate;
            }
            }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                findViewById(R.id.sendButton).setOnClickListener(v -> {

                    Account account = new Account(
                            nameField.getText().toString(),
                            surnameField.getText().toString(),
                            telephoneField.getText().toString(),
                            null,
                            null,
                            infoField.getText().toString(),
                            addressField.getText().toString()
                    );

                    LoggedUser.getInstance().setName(account.getName());
                    LoggedUser.getInstance().setEmail(account.getName().toLowerCase().replace(" ", "") + account.getSurname() + "@gmail.com");
                    LoggedUser.getInstance().setLoggedIn(true);


                    Call<Account> call = NetworkService
                            .getInstance().getJSONApi()
                            .postAccount(account);
                    call.enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {

                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {

                        }
                    });
                    startActivity(new Intent(EditClientProfileActivity.this, ListActivity.class));
                });
            }
        });
    }

    private ValidationResult<String> validateDate(@NonNull String date) {
        if (date.isEmpty()) {
            return ValidationResult.failure(null, date);
        }

        boolean isValid = ValidationUtils.isValidDate(date);
        if (isValid) {
            return ValidationResult.success(date);
        }

        return ValidationResult.failure("Date must have dd.mm.year format and be valid", date);
    }

    private ValidationResult<String> validateUsername(@NonNull String username) {
        return ValidationUtils.isValidUsername(username);
    }

    private ValidationResult validatePhone(@NonNull String phone) {
        if (phone.isEmpty()) {
            return ValidationResult.failure(null, phone);
        }

        boolean isValid = ValidationUtils.isValidMobileNumber(phone);
        if (isValid) {
            return ValidationResult.success(phone);
        }

        return ValidationResult.failure("Phone can contain only digits and '+' sign", phone);
    }

}
package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yun.myapplication.Entities.Account;
import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.LocalDb.LoggedUser;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;
import com.example.yun.myapplication.RxHelper;
import com.example.yun.myapplication.ValidationResult;
import com.example.yun.myapplication.ValidationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;

public class EditProfileActivity extends AppCompatActivity {

    private Medic medic;

    EditText nameField;
    EditText surnameField;
    EditText middleNameField;
    EditText dateField;

    EditText addressField;
    EditText infoField;

    EditText experienceField;
    EditText telephoneField;

    Spinner mySpinner;
    private Subscription _subscription;

    //!*($E^*)$
    private List categoryValues = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        categoryValues.add("Выберите категорию");
        categoryValues.addAll(Arrays.asList(YELL.main.domain.Categories.values()));

        nameField  = findViewById(R.id.editProfileNameField);
        surnameField  = findViewById(R.id.editProfileSurnameField);
        addressField  = findViewById(R.id.editProfileAddressField);
        infoField  = findViewById(R.id.editProfileInformationField);
        experienceField  = findViewById(R.id.editProfileExperienceField);
        telephoneField  = findViewById(R.id.editProfilePhoneNumberField);
        middleNameField = findViewById(R.id.editProfileMiddlenameField);
        dateField = findViewById(R.id.editProfileDateOfBirthField);

        mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item, categoryValues) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        });
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

        Observable<Boolean> experienceObservable = RxHelper.getTextWatcherObservable(experienceField)
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        ValidationResult result = validateExp(s);
                        experienceField.setError(result.getReason());
                        return result.isValid();
                    }
                });

//        Observable<Boolean> categoryObservable = RxHelper.getTextWatcherObservable((EditText) mySpinner.getSelectedItem())
//                .debounce(1600, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        ValidationResult result = validateExp(s);
//                        experienceField.setError(result.getReason());
//                        return result.isValid();
//                    }
//                });

        _subscription = Observable.combineLatest(usernameObservable, surnameObservable, phoneObservable, dateObservable, experienceObservable,
                new Func5<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean validUsername, Boolean validSurname, Boolean validPhone, Boolean validDate, Boolean validExp) {
                return validUsername && validSurname && validPhone && validDate && validExp;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                findViewById(R.id.sendButton).setOnClickListener(v->{

                    medic = new Medic(
                            nameField.getText().toString(),
                            surnameField.getText().toString(),
                            telephoneField.getText().toString(),
                            null,
                            Integer.parseInt(experienceField.getText().toString()),
                            null,
                            addressField.getText().toString(),
                            infoField.getText().toString(),
                            true,
                            mySpinner.getSelectedItem().toString(),
                            false
                    );

                    //logging in
                    LoggedUser.getInstance().setName(medic.getName());
                    LoggedUser.getInstance().setEmail(medic.getName().toLowerCase().replace(" ", "") + medic.getSurname() + "@gmail.com");
                    LoggedUser.getInstance().setLoggedIn(true);

                    Call<Medic> call = NetworkService.getInstance().getJSONApi().postMedic(medic);
                    call.enqueue(new Callback<Medic>() {
                        @Override
                        public void onResponse(Call<Medic> call, Response<Medic> response) {

                        }

                        @Override
                        public void onFailure(Call<Medic> call, Throwable t) {

                        }
                    });

                    startActivity(new Intent(EditProfileActivity.this, ListActivity.class));
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

    private ValidationResult validateCategory(@NonNull String category) {
        if (category.isEmpty()) {
            return ValidationResult.failure(null, category);
        } else {
            return ValidationResult.success(category);
        }
    }

    private ValidationResult validateExp(@NonNull String exp) {
        if (exp.isEmpty()) {
            return ValidationResult.failure(null, exp);
        }

        return ValidationUtils.isExpValid(exp);

    }

    private ValidationResult validatePhone(@NonNull String phone) {
        if (phone.isEmpty()) {
            return ValidationResult.failure(null, phone);
        }

        boolean isValid = ValidationUtils.isValidMobileNumber(phone);
        if (isValid) {
            return ValidationResult.success(phone);
        }

        return ValidationResult.failure("Phone must contain more then 6 digits and can starts with +", phone);
    }

}

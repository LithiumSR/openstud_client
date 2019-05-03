package com.lithium.leona.openstud.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.lithium.leona.openstud.R;
import com.lithium.leona.openstud.data.InfoManager;
import com.lithium.leona.openstud.helpers.ClientHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import lithium.openstud.driver.core.Openstud;
import lithium.openstud.driver.core.models.Career;
import lithium.openstud.driver.core.models.CertificateType;
import lithium.openstud.driver.core.models.Student;
import lithium.openstud.driver.exceptions.OpenstudConnectionException;
import lithium.openstud.driver.exceptions.OpenstudInvalidCredentialsException;
import lithium.openstud.driver.exceptions.OpenstudInvalidResponseException;

public class BottomSheetCertificateFragment extends BottomSheetDialogFragment {

    @BindView(R.id.registration)
    LinearLayout registration;
    @BindView(R.id.exams_taken)
    LinearLayout examsTaken;
    @BindView(R.id.degree_with_evaluation)
    LinearLayout degreeEvaluation;
    @BindView(R.id.degree_with_evaluation_eng)
    LinearLayout degreeEvaluationEng;
    @BindView(R.id.degree_with_exams)
    LinearLayout degreeExams;
    @BindView(R.id.degree_with_exams_eng)
    LinearLayout degreeExamsEng;
    @BindView(R.id.degree_ransom)
    LinearLayout degreeRansom;
    @BindView(R.id.degree_with_thesis)
    LinearLayout degreeThesis;
    @BindView(R.id.degree_with_thesis_eng)
    LinearLayout degreeThesisEng;
    @BindView(R.id.main_layout)
    LinearLayout mainLayout;
    private Openstud os;
    private Student student;
    public BottomSheetCertificateFragment() {
        // Required empty public constructor
    }

    public static BottomSheetCertificateFragment newInstance() {
        return new BottomSheetCertificateFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.certificates_sheet, container, false);
        ButterKnife.bind(this, v);
        ClientHelper.setDialogView(v, getDialog(), BottomSheetBehavior.STATE_EXPANDED);
        Context context = getContext();
        os = InfoManager.getOpenStud(context);
        student = InfoManager.getInfoStudentCached(context, os);
        if (context == null || student == null || os == null) {
            dismiss();
            return null;
        }
        registration.setOnClickListener(v1 -> getCertificate(CertificateType.REGISTRATION));
        examsTaken.setOnClickListener(v1 -> getCertificate(CertificateType.EXAMS_COMPLETED));
        degreeEvaluation.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_WITH_EVALUATION));
        degreeEvaluationEng.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_WITH_EVALUATION_ENG));
        degreeThesis.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_WITH_THESIS));
        degreeThesisEng.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_WITH_THESIS_ENG));
        degreeExams.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_WITH_EXAMS));
        degreeExamsEng.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_WITH_EXAMS_ENG));
        degreeRansom.setOnClickListener(v1 -> getCertificate(CertificateType.DEGREE_FOR_RANSOM));
        return null;
    }


    private void setButtonsState(boolean enabled) {
        Activity activity = getActivity();
        if (activity ==null) return;
        activity.runOnUiThread(() -> {
            registration.setEnabled(enabled);
            examsTaken.setEnabled(enabled);
            degreeEvaluation.setEnabled(enabled);
            degreeEvaluationEng.setEnabled(enabled);
            degreeThesis.setEnabled(enabled);
            degreeThesisEng.setEnabled(enabled);
            degreeExams.setEnabled(enabled);
            degreeExamsEng.setEnabled(enabled);
            degreeRansom.setEnabled(enabled);
        });
    }
    private void getCertificate(CertificateType certificate) {
        Activity activity = getActivity();
        if (activity == null) return;
        ClientHelper.requestReadWritePermissions(activity, new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                new Thread(() -> {
                    try {
                        setButtonsState(false);
                        selectCareer(activity, certificate);
                    } catch (OpenstudConnectionException | OpenstudInvalidResponseException e) {
                        activity.runOnUiThread(() -> Toasty.error(activity, R.string.failed_get_network).show());
                        e.printStackTrace();
                    } catch (OpenstudInvalidCredentialsException e) {
                        InfoManager.clearSharedPreferences(activity);
                        ClientHelper.rebirthApp(activity, ClientHelper.Status.INVALID_CREDENTIALS.getValue());
                        e.printStackTrace();
                    } finally {
                        setButtonsState(true);
                    }
                }).start();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                activity.runOnUiThread(() -> Toasty.error(activity, R.string.no_write_permission_pdf).show());
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        });
    }



    private void selectCareer(Activity activity, CertificateType cert) throws OpenstudInvalidCredentialsException, OpenstudConnectionException, OpenstudInvalidResponseException {
        List<Career> careers = os.getCareersChoichesForCertificate(student,cert);
        if (careers.isEmpty()) {
            activity.runOnUiThread(() -> Toasty.error(activity, R.string.connection_error).show());
        }
        else {
            getFile(activity,cert, careers.get(0));
        }
    }

    private void getFile(Activity activity, CertificateType cert, Career career) throws OpenstudInvalidCredentialsException, OpenstudConnectionException, OpenstudInvalidResponseException {
        boolean check = false;
        String directory = Environment.getExternalStorageDirectory() + "/OpenStud/pdf/certs/";
        File dirs = new File(directory);
        dirs.mkdirs();
        File pdfFile = new File(directory + cert.toString() + ".pdf");
        try {
            if (pdfFile.exists()) pdfFile.delete();
            pdfFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(pdfFile);
            byte[] content = os.getCertificatePDF(student, career, cert);
            fos.write(content);
            fos.close();
            check = true;
        } catch (IOException e) {
            activity.runOnUiThread(() -> Toasty.error(activity, R.string.connection_error).show());
            e.printStackTrace();
        }
        if (!check) {
            pdfFile.delete();
            return;
        }
        ClientHelper.openActionViewPDF(activity, pdfFile);
    }

}
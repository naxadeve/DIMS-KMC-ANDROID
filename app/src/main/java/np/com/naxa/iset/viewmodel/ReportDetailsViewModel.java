package np.com.naxa.iset.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.Flowable;
import np.com.naxa.iset.database.databaserepository.ReportDetailsRepository;
import np.com.naxa.iset.database.entity.ReportDetailsEntity;
import np.com.naxa.iset.mycircle.ContactModel;

public class ReportDetailsViewModel extends AndroidViewModel {

    private ReportDetailsRepository reportDetailsRepository;
    private Flowable<List<ReportDetailsEntity>> mAllReportDetailsList;
    private Flowable<List<ReportDetailsEntity>> mAllUnVerifiedReportDetailsList;

    public ReportDetailsViewModel(@NonNull Application application) {
        super(application);

        reportDetailsRepository = new ReportDetailsRepository(application);

        mAllReportDetailsList = reportDetailsRepository.getAllReportDetailsList();
        mAllUnVerifiedReportDetailsList = reportDetailsRepository.getAllUnVerifiedReportDetailsList();
    }

    public Flowable<List<ReportDetailsEntity>> getAllReportDetailsList() { return mAllReportDetailsList; }
    public Flowable<List<ReportDetailsEntity>> getAllUnVerifiedReportDetailsList() { return mAllUnVerifiedReportDetailsList; }


    public long insert(ReportDetailsEntity reportDetailsEntity) {
        Log.d("VIewholder", "insert: "+reportDetailsEntity.getIncident_type());
        return reportDetailsRepository.insert(reportDetailsEntity); }
}
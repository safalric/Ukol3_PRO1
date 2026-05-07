package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

public class DepartmentStatsReporting {
    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        return new DepartmentStats(
                maxActionStudentsCount(),
                emptyActionsCount(),
                maxTeacherScore()
        );
    }

    private static long maxActionStudentsCount() {
        return 50;
    }

    private static long emptyActionsCount() {
        return 60;
    }


    private static long maxTeacherScore() {
        return 70;
    }
}

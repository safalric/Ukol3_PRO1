package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

public class DepartmentStatsReporting {
    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);
        return new DepartmentStats(
                maxActionStudentsCount(actionsList),
                emptyActionsCount(actionsList),
                maxTeacherScore(actionsList)
        );
    }

    private static long maxActionStudentsCount(ActionsList actionsList) {
        if (actionsList == null || actionsList.items == null) {
            return 0;
        }

        long max = 0;
        for (var action : actionsList.items) {
            if (action != null && action.studentsCount != null && action.studentsCount > max) {
                max = action.studentsCount;
            }
        }
        return max;
    }

    private static long emptyActionsCount(ActionsList actionsList) {
        if (actionsList == null || actionsList.items == null) {
            return 0;
        }

        long emptyCount = 0;
        for (var action : actionsList.items) {
            if (action != null && action.studentsCount != null && action.studentsCount == 0) {
                emptyCount++;
            }
        }
        return emptyCount;
    }


    private static long maxTeacherScore(ActionsList actionsList) {
        if (actionsList == null || actionsList.items == null) {
            return 0;
        }

        long max = 0;
        for (var action : actionsList.items) {
            if (action == null || action.teacherId == null) {
                continue;
            }
            var score = teacherScore(action.teacherId, actionsList);
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    private static long teacherScore(long teacherId, ActionsList actionsList) {
        if (actionsList == null || actionsList.items == null) {
            return 0;
        }

        long total = 0;
        for (var action : actionsList.items) {
            if (action == null || action.teacherId == null || action.studentsCount == null) {
                continue;
            }
            if (action.teacherId == teacherId) {
                total += action.studentsCount;
            }
        }
        return total;
    }
}

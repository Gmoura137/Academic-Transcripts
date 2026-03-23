import java.util.*;

public class AcademicTranscripts {

    static class Transcript {
        String major;
        int totalCredits = 0;
        int semesterCredits = 0;
        int majorCredits = 0;

        double totalQualityPoints = 0.0;
        double semesterQualityPoints = 0.0;
        double majorQualityPoints = 0.0;

        void declareMajor(String maj) {
            if (!isValidDepartment(maj)) {
                System.out.println("Invalid major: " + maj);
                System.exit(0);
            }
            this.major = maj;
        }

        void addGrade(String dept, String grade, String creditsStr) {
            if (major == null) {
                System.out.println("Cannot add grade. Major is not declared.");
                return;
            }

            if (!isAddGradeValid(dept, grade, creditsStr)) return;

            int credits = Integer.parseInt(creditsStr);

            if (semesterCredits + credits > 18) {
                System.out.println("Cannot add grade. Maximum credits allowed per semester is 18.");
                return;
            }

            double points = getGradePoints(grade);

            totalCredits += credits;
            semesterCredits += credits;

            totalQualityPoints += points * credits;
            semesterQualityPoints += points * credits;

            if (dept.equals(major)) {
                majorCredits += credits;
                majorQualityPoints += points * credits;
            }

            System.out.println("Grade added: " + dept + " " + grade + " " + credits);
            printTranscript();
        }

        void startNewSemester() {
            semesterCredits = 0;
            semesterQualityPoints = 0.0;
            printTranscript();
        }

        double getOverallGPA() {
            return totalCredits == 0 ? 0.0 : totalQualityPoints / totalCredits;
        }

        double getSemesterGPA() {
            return semesterCredits == 0 ? 0.0 : semesterQualityPoints / semesterCredits;
        }

        double getMajorGPA() {
            return majorCredits == 0 ? 0.0 : majorQualityPoints / majorCredits;
        }

        void printTranscript() {
            System.out.println("==========");
            System.out.println("Total Credits: " + totalCredits);
            System.out.printf("Overall GPA: %.2f\n", getOverallGPA());
            System.out.println("Current Semester Credits: " + semesterCredits);
            System.out.printf("Current Semester GPA: %.2f\n", getSemesterGPA());
            System.out.println("Major: " + major);
            System.out.println("Major Credits: " + majorCredits);
            System.out.printf("Major GPA: %.2f\n", getMajorGPA());
            System.out.println("==========");
        }
    }

    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("declareMajor")) {
            System.out.println("Invalid declareMajor command. Major not provided.");
            return;
        }

        Transcript t = new Transcript();
        t.declareMajor(args[1]);

        for (int i = 2; i < args.length; ) {
            String cmd = args[i];

            if (cmd.equals("addGrade")) {
                if (i + 3 >= args.length) {
                    System.out.println("Invalid addGrade command. Department, grade, and/or credits not provided.");
                    return;
                }

                t.addGrade(args[i + 1], args[i + 2], args[i + 3]);
                i += 4;
            } else if (cmd.equals("startNewSemester")) {
                t.startNewSemester();
                i += 1;
            } else {
                System.out.println("Unknown command: " + cmd);
                return;
            }
        }
    }

    public static boolean isValidDepartment(String department) {
        return department != null && department.length() == 3;
    }

    public static boolean isAddGradeValid(String department, String grade, String credits) {
        if (!isValidDepartment(department)) {
            System.out.println("Invalid department: " + department);
            return false;
        }

        if (!isValidGrade(grade)) {
            System.out.println("Invalid grade: " + grade);
            return false;
        }

        if (!credits.matches("[1-4]")) {
            System.out.println("Invalid credits: " + credits);
            return false;
        }

        return true;
    }

    private static boolean isValidGrade(String grade) {
        return switch (grade) {
            case "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F" -> true;
            default -> false;
        };
    }

    private static double getGradePoints(String grade) {
        return switch (grade) {
            case "A" -> 4.0;
            case "A-" -> 3.66;
            case "B+" -> 3.33;
            case "B" -> 3.0;
            case "B-" -> 2.66;
            case "C+" -> 2.33;
            case "C" -> 2.0;
            case "C-" -> 1.66;
            case "D+" -> 1.33;
            case "D" -> 1.0;
            case "D-" -> 0.66;
            case "F" -> 0.0;
            default -> -1.0;
        };
    }
}






		




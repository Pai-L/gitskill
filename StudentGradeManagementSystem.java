import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class StudentGrade {
    String name;
    String id;
    String course;
    double grade;

    public StudentGrade(String name, String id, String course, double grade) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.grade = grade;
    }
}

public class StudentGradeManagementSystem {
    static List<StudentGrade> grades = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    recordGrade();
                    break;
                case 2:
                    queryGrade();
                    break;
                case 3:
                    statisticsGrade();
                    break;
                case 4:
                    System.out.println("感谢使用学生成绩管理系统，再见！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入。");
            }
        }
    }

    static void displayMenu() {
        System.out.println("=================================");
        System.out.println("欢迎使用学生成绩管理系统");
        System.out.println("=================================");
        System.out.println("请选择操作：");
        System.out.println("1. 记录学生成绩");
        System.out.println("2. 查询学生成绩");
        System.out.println("3. 统计课程成绩");
        System.out.println("4. 退出系统");
        System.out.print("请输入选项序号：");
    }

    static void recordGrade() {
        System.out.println("===== 记录学生成绩 =====");
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入学生学号：");
        String id = scanner.nextLine();
        System.out.print("请输入课程名称：");
        String course = scanner.nextLine();
        System.out.print("请输入成绩（0-100）：");
        double grade = scanner.nextDouble();
        scanner.nextLine();
        grades.add(new StudentGrade(name, id, course, grade));
        System.out.println("成绩已成功记录！");
    }

    static void queryGrade() {
        System.out.println("===== 查询学生成绩 =====");
        System.out.println("请选择查询方式：");
        System.out.println("1. 按学生姓名查询");
        System.out.println("2. 按学生学号查询");
        System.out.println("3. 按课程名称查询");
        System.out.print("请输入选项序号：");
        int queryChoice = scanner.nextInt();
        scanner.nextLine();
        switch (queryChoice) {
            case 1:
                System.out.print("请输入学生姓名：");
                String name = scanner.nextLine();
                for (StudentGrade grade : grades) {
                    if (grade.name.equals(name)) {
                        System.out.printf("姓名：%s, 学号：%s, 课程：%s, 成绩：%.1f\n", grade.name, grade.id, grade.course, grade.grade);
                    }
                }
                break;
            case 2:
                System.out.print("请输入学生学号：");
                String id = scanner.nextLine();
                for (StudentGrade grade : grades) {
                    if (grade.id.equals(id)) {
                        System.out.printf("姓名：%s, 学号：%s, 课程：%s, 成绩：%.1f\n", grade.name, grade.id, grade.course, grade.grade);
                    }
                }
                break;
            case 3:
                System.out.print("请输入课程名称：");
                String course = scanner.nextLine();
                for (StudentGrade grade : grades) {
                    if (grade.course.equals(course)) {
                        System.out.printf("姓名：%s, 学号：%s, 课程：%s, 成绩：%.1f\n", grade.name, grade.id, grade.course, grade.grade);
                    }
                }
                break;
            default:
                System.out.println("无效的查询选项，请重新选择。");
        }
    }

    static void statisticsGrade() {
        System.out.println("===== 统计课程成绩 =====");
        System.out.print("请输入课程名称：");
        String course = scanner.nextLine();
        double total = 0;
        int count = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (StudentGrade grade : grades) {
            if (grade.course.equals(course)) {
                total += grade.grade;
                count++;
                if (grade.grade > max) {
                    max = grade.grade;
                }
                if (grade.grade < min) {
                    min = grade.grade;
                }
            }
        }
        if (count > 0) {
            double average = total / count;
            System.out.println("课程：" + course);
            System.out.printf("平均分：%.2f\n", average);
            System.out.printf("最高分：%.1f\n", max);
            System.out.printf("最低分：%.1f\n", min);
        } else {
            System.out.println("未找到该课程的成绩记录。");
        }
    }
}
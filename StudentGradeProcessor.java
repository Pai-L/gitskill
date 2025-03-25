import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 学生成绩类
class StudentGrade {
    private String name;
    private String studentId;
    private String courseName;
    private double grade;

    public StudentGrade(String name, String studentId, String courseName, double grade) {
        this.name = name;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }
}

// 学生成绩管理系统类
class StudentGradeManagementSystem {
    private List<StudentGrade> grades;

    public StudentGradeManagementSystem() {
        this.grades = new ArrayList<>();
    }

    // 记录学生成绩
    public void recordGrade(String name, String studentId, String courseName, double grade) {
        for (StudentGrade g : grades) {
            if (g.getStudentId().equals(studentId) && g.getCourseName().equals(courseName)) {
                throw new IllegalArgumentException("该学号的该课程成绩已存在！");
            }
        }
        grades.add(new StudentGrade(name, studentId, courseName, grade));
        System.out.println("成绩已成功记录！");
    }

    // 按学生姓名查询成绩
    public void queryGradeByName(String name) {
        boolean found = false;
        for (StudentGrade g : grades) {
            if (g.getName().equals(name)) {
                System.out.printf("姓名：%s, 学号：%s, 课程：%s, 成绩：%.1f\n", g.getName(), g.getStudentId(), g.getCourseName(), g.getGrade());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该学生的成绩记录。");
        }
    }

    // 按学生学号查询成绩
    public void queryGradeById(String studentId) {
        boolean found = false;
        for (StudentGrade g : grades) {
            if (g.getStudentId().equals(studentId)) {
                System.out.printf("姓名：%s, 学号：%s, 课程：%s, 成绩：%.1f\n", g.getName(), g.getStudentId(), g.getCourseName(), g.getGrade());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该学号的成绩记录。");
        }
    }

    // 按课程名称查询成绩
    public void queryGradeByCourse(String courseName) {
        boolean found = false;
        for (StudentGrade g : grades) {
            if (g.getCourseName().equals(courseName)) {
                System.out.printf("姓名：%s, 学号：%s, 课程：%s, 成绩：%.1f\n", g.getName(), g.getStudentId(), g.getCourseName(), g.getGrade());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该课程的成绩记录。");
        }
    }

    // 统计课程成绩
    public void statisticsGrade(String courseName) {
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        int count = 0;
        for (StudentGrade g : grades) {
            if (g.getCourseName().equals(courseName)) {
                sum += g.getGrade();
                max = Math.max(max, g.getGrade());
                min = Math.min(min, g.getGrade());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("未找到该课程的成绩记录。");
        } else {
            double average = sum / count;
            System.out.printf("课程：%s\n", courseName);
            System.out.printf("平均分：%.2f\n", average);
            System.out.printf("最高分：%.1f\n", max);
            System.out.printf("最低分：%.1f\n", min);
        }
    }
}

// 主类，包含菜单界面
public class Main {
    public static void main(String[] args) {
        StudentGradeManagementSystem system = new StudentGradeManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=================================");
            System.out.println("欢迎使用学生成绩管理系统");
            System.out.println("=================================");
            System.out.println("请选择操作：");
            System.out.println("1. 记录学生成绩");
            System.out.println("2. 查询学生成绩");
            System.out.println("3. 统计课程成绩");
            System.out.println("4. 退出系统");
            System.out.print("请输入选项序号：");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    System.out.println("===== 记录学生成绩 =====");
                    System.out.print("请输入学生姓名：");
                    String name = scanner.nextLine();
                    System.out.print("请输入学生学号：");
                    String studentId = scanner.nextLine();
                    System.out.print("请输入课程名称：");
                    String courseName = scanner.nextLine();
                    System.out.print("请输入成绩（0-100）：");
                    try {
                        double grade = scanner.nextDouble();
                        scanner.nextLine(); // 消耗换行符
                        if (grade < 0 || grade > 100) {
                            System.out.println("成绩必须在 0-100 分之间！");
                        } else {
                            system.recordGrade(name, studentId, courseName, grade);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
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
                            String queryName = scanner.nextLine();
                            system.queryGradeByName(queryName);
                            break;
                        case 2:
                            System.out.print("请输入学生学号：");
                            String queryId = scanner.nextLine();
                            system.queryGradeById(queryId);
                            break;
                        case 3:
                            System.out.print("请输入课程名称：");
                            String queryCourse = scanner.nextLine();
                            system.queryGradeByCourse(queryCourse);
                            break;
                        default:
                            System.out.println("无效的查询选项！");
                    }
                    break;
                case 3:
                    System.out.println("===== 统计课程成绩 =====");
                    System.out.print("请输入课程名称：");
                    String statCourse = scanner.nextLine();
                    system.statisticsGrade(statCourse);
                    break;
                case 4:
                    System.out.println("感谢使用学生成绩管理系统，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选项，请重新输入！");
            }
        }
    }
}
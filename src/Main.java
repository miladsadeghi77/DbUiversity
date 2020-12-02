import entities.StuEntity;
import entities.Stu_TeachEntity;
import entities.TeachEntity;
import service.StuService;
import service.Stu_TeachService;
import service.TeachService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Program University");
        Scanner scanner = new Scanner(System.in);

        TeachService teachService = new TeachService();
        TeachEntity teachEntity = new TeachEntity();

        StuEntity stuEntity = new StuEntity();
        StuService stuService=new StuService();

        Stu_TeachEntity stu_teachEntity=new Stu_TeachEntity();
        Stu_TeachService stu_teachService=new Stu_TeachService();

        String resume;
        do {
            System.out.print("***** Enter the relevant number to select your position *****" +
                    "\n" + "(1.Teacher 2.Student ): ");
            int status = scanner.nextInt();

            if (status == 1) {
                teachService.printTeach();
                System.out.print("***** What operation do you want to do? Enter the relevant number *****" +
                        "\n" + "(1.update 2.add 3.remove 4.showStudent): ");
                status = scanner.nextInt();
                if (status == 1) {
                    System.out.print("Teacher Id: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter the name of Teacher: ");
                    String fname = scanner.next();
                    System.out.print("And Enter the Last Name of Teacher: ");
                    String lname = scanner.next();
                    teachEntity.setSsn(id);
                    teachEntity.setfName(fname);
                    teachEntity.setlName(lname);
                    teachService.updateTeach(teachEntity);
                    teachService.printTeach();

                } else if (status == 2) {
                    System.out.print("Enter the name of Teacher: ");
                    String fname = scanner.next();
                    System.out.print("And Enter the Last Name of Teacher: ");
                    String lname = scanner.next();
                    teachEntity.setfName(fname);
                    teachEntity.setlName(lname);
                    teachService.addTeach(teachEntity);
                    teachService.printTeach();
                } else if (status == 3) {
                    System.out.print("Id Teacher: ");
                    int id = scanner.nextInt();
                    teachEntity.setSsn(id);
                    teachService.removeTeach(teachEntity);
                    teachService.printTeach();

                } else if (status == 4) {
                    System.out.print("TeacherId: ");
                    int id = scanner.nextInt();
                    stuEntity.setSsn(id);
                    List<StuEntity> Students = teachService.findStudent(stuEntity);
                    for (StuEntity model : Students) {
                        System.out.println(model.toString());
                    }

                }

            } else if (status == 2) {
                stuService.printStu();
                System.out.print("***** What operation do you want to do? Enter the relevant number *****" +
                        "\n" + "(1.update 2.add 3.remove 4.showTeachers): ");
                status = scanner.nextInt();
                if (status == 1) {
                    System.out.print("Student Id: ");
                    int id = scanner.nextInt();
                    System.out.print("Ok! Enter the name of Student: ");
                    String fname = scanner.next();
                    System.out.print("And Enter the Last Name of Student: ");
                    String lname = scanner.next();
                    stuEntity.setSsn(id);
                    stuEntity.setfName(fname);
                    stuEntity.setlName(lname);
                    stuService.updateStu(stuEntity);
                    stuService.printStu();

                } else if (status == 2) {
                    System.out.print("Enter the Student Id: ");
                    int stuId= scanner.nextInt();
                    stuEntity.setSsn(stuId);
                    stu_teachEntity.setIdStudent(stuId);
                    System.out.print("Ok! Enter the First name of Student: ");
                    String fName = scanner.next();
                    stuEntity.setfName(fName);
                    System.out.print("And Enter the Last Name of Student: ");
                    String lName = scanner.next();
                    stuEntity.setlName(lName);
                    teachService.printTeach();
                    System.out.print("And Enter your teacher ID: ");
                    int idTeacher = scanner.nextInt();
                    stu_teachEntity.setIdTeacher(idTeacher);

                    stuService.addStu(stuEntity);
                    stu_teachService.addStuTeach(stu_teachEntity);

                    stuService.printStu();

                } else if (status == 3) {
                    System.out.print("Id Student: ");
                    int id = scanner.nextInt();
                    stuEntity.setSsn(id);
                    stuService.removeStu(stuEntity);
                    stuService.printStu();

                } else if (status == 4) {
                    System.out.print("your StudentId: ");
                    int id = scanner.nextInt();
                    teachEntity.setSsn(id);
                    List<TeachEntity> teachers = stuService.findTeach(teachEntity);
                    for (TeachEntity model : teachers) {
                        System.out.println(model.toString());
                    }
                }
            }
            System.out.print("To continue? (Y/N) :");
            resume = scanner.next();
        } while (resume.toUpperCase().equals("Y")) ;
    }
}

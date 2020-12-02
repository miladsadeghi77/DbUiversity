package entities;

public class Stu_TeachEntity extends OperationEntity {
    @Override
    public int getSsn() {
        return super.getSsn();
    }

    @Override
    public void setSsn(int ssn) {
        super.setSsn(ssn);
    }

    @Override
    public int getIdTeacher() {
        return super.getIdTeacher();
    }

    @Override
    public void setIdTeacher(int idTeacher) {
        super.setIdTeacher(idTeacher);
    }

    @Override
    public int getIdStudent() {
        return super.getIdStudent();
    }

    @Override
    public void setIdStudent(int idStudent) {
        super.setIdStudent(idStudent);
    }

    @Override
    public String toString() {
        return "{ Snn=" + getSsn() +
                ", IdStudent='" + getIdStudent() + '\'' +
                ", IdTeacher='" + getIdTeacher() + '\'' +
                '}';
    }
}

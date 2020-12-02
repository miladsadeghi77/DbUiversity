package entities;

public class StuEntity extends OperationEntity {
    @Override
    public int getSsn() {
        return super.getSsn();
    }

    @Override
    public void setSsn(int ssn) {
        super.setSsn(ssn);
    }

    @Override
    public String getfName() {
        return super.getfName();
    }

    @Override
    public void setfName(String fName) {
        super.setfName(fName);
    }

    @Override
    public String getlName() {
        return super.getlName();
    }

    @Override
    public void setlName(String lName) {
        super.setlName(lName);
    }


    @Override
    public String toString() {
        return  "{ StudentID=" + getSsn() +
                " | FnameStudent='" + getfName() + '\'' +
                " | LnameStudent='" + getlName() + '\'' +
                '}';
    }
}

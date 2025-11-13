abstract public class StaffMember{
    protected String name;
    protected String adress;
    protected String phoneNumber;

    public StaffMember(String name, String adress, String number){
        this.name = name;
        this.adress = adress;
        this.phoneNumber = number;
    }

    public String toString(){
        String res =    "\n" + "Name: " + this.name + "\n" +
                        "Adress: " + this.phoneNumber + "\n" +
                        "Phone Number: " + this.phoneNumber;
        return res;
    }

    abstract public double pay();
}
public class Volontario extends StaffMember{
    public Volontario(String name, String adress, String number){
        super(name, adress, number);
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "Grazie!";
    }

    @Override
    public double pay(){
        return 0.0;
    }
}
public class ImpiegatoOre extends Impiegato{
    private int hourWorked = 0;
    private double hourWage;
    public ImpiegatoOre(String name, String adress, String number, String cf, double hourWage){
        super(name, adress, number, cf, 0);
        this.hourWage = hourWage;
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "Ore Lavorate: " + this.hourWorked;
    }

    @Override
    public double pay(){
        return this.hourWage * ((double)this.hourWorked);
    }

    public void addHours(int hours){
        this.hourWorked += hours;
    }
}
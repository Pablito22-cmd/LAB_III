public class Dirigente extends Impiegato{
    private double bonus = 0;
    public Dirigente(String name, String adress, String number, String cf, double wage){
        super(name, adress, number, cf, wage);
    }

    @Override
    public double pay(){
        return this.wage + this.bonus;
    }

    public void awardBonus(double bonus){
        this.bonus += bonus;
    }
}
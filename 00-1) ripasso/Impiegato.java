public class Impiegato extends StaffMember{
    protected String codiceFiscale;
    protected double wage;
    public Impiegato(String name, String adress, String number, String cf, double wage){
        super(name, adress, number);
        this.codiceFiscale = cf;
        this.wage = wage;
    }

    @Override
    public String toString(){
        String plus =   "Codice Fiscale: " + this.codiceFiscale + "\n" +
                        "Wage: " + this.wage;
        return super.toString() + plus;
    }

    @Override
    public double pay(){
        return this.wage;
    }
}
public class Main{
    public static void main(String[] args){
        Members personel = new Members();
        personel.payday();
    }
}

class Members{
    private StaffMember staffList[] = new StaffMember[6];
    public Members(){
        staffList[0] = new Dirigente("Laura","Via Roma 5","070707","123456789",2500);
        staffList[1] = new Impiegato("Carla","Via Liguria 20","050505","987654321",1250);
        staffList[2] = new Impiegato("Mario", "Via Milano 120","060606","010203040",1500);
        staffList[3] = new ImpiegatoOre("Alessia","Via Calabria 231","010101","958473625",15.55);
        staffList[4] = new Volontario("Giovanni","Via Campania 4","020202");
        staffList[5] = new Volontario("Anna","Via Cagliari 27","090909");
        ((Dirigente)staffList[0]).awardBonus(500.00);
        ((ImpiegatoOre)staffList[3]).addHours(40);
    }

    public void payday(){
        for(int i = 0; i < 6; i++){
            System.out.println(this.staffList[i]);
            System.out.println(this.staffList[i].pay());
        }
    }
}
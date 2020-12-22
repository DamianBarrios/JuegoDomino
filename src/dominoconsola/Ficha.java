package dominoconsola;

public class Ficha {

    int numero1;
    int numero2;
    String id;

    public Ficha(int numero1, int numero2, String id) {
        super();
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.id = id;
    }

    public int getNumero1() {
            return numero1;
    }

    public void setNumero1(int numero1) {
            this.numero1 = numero1;
    }

    public int getNumero2() {
            return numero2;
    }

    public void setNumero2(int numero2) {
            this.numero2 = numero2;
    }

}
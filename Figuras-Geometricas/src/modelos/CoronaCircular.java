package modelos;

public class CoronaCircular extends Circulo{

    private double _radioInterno;

    public CoronaCircular(String name, double radioExterno, double radioInterno) {
        super(name, radioExterno);
        _radioInterno = radioInterno;
    }

    public double getArea() {
        return super.getArea() - Math.PI * Math.pow(_radioInterno, 2);
    }

}
package modelos;

public class Hexagono extends FiguraGeometrica{
    private double _lado;
    private double _apotema; // apotema

    public Hexagono(String nombre, double lado, double apotema){
        super(nombre);
        _lado = lado;
        _apotema = apotema;
    }

    public double getArea(){
        return ((6 * _lado) * _apotema) / 2;
    }

}

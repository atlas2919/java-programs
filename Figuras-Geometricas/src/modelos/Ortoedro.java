package modelos;

public class Ortoedro extends FiguraGeometrica{
    private double _ladoA;
    private double _ladoB;
    private double _ladoC;

    public Ortoedro(String nombre, double ladoA, double ladoB, double ladoC) {
        super(nombre);
        _ladoA = ladoA;
        _ladoB = ladoB;
        _ladoC = ladoC;
    }

    public double getArea(){
        return (2 * ((_ladoA * _ladoB) + (_ladoA * _ladoC) + (_ladoC * _ladoB)));
    }
}

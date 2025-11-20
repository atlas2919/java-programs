package modelos;

public class Triangulo extends FiguraGeometrica{
    private double _ladoA;
    private double _ladoB;
    private double _ladoC;
    
    public Triangulo(String nombre, double ladoA, double ladoB, double ladoC) {
        super(nombre);
        _ladoA = ladoA;
        _ladoB = ladoB;
        _ladoC = ladoC;
    }

    public double getArea(){
        double semiPerimetro = (_ladoA + _ladoB + _ladoC) / 2;
        return Math.sqrt(semiPerimetro 
            * (semiPerimetro - _ladoA) 
            * (semiPerimetro - _ladoB) 
            * (semiPerimetro - _ladoC));
    }
}

package modelos;

public class Trapecio extends FiguraGeometrica{
    private double _baseMayor;
    private double _baseMenor;
    private double _altura;

    public Trapecio(String nombre, double baseMayor, double baseMenor, double altura) {
        super(nombre);
        _baseMayor = baseMayor;
        _baseMenor = baseMenor;
        _altura = altura;
    }

    public double getArea(){
        return ((_baseMayor + _baseMenor) * _altura) / 2;
    }
}
